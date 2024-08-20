package com.financeflow.be.services.interfaces;

import com.financeflow.be.core.exceptions.*;
import com.financeflow.be.models.dto.BalanceContainer;
import com.financeflow.be.models.dto.TransactionIn;
import com.financeflow.be.models.dto.TransactionOut;

import java.util.List;

public interface ITransactionService {
    Boolean checkCurrencyCode(String currencyCode) throws CurrencyDoesNotExistException;
    Double convertFromOneCurrencyToOther(BalanceContainer balanceContainer, String newCurrencyCode) throws CurrencyDoesNotExistException;
    TransactionOut commitTransaction(TransactionIn request) throws AccountNotFoundException, NotEnoughBalanceException, ExpenseNotFoundException, CurrencyDoesNotExistException;
    List<TransactionOut> getAllTransactions() throws AccountNotFoundException, CurrencyDoesNotExistException, TransactionsNotFoundExceptions;
    List<TransactionOut> getAllTransactionsForAccount(Integer id) throws AccountNotFoundException, NoTransactionsForAccountException, CurrencyDoesNotExistException;
    void deleteAllTransactions();
}
