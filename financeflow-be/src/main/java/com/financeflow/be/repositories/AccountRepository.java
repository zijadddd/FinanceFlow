package com.financeflow.be.repositories;

import com.financeflow.be.models.dao.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Modifying
    @Query(value = "ALTER TABLE accounts AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
