package com.example.transfer.service;

import com.example.transfer.model.TransactionRequest;
import com.example.transfer.model.dto.TransferRequestDTO;

public interface TransactionRequestService {
    TransferRequestDTO transfer(TransactionRequest transferRequest);
}
