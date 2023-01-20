package com.example.transfer.model;

import com.example.transfer.enums.TransferStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "transfer-request")
public class TransferRequest implements Serializable {


    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "transferRequest")
    private List<Transaction> transactions;

    @OneToOne
    @JoinColumn(name = "from_account_id")
    private Account fromAccount;

    @OneToOne
    @JoinColumn(name = "to_account_id")
    private Account toAccount;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "initiatedAt", insertable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime initiatedAt;

    @Column(name = "completedAt", insertable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime completedAt;

    @Column(name = "status", nullable = false)
    private TransferStatus status;

}
