package com.example.transfer.listeners;

import com.example.transfer.enums.TransferStatus;
import com.example.transfer.model.TransferHistory;
import com.example.transfer.model.TransferRequest;
import com.example.transfer.model.dto.TransferHistoryDTO;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class TransferRequestListener {
    @PrePersist
    public void prePersist(TransferRequest transferRequest) {
        TransferHistoryDTO historyDTO = new TransferHistoryDTO(transferRequest,
                "Transfer initiated",
                TransferStatus.INITIATED);
        TransferHistory transferHistory = new TransferHistory();
        transferHistory.setTransferRequest(historyDTO.getTransferRequest());
        transferHistory.setMessage(historyDTO.getMessage());
        transferHistory.setStatus(historyDTO.getStatus());
        transferRequest.getHistory().add(transferHistory);
    }

    @PreUpdate
    public void preUpdate(TransferRequest transferRequest) {
        TransferHistoryDTO historyDTO = new TransferHistoryDTO(
                transferRequest,
                "Transfer completed",
                transferRequest.getStatus());

        TransferHistory transferHistory = new TransferHistory();
        transferHistory.setTransferRequest(historyDTO.getTransferRequest());
        transferHistory.setMessage(historyDTO.getMessage());
        transferHistory.setStatus(historyDTO.getStatus());

        transferRequest.getHistory().add(transferHistory);
    }
}