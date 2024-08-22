package com.financeflow.be.controllers;

import com.financeflow.be.core.exceptions.AccountNotFoundException;
import com.financeflow.be.core.exceptions.AccountsNotFoundException;
import com.financeflow.be.core.exceptions.BalanceNeedToBeGreaterThanZeroException;
import com.financeflow.be.core.exceptions.CurrencyDoesNotExistException;
import com.financeflow.be.models.dto.AccountIn;
import com.financeflow.be.models.dto.AccountOut;
import com.financeflow.be.services.implementations.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping()
    public ResponseEntity<List<AccountOut>> getAccounts() throws AccountsNotFoundException, CurrencyDoesNotExistException {
        List<AccountOut> response = accountService.getAll();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<AccountOut> createAccount(@RequestBody AccountIn request) throws CurrencyDoesNotExistException, BalanceNeedToBeGreaterThanZeroException {
        AccountOut response = accountService.create(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
