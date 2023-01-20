package com.example.transfer.service;

import com.example.transfer.model.TransferRequest;
import com.example.transfer.model.dto.TransferRequestDTO;

public interface TransferService {
    TransferRequestDTO transfer(TransferRequest transferRequest);
}
