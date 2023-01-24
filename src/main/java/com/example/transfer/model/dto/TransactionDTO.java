package com.example.transfer.model.dto;

import com.example.transfer.enums.TransactionStatus;
import com.example.transfer.model.Transaction;
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

    private AccountDTO account;

    private BigDecimal amount;

    private String message;

    private TransactionStatus type;

    private LocalDateTime date;


    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.account = new AccountDTO(transaction.getAccount());
        this.amount = transaction.getAmount();
        this.message = transaction.getMessage();
        this.type = transaction.getType();
        this.date = transaction.getDate();
    }
}
