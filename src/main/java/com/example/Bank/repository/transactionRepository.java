package com.example.Bank.repository;

import com.example.Bank.model.transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface transactionRepository extends JpaRepository<transaction, Long> {

    // Find transactions by account number
    List<transaction> findByAccountNumber(String accountNumber);
}
