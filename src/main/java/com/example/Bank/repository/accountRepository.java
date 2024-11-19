package com.example.Bank.repository;

import com.example.Bank.model.account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface accountRepository extends JpaRepository<account,String> {
    Optional<account> findByAccountNumber(String accountNumber);
}
