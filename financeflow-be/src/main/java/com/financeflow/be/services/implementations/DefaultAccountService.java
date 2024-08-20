package com.financeflow.be.services.implementations;

import com.financeflow.be.core.exceptions.CurrencyDoesNotExistException;
import com.financeflow.be.core.exceptions.DefaultAccountNotFoundException;
import com.financeflow.be.models.dao.DefaultAccount;
import com.financeflow.be.models.dto.BalanceContainer;
import com.financeflow.be.models.dto.DefaultAccountOut;
import com.financeflow.be.models.dto.MessageOut;
import com.financeflow.be.repositories.AccountRepository;
import com.financeflow.be.repositories.DefaultAccountRepository;
import com.financeflow.be.services.interfaces.IDefaultAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultAccountService implements IDefaultAccountService {

    @Autowired
    private DefaultAccountRepository defaultAccountRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @Override
    public DefaultAccountOut getDefaultAccount() {
        DefaultAccount defaultAccount = defaultAccountRepository.findById(1).get();
        DefaultAccountOut response = new DefaultAccountOut(defaultAccount.getBalance(), defaultAccount.getCurrencyCode());

        return response;
    }

    @Override
    public MessageOut changeCurrency(String currencyCode) throws DefaultAccountNotFoundException, CurrencyDoesNotExistException {
        DefaultAccount defaultAccount = defaultAccountRepository.findById(1).orElseThrow(DefaultAccountNotFoundException::new);
        Double newBalance = transactionService.convertFromOneCurrencyToOther(
                new BalanceContainer(defaultAccount.getBalance(), defaultAccount.getCurrencyCode()),
                currencyCode
        );
        defaultAccount.setBalance(newBalance);
        defaultAccount.setCurrencyCode(currencyCode);
        defaultAccountRepository.save(defaultAccount);
        return new MessageOut("Currency successfully changed.");
    }

    @Override
    public MessageOut deleteAllData() {
        accountService.deleteAllAccounts();
        transactionService.deleteAllTransactions();

        DefaultAccount defaultAccount = defaultAccountRepository.findById(1).get();
        defaultAccount.setBalance(0.0);
        defaultAccountRepository.save(defaultAccount);

        return new MessageOut("All data has been deleted.");
    }
}
