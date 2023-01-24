package com.example.transfer.service.impl;

import com.example.transfer.enums.TransactionStatus;
import com.example.transfer.enums.TransferStatus;
import com.example.transfer.exception.AccountNotFoundException;
import com.example.transfer.exception.InsufficientFundsException;
import com.example.transfer.model.Account;
import com.example.transfer.model.Transaction;
import com.example.transfer.model.TransactionRequest;
import com.example.transfer.model.dto.TransferRequestDTO;
import com.example.transfer.repository.AccountRepository;
import com.example.transfer.repository.TransactionRequestRepository;
import com.example.transfer.service.TransactionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(rollbackFor=Exception.class)
public class TransactionRequestServiceImpl implements TransactionRequestService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRequestRepository transferRequestRepository;


    public TransferRequestDTO transfer(TransactionRequest transferRequest) throws AccountNotFoundException, InsufficientFundsException, UnsupportedOperationException {
        Account fromAccount = accountRepository.findByIdJoinUser(transferRequest.getFromTransaction().getAccount().getId())
                .orElseThrow(() -> new AccountNotFoundException("From account not found"));
        Account toAccount = accountRepository.findByIdJoinUser(transferRequest.getToTransaction().getAccount().getId())
                .orElseThrow(() -> new AccountNotFoundException("To account not found"));

        if (fromAccount.getBalance().compareTo(transferRequest.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient funds in the from account");
        }
        try {
            transferRequest.setInitiatedAt(LocalDateTime.now());
            fromAccount.setBalance(fromAccount.getBalance().subtract(transferRequest.getAmount()));
            toAccount.setBalance(toAccount.getBalance().add(transferRequest.getAmount()));
            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);

            // save transactions
            Transaction fromTransaction = new Transaction();
            fromTransaction.setAccount(fromAccount);
            fromTransaction.setDate(LocalDateTime.now());
            fromTransaction.setAmount(transferRequest.getAmount());
            fromTransaction.setType(TransactionStatus.DEBIT);
            fromTransaction.setMessage("Valor enviado");

            Transaction toTransaction = new Transaction();
            toTransaction.setAccount(toAccount);
            toTransaction.setDate(LocalDateTime.now());
            toTransaction.setAmount(transferRequest.getAmount());
            toTransaction.setType(TransactionStatus.CREDIT);
            toTransaction.setMessage("Valor recebido");

            transferRequest.setFromTransaction(fromTransaction);
            transferRequest.setToTransaction(toTransaction);
            transferRequest.setStatus(TransferStatus.SUCCESS);
            transferRequest.setCompletedAt(LocalDateTime.now());
            transferRequest = transferRequestRepository.save(transferRequest);

        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage(), e.getCause());
        }

        return new TransferRequestDTO(transferRequest);
    }
}