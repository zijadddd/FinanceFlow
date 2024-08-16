package com.financeflow.be.repositories;

import com.financeflow.be.models.dao.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Modifying
    @Query(value = "ALTER TABLE transactions AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
