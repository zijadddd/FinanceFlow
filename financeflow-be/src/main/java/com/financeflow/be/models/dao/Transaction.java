package com.financeflow.be.models.dao;

import com.financeflow.be.core.ExpenseType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private ExpenseType expense;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private String currencyCode;

    @Column(nullable = false)
    private LocalDateTime processedAt;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account accountId;
}
