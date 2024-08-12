package com.financeflow.be.services.interfaces;
import com.financeflow.be.core.exceptions.CurrencyDoesNotExistException;
import com.financeflow.be.core.exceptions.DefaultAccountNotFoundException;
import com.financeflow.be.models.dto.DefaultAccountOut;

public interface IDefaultAccountService {
    DefaultAccountOut getDefaultAccount();
    String changeCurrency(String currencyCode) throws DefaultAccountNotFoundException, CurrencyDoesNotExistException;
}
