package com.example.transfer.model.dto;

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
}
