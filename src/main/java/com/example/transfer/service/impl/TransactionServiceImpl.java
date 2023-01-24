package com.example.transfer.service.impl;

import com.example.transfer.model.Transaction;
import com.example.transfer.model.dto.AccountDTO;
import com.example.transfer.model.dto.TransactionDTO;
import com.example.transfer.repository.TransactionRepository;
import com.example.transfer.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<TransactionDTO> extract(Long userId) {
        return transactionRepository.findByAccountIdAndUserId(userId)
                .stream()
                .map(this::converter)
                .collect(Collectors.toList());
    }

    private TransactionDTO converter(Transaction transaction) {
        TransactionDTO result = new TransactionDTO();
        Transaction transactionDatabase = transactionRepository.getReferenceById(transaction.getId());
        result.setId(transactionDatabase.getId());
        result.setAccount(new AccountDTO(transactionDatabase.getAccount()));
        result.setDate(transactionDatabase.getDate());
        result.setMessage(transactionDatabase.getMessage());
        result.setAmount(transactionDatabase.getAmount());
        return result;
    }
}