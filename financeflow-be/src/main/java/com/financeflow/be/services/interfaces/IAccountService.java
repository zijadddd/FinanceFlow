package com.financeflow.be.services.interfaces;

import com.financeflow.be.core.exceptions.AccountNotFoundException;
import com.financeflow.be.core.exceptions.AccountsNotFoundException;
import com.financeflow.be.core.exceptions.BalanceNeedToBeGreaterThanZeroException;
import com.financeflow.be.core.exceptions.CurrencyDoesNotExistException;
import com.financeflow.be.models.dao.Account;
import com.financeflow.be.models.dto.AccountIn;
import com.financeflow.be.models.dto.AccountOut;

import java.util.List;

public interface IAccountService {
    List<AccountOut> getAll() throws AccountsNotFoundException, CurrencyDoesNotExistException;
    AccountOut create(AccountIn request) throws CurrencyDoesNotExistException, BalanceNeedToBeGreaterThanZeroException;
    void deleteAllAccounts();
}
