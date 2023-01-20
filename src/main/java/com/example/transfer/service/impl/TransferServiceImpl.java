package com.example.transfer.service.impl;

import com.example.transfer.enums.TransferStatus;
import com.example.transfer.model.Account;
import com.example.transfer.model.dto.TransferRequestDTO;
import com.example.transfer.repository.AccountRepository;
import com.example.transfer.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AccountRepository accountRepository;

    public TransferRequestDTO transfer(TransferRequestDTO transferRequestDTO) {
        Account fromAccount = accountRepository.findById(transferRequestDTO.getFromAccount().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid account number"));
        Account toAccount = accountRepository.findById(transferRequestDTO.getToAccount().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid account number"));
        transferRequestDTO.setStatus(TransferStatus.IN_PROGRESS);
        if (fromAccount.getBalance().compareTo(transferRequestDTO.getAmount()) < 0) {
            transferRequestDTO.setStatus(TransferStatus.FAILED);
            throw new IllegalArgumentException("Insufficient funds");
        }
        transferRequestDTO.setStatus(TransferStatus.INITIATED);
        fromAccount.setBalance(fromAccount.getBalance().subtract(transferRequestDTO.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(transferRequestDTO.getAmount()));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        transferRequestDTO.setStatus(TransferStatus.SUCCESS);
        return transferRequestDTO;
    }
}