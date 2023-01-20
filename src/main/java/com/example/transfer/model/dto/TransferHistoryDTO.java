package com.example.transfer.model.dto;

import com.example.transfer.enums.TransferStatus;
import com.example.transfer.model.TransferRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TransferHistoryDTO {
    private Long id;

    private TransferRequest transferRequest;

    private LocalDateTime timestamp;
    private String message;
    private TransferStatus status;

    public TransferHistoryDTO(TransferRequest transferRequest, String message, TransferStatus transferStatus) {
        this.transferRequest = transferRequest;
        this.message = message;
        this.status = transferStatus;
    }
}
