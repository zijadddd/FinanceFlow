package com.financeflow.be.services.interfaces;

import com.financeflow.be.core.exceptions.AccountNotFoundException;
import com.financeflow.be.core.exceptions.AccountsNotFoundException;
import com.financeflow.be.models.dao.Account;
import com.financeflow.be.models.dto.AccountIn;
import com.financeflow.be.models.dto.AccountOut;

import java.util.List;

public interface IAccountService {
    AccountOut getAccount(Integer id) throws AccountNotFoundException;
    List<AccountOut> getAll() throws AccountsNotFoundException;
    AccountOut create(AccountIn request);
    String delete(Integer id) throws AccountNotFoundException;
}
