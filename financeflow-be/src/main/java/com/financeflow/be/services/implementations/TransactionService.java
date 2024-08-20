package com.financeflow.be.services.implementations;

import com.financeflow.be.core.ExpenseType;
import com.financeflow.be.core.exceptions.*;
import com.financeflow.be.models.dao.Account;
import com.financeflow.be.models.dao.DefaultAccount;
import com.financeflow.be.models.dao.Transaction;
import com.financeflow.be.models.dto.AccountOut;
import com.financeflow.be.models.dto.BalanceContainer;
import com.financeflow.be.models.dto.TransactionIn;
import com.financeflow.be.models.dto.TransactionOut;
import com.financeflow.be.repositories.AccountRepository;
import com.financeflow.be.repositories.DefaultAccountRepository;
import com.financeflow.be.repositories.TransactionRepository;
import com.financeflow.be.services.interfaces.ITransactionService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TransactionService implements ITransactionService {
    private final String uri = "https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies/";
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DefaultAccountRepository defaultAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Boolean checkCurrencyCode(String currencyCode) throws CurrencyDoesNotExistException {
        String result = restTemplate.getForObject(uri + currencyCode.toLowerCase() + ".json", String.class);

        if (!result.contains(currencyCode.toLowerCase())) throw new CurrencyDoesNotExistException(currencyCode);
        return true;
    }

    @Override
    public Double convertFromOneCurrencyToOther(BalanceContainer balanceContainer, String newCurrencyCode) throws CurrencyDoesNotExistException {
        if (!this.checkCurrencyCode(newCurrencyCode)) throw new CurrencyDoesNotExistException(newCurrencyCode);
        String apiResponse = restTemplate.getForObject(uri + balanceContainer.getCurrencyCode().toLowerCase() + ".json", String.class);
        JSONObject currencyCourses = new JSONObject(apiResponse).getJSONObject(balanceContainer.getCurrencyCode().toLowerCase());


        return balanceContainer.getAmount() * currencyCourses.getDouble(newCurrencyCode.toLowerCase());
    }

    @Override
    public TransactionOut commitTransaction(TransactionIn request) throws AccountNotFoundException, NotEnoughBalanceException, ExpenseNotFoundException, CurrencyDoesNotExistException {
        Account account = accountRepository.findById(request.getAccountId()).orElseThrow(() -> new AccountNotFoundException(request.getAccountId()));
        if ((request.getAmount() * -1) > account.getBalance()) throw new NotEnoughBalanceException();

        Transaction transaction = new Transaction();
        transaction.setDescription(request.getDescription());
        transaction.setExpense(ExpenseType.fromString(request.getExpense()));
        transaction.setAmount(request.getAmount());
        transaction.setProcessedAt(LocalDateTime.now());
        transaction.setAccount(account);
        transactionRepository.save(transaction);

        account.setBalance(account.getBalance() + request.getAmount());
        accountRepository.save(account);

        DefaultAccount defaultAccount = defaultAccountRepository.findById(1).get();
        Double defaultAmount = this.convertFromOneCurrencyToOther(
                new BalanceContainer(transaction.getAmount(), account.getCurrencyCode()),
                defaultAccount.getCurrencyCode());

        List<Account> accountsBalances = accountRepository.findAll().stream().toList();
        Double sumOfBalances = accountsBalances.stream().map(accountBalance -> {
            try {
                return convertFromOneCurrencyToOther(
                                new BalanceContainer(accountBalance.getBalance(), accountBalance.getCurrencyCode()),
                                defaultAccount.getCurrencyCode());
            } catch (CurrencyDoesNotExistException e) {
                throw new RuntimeException(e);
            }
        }).reduce(0.0, Double::sum);
        defaultAccount.setBalance(sumOfBalances);
        defaultAccountRepository.save(defaultAccount);


        TransactionOut response = new TransactionOut(account.getName(),
                transaction.getDescription(), transaction.getExpense().toString(), transaction.getAmount(), account.getCurrencyCode(), defaultAmount, defaultAccount.getCurrencyCode(), transaction.getProcessedAt().toString());

        return response;
    }

    @Override
    public List<TransactionOut> getAllTransactions() throws AccountNotFoundException, CurrencyDoesNotExistException {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionOut> response = new ArrayList<>();
        for (Transaction transaction : transactions) {
            Account account = accountRepository.findById(transaction.getAccount().getId())
                    .orElseThrow(() -> new AccountNotFoundException(transaction.getAccount().getId()));

            Double defaultAmount = this.convertFromOneCurrencyToOther(new BalanceContainer(
                    transaction.getAmount(), account.getCurrencyCode()
            ), defaultAccountRepository.findById(1).get().getCurrencyCode());

            response.add(new TransactionOut(account.getName(), transaction.getDescription(), transaction.getExpense().toString(), transaction.getAmount(),
                    account.getCurrencyCode(), defaultAmount, defaultAccountRepository.findById(1).get().getCurrencyCode(), transaction.getProcessedAt().toString()));
        }

        return response;
    }

    @Override
    public List<TransactionOut> getAllTransactionsForAccount(Integer id) throws AccountNotFoundException, NoTransactionsForAccountException, CurrencyDoesNotExistException {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionOut> response = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAccount().getId() != id) continue;
            Account account = accountRepository.findById(transaction.getAccount().getId())
                    .orElseThrow(() -> new AccountNotFoundException(transaction.getAccount().getId()));

            DefaultAccount defaultAccount = defaultAccountRepository.findById(1).get();

            Double defaultAmount = this.convertFromOneCurrencyToOther(new BalanceContainer(
                    transaction.getAmount(), account.getCurrencyCode()
            ), defaultAccount.getCurrencyCode());

            response.add(new TransactionOut(account.getName(), transaction.getDescription(), transaction.getExpense().toString(), transaction.getAmount(),
                    account.getCurrencyCode(), defaultAmount, defaultAccount.getCurrencyCode(), transaction.getProcessedAt().toString()));
        }

        Account account = accountRepository.findById(id).get();

        if (response.isEmpty()) throw new NoTransactionsForAccountException(account.getName());

        return response;
    }

    @Override
    public void deleteAllTransactions() {
        transactionRepository.deleteAll();
        transactionRepository.resetAutoIncrement();
    }
}
