package com.example.transfer.model.dto;

import com.example.transfer.model.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class AccountDTO {

    private Long id;

    private Integer accountNumber;

    private Integer digit;

    private BigDecimal balance;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.accountNumber = account.getAccountNumber();
        this.digit = account.getDigit();
        this.balance = account.getBalance();
    }
}
