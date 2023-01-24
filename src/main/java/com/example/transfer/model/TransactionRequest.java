package com.example.transfer.model;

import com.example.transfer.enums.TransferStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transaction_request")
public class TransactionRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "from_transaction_id")
    private Transaction fromTransaction;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "to_transaction_id")
    private Transaction toTransaction;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "initiatedAt", nullable = false)
    private LocalDateTime initiatedAt;

    @Column(name = "completedAt", nullable = false)
    private LocalDateTime completedAt;

    @Column(name = "status", nullable = false)
    private TransferStatus status;

}
