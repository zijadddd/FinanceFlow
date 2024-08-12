package com.financeflow.be.repositories;

import com.financeflow.be.models.dao.DefaultAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultAccountRepository extends JpaRepository<DefaultAccount, Integer> {
}
