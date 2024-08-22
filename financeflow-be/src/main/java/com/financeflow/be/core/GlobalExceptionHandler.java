package com.financeflow.be.core;

import com.financeflow.be.core.exceptions.*;
import com.financeflow.be.models.dto.MessageOut;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountsNotFoundException.class)
    public ResponseEntity<MessageOut> handleAccountsNotFoundException(AccountsNotFoundException ex) {
        return new ResponseEntity<>(new MessageOut(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<MessageOut> handleAccountNotFoundException(AccountNotFoundException ex) {
        return new ResponseEntity<>(new MessageOut(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    public ResponseEntity<MessageOut> handleNotEnoughBalanceException(NotEnoughBalanceException ex) {
        return new ResponseEntity<>(new MessageOut(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<MessageOut> handleExpenseNotFoundException(ExpenseNotFoundException ex) {
        return new ResponseEntity<>(new MessageOut(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionsNotFoundExceptions.class)
    public ResponseEntity<MessageOut> handleTransactionsNotFoundExceptions(TransactionsNotFoundExceptions ex) {
        return new ResponseEntity<>(new MessageOut(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DefaultAccountNotFoundException.class)
    public ResponseEntity<MessageOut> handleDefaultAccountNotFoundException(DefaultAccountNotFoundException ex) {
        return new ResponseEntity<>(new MessageOut(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CurrencyDoesNotExistException.class)
    public ResponseEntity<MessageOut> handleCurrencyDoesNotExistException(CurrencyDoesNotExistException ex) {
        return new ResponseEntity<>(new MessageOut(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoTransactionsForAccountException.class)
    public ResponseEntity<MessageOut> handleNoTransactionsForAccountException(NoTransactionsForAccountException ex) {
        return new ResponseEntity<>(new MessageOut(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BalanceNeedToBeGreaterThanZeroException.class)
    public ResponseEntity<MessageOut> handleBalanceNeedToBeGreaterThanZeroException(BalanceNeedToBeGreaterThanZeroException ex) {
        return new ResponseEntity<>(new MessageOut(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
