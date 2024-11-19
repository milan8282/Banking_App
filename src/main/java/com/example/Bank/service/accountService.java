package com.example.Bank.service;

import com.example.Bank.model.account;
import com.example.Bank.repository.accountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class accountService {

    @Autowired
    private accountRepository accountRepo;

    @Autowired
    private transactionService transService;

    // Create a new account
    public account openAccount(String accountHolder,double initialBalance){
        String accountNumber = generateAccountNumber();
        account ac = new account();
        ac.setAccountNumber(accountNumber);
        ac.setAccountHolder(accountHolder);
        ac.setAmount(initialBalance);
        accountRepo.save(ac);

        transService.logTransaction(accountNumber,"deposit",initialBalance);

        return ac;
    }


//    withdraw Money
    public String withdraw(String accountNumber,double amount){
        Optional<account> accountOpt = accountRepo.findByAccountNumber(accountNumber);
        if (accountOpt.isPresent()){
            account ac = accountOpt.get();
            if (ac.getAmount() >= amount){
                ac.setAmount(ac.getAmount() - amount);
                accountRepo.save(ac);

                // Log the withdrawal transaction
                transService.logTransaction(accountNumber,"withdraw",amount);

                return "withDrawl Successful";
            }else {
                return "Insufficient Balance";
            }
        }
        return "Account Not Found";
    }

    // Deposit money
    public String deposit(String accountNumber,double amount){
        Optional<account> accountOpt = accountRepo.findByAccountNumber(accountNumber);
        if (accountOpt.isPresent()){
            account ac = accountOpt.get();
            ac.setAmount(ac.getAmount() + amount);
            accountRepo.save(ac);

            // Log the deposit transaction
            transService.logTransaction(accountNumber, "deposit", amount);

            return "Deposit Successful";
        }
        return "Account Not Found";
    }


    // Transfer money between two accounts
    public String transferMoney(String fromAccountNum,String toAccountNum,double amount){
        Optional<account> fromAccountOpt = accountRepo.findByAccountNumber(fromAccountNum);
        Optional<account> toAccountOpt = accountRepo.findByAccountNumber(toAccountNum);

        if (fromAccountOpt.isPresent() && toAccountOpt.isPresent()){
            account fromAccount = fromAccountOpt.get();
            account toAccount = toAccountOpt.get();

            if (fromAccount.getAmount() >= amount){
                fromAccount.setAmount(fromAccount.getAmount() - amount);
                toAccount.setAmount(toAccount.getAmount() + amount);
                accountRepo.save(fromAccount);
                accountRepo.save(toAccount);

                // Log the transactions
                transService.logTransaction(fromAccountNum,"withdraw",amount);
                transService.logTransaction(toAccountNum,"deposit",amount);

                return "Transfer successful";
            }else {
                return "Insufficient Funds in source Account";
            }
        }
        return "One or Both accounts Not Found";
    }



    // Show balance of an account
    public Optional<account> showBalance(String accountNumber) {
        return accountRepo.findByAccountNumber(accountNumber);
    }

    private String generateAccountNumber() {
        return String.format("%010d", (int)(Math.random() * 1000000000L));
    }
}
