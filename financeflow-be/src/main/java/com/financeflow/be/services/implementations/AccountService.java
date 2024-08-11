package com.financeflow.be.services.implementations;

import com.financeflow.be.core.exceptions.AccountNotFoundException;
import com.financeflow.be.core.exceptions.AccountsNotFoundException;
import com.financeflow.be.models.dao.Account;
import com.financeflow.be.models.dto.AccountIn;
import com.financeflow.be.models.dto.AccountOut;
import com.financeflow.be.repositories.AccountRepository;
import com.financeflow.be.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountOut getAccount(int id) throws AccountNotFoundException {
        return accountRepository.findById(id).map(AccountOut::new).orElseThrow(() -> new AccountNotFoundException(id));
    }

    @Override
    public List<AccountOut> getAll() throws AccountsNotFoundException {
        List<AccountOut> response = accountRepository.findAll().stream().map(AccountOut::new).collect(Collectors.toList());
        if (response.isEmpty()) throw new AccountsNotFoundException();

        return response;
    }

    @Override
    public AccountOut create(AccountIn request) {
        Account account = new Account();
        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());
        account.setBalance(request.getBalance());
        account.setCurrencyCode(request.getCurrencyCode());
        account.setCreatedAt(LocalDateTime.now());

        accountRepository.save(account);
        return new AccountOut(account);
    }

    @Override
    public String delete(int id) throws AccountNotFoundException {
        if (!accountRepository.existsById(id)) throw new AccountNotFoundException(id);
        accountRepository.deleteById(id);

        return "Account with id " + id + " was successfully deleted";
    }
}
