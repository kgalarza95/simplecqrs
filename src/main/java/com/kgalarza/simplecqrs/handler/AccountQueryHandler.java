package com.kgalarza.simplecqrs.handler;

import com.kgalarza.simplecqrs.cqrs.query.GetAccountQuery;
import com.kgalarza.simplecqrs.cqrs.query.GetAllAccountsQuery;
import com.kgalarza.simplecqrs.model.Account;
import com.kgalarza.simplecqrs.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountQueryHandler {

    private final AccountRepository accountRepository;

    public AccountQueryHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account handle(GetAccountQuery query) {
        return accountRepository.findById(query.getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public List<Account> handle(GetAllAccountsQuery query) {
        return accountRepository.findAll();
    }
}
