package com.financeflow.be.services.interfaces;
import com.financeflow.be.core.exceptions.CurrencyDoesNotExistException;
import com.financeflow.be.core.exceptions.DefaultAccountNotFoundException;
import com.financeflow.be.models.dto.DefaultAccountOut;
import com.financeflow.be.models.dto.MessageOut;

public interface IDefaultAccountService {
    DefaultAccountOut getDefaultAccount();
    MessageOut changeCurrency(String currencyCode) throws DefaultAccountNotFoundException, CurrencyDoesNotExistException;
    MessageOut deleteAllData();
}
