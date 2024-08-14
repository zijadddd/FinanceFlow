package com.financeflow.be.models.dao;

import com.financeflow.be.core.ExpenseType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "transactions")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private ExpenseType expense;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime processedAt;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;
}
