package com.example.transfer.service.impl;

import com.example.transfer.enums.TransactionStatus;
import com.example.transfer.enums.TransferStatus;
import com.example.transfer.exception.AccountNotFoundException;
import com.example.transfer.exception.InsufficientFundsException;
import com.example.transfer.model.Account;
import com.example.transfer.model.Transaction;
import com.example.transfer.model.TransferRequest;
import com.example.transfer.model.dto.TransferRequestDTO;
import com.example.transfer.repository.AccountRepository;
import com.example.transfer.repository.TransactionRepository;
import com.example.transfer.repository.TransferRequestRepository;
import com.example.transfer.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransferRequestRepository transferRequestRepository;

    public TransferRequestDTO transfer(TransferRequest transferRequest) throws AccountNotFoundException, InsufficientFundsException {
        Account fromAccount = accountRepository.findByAccountNumber(transferRequest.getFromAccount().getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException("From account not found"));
        Account toAccount = accountRepository.findByAccountNumber(transferRequest.getToAccount().getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException("To account not found"));

        if (fromAccount.getBalance().compareTo(transferRequest.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient funds in the from account");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(transferRequest.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(transferRequest.getAmount()));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // save transactions
        Transaction fromTransaction = new Transaction();
        fromTransaction.setAccount(fromAccount);
        fromTransaction.setAmount(transferRequest.getAmount());
        fromTransaction.setType(TransactionStatus.DEBIT);
        fromTransaction.setMessage("Valor enviado");

        Transaction toTransaction = new Transaction();
        toTransaction.setAccount(toAccount);
        toTransaction.setAmount(transferRequest.getAmount());
        toTransaction.setType(TransactionStatus.CREDIT);
        toTransaction.setMessage("Valor recebido");
        transferRequest.setTransactions(new ArrayList<>());
        transferRequest.getTransactions().add(fromTransaction);
        transferRequest.getTransactions().add(toTransaction);
        transferRequest.setCompletedAt(transferRequest.getCompletedAt());

        transferRequest = transferRequestRepository.save(transferRequest);
        transferRequest.setStatus(TransferStatus.SUCCESS);

        TransferRequestDTO transferRequestDTO = new TransferRequestDTO(transferRequest);

        return transferRequestDTO;
    }
}