package com.example.transfer.model.dto;

import com.example.transfer.enums.TransactionStatus;
import com.example.transfer.model.TransferRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDTO {
    private Long id;

    private TransferRequest transferRequest;

    private AccountDTO account;

    private BigDecimal amount;

    private String message;

    private TransactionStatus type;

    private LocalDateTime date;
}
