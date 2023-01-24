package com.example.transfer.service;

import com.example.transfer.model.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionDTO> extract(Long UserId);
}
