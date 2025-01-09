package com.kgalarza.simplecqrs.handler;

import com.kgalarza.simplecqrs.cqrs.query.GetAccountQuery;
import com.kgalarza.simplecqrs.cqrs.query.GetAllAccountsQuery;
import com.kgalarza.simplecqrs.model.AccountBalanceView;
import com.kgalarza.simplecqrs.repository.AccountBalanceViewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountViewQueryHandler {

    private final AccountBalanceViewRepository viewRepository;

    public AccountViewQueryHandler(AccountBalanceViewRepository viewRepository) {
        this.viewRepository = viewRepository;
    }

    public AccountBalanceView handle(GetAccountQuery query) {
        return viewRepository.findById(query.getId())
                .orElseThrow(() -> new RuntimeException("Account not found in the view"));
    }

    public List<AccountBalanceView> handle(GetAllAccountsQuery query) {
        return viewRepository.findAll();
    }
}
