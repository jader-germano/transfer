package com.example.transfer.model.dto;

import com.example.transfer.enums.TransferStatus;
import com.example.transfer.model.Account;
import com.example.transfer.model.Transaction;
import com.example.transfer.model.TransferRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TransferRequestDTO {
    private Long id;

    private List<Transaction> transactions;

    private Account fromAccount;

    private Account toAccount;

    private BigDecimal amount;

    private LocalDateTime initiatedAt;

    private LocalDateTime completedAt;

    private TransferStatus status;

    public TransferRequestDTO(TransferRequest transferRequest) {
        this.id = transferRequest.getId();
        this.transactions = transferRequest.getTransactions();
        this.fromAccount = transferRequest.getFromAccount();
        this.toAccount = transferRequest.getToAccount();
        this.amount = transferRequest.getAmount();
        this.initiatedAt = transferRequest.getInitiatedAt();
        this.completedAt = transferRequest.getCompletedAt();
        this.status = transferRequest.getStatus();
    }
}
