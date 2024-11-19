package com.example.Bank.controller;

import com.example.Bank.model.account;
import com.example.Bank.model.transaction;
import com.example.Bank.service.accountService;
import com.example.Bank.service.transactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/accounts")
public class accountController {

    @Autowired
    private accountService AccountService;

    @Autowired
    private transactionService TransactionService;

    // Open a new account
    @PostMapping("/open")
    public account openAccount(@RequestParam String accountHolder, @RequestParam double initialBalance) {
        return AccountService.openAccount(accountHolder, initialBalance);
    }

    // Withdraw money
    @PostMapping("/{accountNumber}/withdraw")
    public String withdraw(@PathVariable String accountNumber, @RequestParam double amount) {
        return AccountService.withdraw(accountNumber, amount);
    }

    // Deposit money
    @PostMapping("/{accountNumber}/deposit")
    public String deposit(@PathVariable String accountNumber, @RequestParam double amount) {
        return AccountService.deposit(accountNumber, amount);
    }

    // Transfer money
    @PostMapping("/transfer")
    public String transfer(@RequestParam String fromAccount, @RequestParam String toAccount, @RequestParam double amount) {
        return AccountService.transferMoney(fromAccount, toAccount, amount);
    }

    // Show balance
    @GetMapping("/{accountNumber}/balance")
    public Optional<account> showBalance(@PathVariable String accountNumber) {
        return AccountService.showBalance(accountNumber);
    }

    // Get account statement (list of transactions)
    @GetMapping("/{accountNumber}/statement")
    public List<transaction> getAccountStatement(@PathVariable String accountNumber) {
        return TransactionService.getAccountStatement(accountNumber);
    }
}
