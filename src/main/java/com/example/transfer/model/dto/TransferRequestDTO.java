package com.example.transfer.model.dto;

import com.example.transfer.enums.TransferStatus;
import com.example.transfer.model.TransactionRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TransferRequestDTO {
    private Long id;

    private TransactionDTO toTransaction;

    private TransactionDTO fromTransaction;

    private BigDecimal amount;

    private LocalDateTime initiatedAt;

    private LocalDateTime completedAt;

    private TransferStatus status;

    public TransferRequestDTO(TransactionRequest transferRequest) {
        this.id = transferRequest.getId();
        this.toTransaction = new TransactionDTO(transferRequest.getToTransaction());
        this.fromTransaction = new TransactionDTO(transferRequest.getFromTransaction());
        this.amount = transferRequest.getAmount();
        this.initiatedAt = transferRequest.getInitiatedAt();
        this.completedAt = transferRequest.getCompletedAt();
        this.status = transferRequest.getStatus();
    }
}
