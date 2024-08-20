package com.financeflow.be.controllers;

import com.financeflow.be.core.exceptions.*;
import com.financeflow.be.models.dto.TransactionIn;
import com.financeflow.be.models.dto.TransactionOut;
import com.financeflow.be.services.implementations.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping()
    public ResponseEntity<TransactionOut> commitTransaction(@RequestBody TransactionIn request)
            throws AccountNotFoundException, NotEnoughBalanceException, ExpenseNotFoundException, CurrencyDoesNotExistException {
        TransactionOut response = transactionService.commitTransaction(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<TransactionOut>> getTransactions() throws AccountNotFoundException, CurrencyDoesNotExistException, TransactionsNotFoundExceptions {
        List<TransactionOut> response = transactionService.getAllTransactions();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TransactionOut>> getTransactionsForAccount(@PathVariable Integer id) throws AccountNotFoundException, NoTransactionsForAccountException, CurrencyDoesNotExistException {
        List<TransactionOut> response = transactionService.getAllTransactionsForAccount(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
