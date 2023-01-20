package com.example.transfer.model.dto;

import com.example.transfer.enums.TransferStatus;
import com.example.transfer.model.Account;
import com.example.transfer.model.TransferHistory;
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
    private Account fromAccount;
    private Account toAccount;
    private BigDecimal amount;
    private LocalDateTime initiatedAt;
    private LocalDateTime completedAt;
    private TransferStatus status;
    private List<TransferHistory> history;
}
