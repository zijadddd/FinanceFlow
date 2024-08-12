package com.financeflow.be.controllers;

import com.financeflow.be.core.exceptions.CurrencyDoesNotExistException;
import com.financeflow.be.core.exceptions.DefaultAccountNotFoundException;
import com.financeflow.be.models.dto.AccountIn;
import com.financeflow.be.models.dto.AccountOut;
import com.financeflow.be.models.dto.DefaultAccountIn;
import com.financeflow.be.models.dto.DefaultAccountOut;
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
    public ResponseEntity<String> changeCurrencyCode(@RequestBody DefaultAccountIn request) throws DefaultAccountNotFoundException, CurrencyDoesNotExistException {
        String response = defaultAccountService.changeCurrency(request.getCurrencyCode());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
