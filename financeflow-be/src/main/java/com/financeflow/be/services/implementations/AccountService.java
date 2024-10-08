package com.financeflow.be.services.implementations;

import com.financeflow.be.core.exceptions.AccountNotFoundException;
import com.financeflow.be.core.exceptions.AccountsNotFoundException;
import com.financeflow.be.core.exceptions.BalanceNeedToBeGreaterThanZeroException;
import com.financeflow.be.core.exceptions.CurrencyDoesNotExistException;
import com.financeflow.be.models.dao.Account;
import com.financeflow.be.models.dao.DefaultAccount;
import com.financeflow.be.models.dto.AccountIn;
import com.financeflow.be.models.dto.AccountOut;
import com.financeflow.be.models.dto.BalanceContainer;
import com.financeflow.be.repositories.AccountRepository;
import com.financeflow.be.repositories.DefaultAccountRepository;
import com.financeflow.be.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DefaultAccountRepository defaultAccountRepository;

    @Autowired
    private TransactionService transactionService;

    @Override
    public List<AccountOut> getAll() throws AccountsNotFoundException, CurrencyDoesNotExistException {
        List<Account> accounts = accountRepository.findAll();
        DefaultAccount defaultAccount = defaultAccountRepository.findById(1).get();
        if (accounts.isEmpty()) throw new AccountsNotFoundException();

        List<AccountOut> response = new ArrayList<>();
        for (Account account : accounts) {
            Double defaultBalance = transactionService.convertFromOneCurrencyToOther(
                    new BalanceContainer(account.getBalance(), account.getCurrencyCode()),
                    defaultAccount.getCurrencyCode()
            );

            response.add(new AccountOut(account, defaultBalance, defaultAccount.getCurrencyCode()));
        }

        return response;
    }

    @Override
    public AccountOut create(AccountIn request) throws CurrencyDoesNotExistException, BalanceNeedToBeGreaterThanZeroException {
        Account account = new Account();
        account.setName(request.getName());
        if (request.getBalance() < 0) throw new BalanceNeedToBeGreaterThanZeroException();
        account.setBalance(request.getBalance());
        account.setCurrencyCode(request.getCurrencyCode().toUpperCase());
        account.setCreatedAt(LocalDateTime.now());

        DefaultAccount defaultAccount = defaultAccountRepository.findById(1).get();

        account.setDefaultAccount(defaultAccount);

        accountRepository.save(account);

        Double defaultBalance = transactionService.convertFromOneCurrencyToOther(
                new BalanceContainer(account.getBalance(), account.getCurrencyCode()),
                defaultAccount.getCurrencyCode()
        );

        defaultAccount.setBalance(defaultAccount.getBalance() + defaultBalance);
        defaultAccountRepository.save(defaultAccount);

        return new AccountOut(account, defaultBalance, defaultAccount.getCurrencyCode());
    }

    @Override
    public void deleteAllAccounts() {
        accountRepository.deleteAll();
        accountRepository.resetAutoIncrement();
    }
}
