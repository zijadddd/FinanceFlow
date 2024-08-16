package com.financeflow.be.controllers;

import com.financeflow.be.core.exceptions.CurrencyDoesNotExistException;
import com.financeflow.be.core.exceptions.DefaultAccountNotFoundException;
import com.financeflow.be.models.dto.*;
import com.financeflow.be.services.implementations.DefaultAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/defaultaccount")
public class DefaultAccountController {

    @Autowired
    private DefaultAccountService defaultAccountService;

    @GetMapping()
    public ResponseEntity<DefaultAccountOut> getDefaultAccount() throws DefaultAccountNotFoundException {
        DefaultAccountOut response = defaultAccountService.getDefaultAccount();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<MessageOut> changeCurrencyCode(@RequestBody DefaultAccountIn request) throws DefaultAccountNotFoundException, CurrencyDoesNotExistException {
        MessageOut response = defaultAccountService.changeCurrency(request.getCurrencyCode());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<MessageOut> deleteAllData() {
        MessageOut response = defaultAccountService.deleteAllData();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
