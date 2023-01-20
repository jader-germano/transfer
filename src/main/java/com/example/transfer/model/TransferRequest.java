package com.example.transfer.model;

import com.example.transfer.enums.TransferStatus;
import com.example.transfer.listeners.TransferRequestListener;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "transfer-request")
@EntityListeners(TransferRequestListener.class)
public class TransferRequest {


    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "from_account_id")
    private Account fromAccount;
    @OneToOne
    @JoinColumn(name = "to_account_id")
    private Account toAccount;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "initiatedAt", nullable = false)
    private LocalDateTime initiatedAt;

    @Column(name = "completedAt", nullable = false)
    private LocalDateTime completedAt;

    @Column(name = "status", nullable = false)
    private TransferStatus status;

    @OneToMany(mappedBy = "transferRequest", cascade = CascadeType.ALL)
    private List<TransferHistory> history;
}
