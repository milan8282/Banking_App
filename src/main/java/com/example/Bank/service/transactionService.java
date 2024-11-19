package com.example.Bank.service;

import com.example.Bank.model.transaction;
import com.example.Bank.repository.transactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class transactionService {

    @Autowired
    private transactionRepository transactionRepo;

    public void logTransaction(String accountNumber,String type,double amount){
        transaction trans = new transaction();
        trans.setAccountNumber(accountNumber);
        trans.setType(type);
        trans.setAmount(amount);
        trans.setDate(new Date());
        transactionRepo.save(trans);
    }

    public List<transaction> getAccountStatement(String accountNumber) {
        return transactionRepo.findByAccountNumber(accountNumber);
    }

}
