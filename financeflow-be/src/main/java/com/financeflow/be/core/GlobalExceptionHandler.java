package com.financeflow.be.core;

import com.financeflow.be.core.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountsNotFoundException.class)
    public ResponseEntity<String> handleAccountsNotFoundException(AccountsNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    public ResponseEntity<String> handleNotEnoughBalanceException(NotEnoughBalanceException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<String> handleExpenseNotFoundException(ExpenseNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionsNotFoundExceptions.class)
    public ResponseEntity<String> handleTransactionsNotFoundExceptions(TransactionsNotFoundExceptions ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DefaultAccountNotFoundException.class)
    public ResponseEntity<String> handleDefaultAccountNotFoundException(DefaultAccountNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CurrencyDoesNotExistException.class)
    public ResponseEntity<String> handleCurrencyDoesNotExistException(CurrencyDoesNotExistException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoTransactionsForAccountException.class)
    public ResponseEntity<String> handleNoTransactionsForAccountException(NoTransactionsForAccountException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
