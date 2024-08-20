package com.financeflow.be.core;

import com.financeflow.be.models.dao.Account;
import com.financeflow.be.models.dao.DefaultAccount;
import com.financeflow.be.models.dao.Transaction;
import com.financeflow.be.models.dto.BalanceContainer;
import com.financeflow.be.repositories.AccountRepository;
import com.financeflow.be.repositories.DefaultAccountRepository;
import com.financeflow.be.repositories.TransactionRepository;
import com.financeflow.be.services.implementations.TransactionService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DefaultAccountRepository defaultAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    @Override
    public void run(String... args) throws Exception {
        if (defaultAccountRepository.count() == 0) {
            DefaultAccount account = new DefaultAccount();
            account.setBalance(150.00);
            account.setCurrencyCode("BAM");
            defaultAccountRepository.save(account);
        }

        if (accountRepository.count() == 0) {
            Account account1 = new Account();
            account1.setName("Current");
            account1.setBalance(1200.50);
            account1.setCurrencyCode("EUR");
            account1.setCreatedAt(LocalDateTime.now());
            account1.setDefaultAccount(defaultAccountRepository.findById(1).get());
            accountRepository.save(account1);

            Account account2 = new Account();
            account2.setName("Savings");
            account2.setBalance(875.20);
            account2.setCurrencyCode("USD");
            account2.setCreatedAt(LocalDateTime.now());
            account2.setDefaultAccount(defaultAccountRepository.findById(1).get());
            accountRepository.save(account2);

            Account account3 = new Account();
            account3.setName("PayPal");
            account3.setBalance(320.75);
            account3.setCurrencyCode("EUR");
            account3.setCreatedAt(LocalDateTime.now());
            account3.setDefaultAccount(defaultAccountRepository.findById(1).get());
            accountRepository.save(account3);

            DefaultAccount defaultAccount = defaultAccountRepository.findById(1).get();
            Double account1BalanceInBam = transactionService.convertFromOneCurrencyToOther(new BalanceContainer(account1.getBalance(), account1.getCurrencyCode()), defaultAccount.getCurrencyCode());
            Double account2BalanceInBam = transactionService.convertFromOneCurrencyToOther(new BalanceContainer(account2.getBalance(), account2.getCurrencyCode()), defaultAccount.getCurrencyCode());
            Double account3BalanceInBam = transactionService.convertFromOneCurrencyToOther(new BalanceContainer(account3.getBalance(), account3.getCurrencyCode()), defaultAccount.getCurrencyCode());
            defaultAccount.setBalance(account1BalanceInBam + account2BalanceInBam + account3BalanceInBam);
            defaultAccountRepository.save(defaultAccount);
        }

        if (transactionRepository.count() == 0) {
            Transaction transaction1 = new Transaction();
            transaction1.setDescription("Deposit some cash");
            transaction1.setAmount(500.00);
            transaction1.setAccount(accountRepository.findById(1).get());
            transaction1.setExpense(ExpenseType.TRANSFER);
            transaction1.setProcessedAt(LocalDateTime.now());
            transactionRepository.save(transaction1);

            Transaction transaction2 = new Transaction();
            transaction2.setDescription("Groceries");
            transaction2.setAmount(-150.75);
            transaction2.setAccount(accountRepository.findById(1).get());
            transaction2.setExpense(ExpenseType.FOOD);
            transaction2.setProcessedAt(LocalDateTime.now());
            transactionRepository.save(transaction2);

            Transaction transaction3 = new Transaction();
            transaction3.setDescription("Transfer to friends account");
            transaction3.setAmount(-200.00);
            transaction3.setAccount(accountRepository.findById(1).get());
            transaction3.setExpense(ExpenseType.TRANSFER);
            transaction3.setProcessedAt(LocalDateTime.now());
            transactionRepository.save(transaction3);

            Transaction transaction4 = new Transaction();
            transaction4.setDescription("Deposit money");
            transaction4.setAmount(300.00);
            transaction4.setAccount(accountRepository.findById(2).get());
            transaction4.setExpense(ExpenseType.TRANSFER);
            transaction4.setProcessedAt(LocalDateTime.now());
            transactionRepository.save(transaction4);

            Transaction transaction5 = new Transaction();
            transaction5.setDescription("Udemy course payment");
            transaction5.setAmount(-45.00);
            transaction5.setAccount(accountRepository.findById(2).get());
            transaction5.setExpense(ExpenseType.EDUCATION);
            transaction5.setProcessedAt(LocalDateTime.now());
            transactionRepository.save(transaction5);

            Transaction transaction6 = new Transaction();
            transaction6.setDescription("Add money to account");
            transaction6.setAmount(150.00);
            transaction6.setAccount(accountRepository.findById(3).get());
            transaction6.setExpense(ExpenseType.TRANSFER);
            transaction6.setProcessedAt(LocalDateTime.now());
            transactionRepository.save(transaction6);

            Transaction transaction7 = new Transaction();
            transaction7.setDescription("Electricity bill payment");
            transaction7.setAmount(-60.00);
            transaction7.setAccount(accountRepository.findById(3).get());
            transaction7.setExpense(ExpenseType.PERSONAL_SERVICES);
            transaction7.setProcessedAt(LocalDateTime.now());
            transactionRepository.save(transaction7);
        }
    }
}
