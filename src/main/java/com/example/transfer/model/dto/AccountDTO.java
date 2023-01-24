package com.example.transfer.model.dto;

import com.example.transfer.model.Account;
import com.example.transfer.model.User;
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

    private UserDTO user;

    private Integer digit;

    private BigDecimal balance;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.accountNumber = account.getAccountNumber();
        this.user = new UserDTO(account.getUser());
        this.digit = account.getDigit();
        this.balance = account.getBalance();
    }

    public AccountDTO(User user) {
        this.user = new UserDTO(user);
    }
}
