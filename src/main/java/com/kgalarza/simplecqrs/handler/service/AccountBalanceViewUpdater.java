package com.kgalarza.simplecqrs.handler.service;

import com.kgalarza.simplecqrs.model.Account;
import com.kgalarza.simplecqrs.model.AccountBalanceView;
import com.kgalarza.simplecqrs.repository.AccountBalanceViewRepository;
import com.kgalarza.simplecqrs.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountBalanceViewUpdater {

    private final AccountRepository accountRepository;
    private final AccountBalanceViewRepository viewRepository;

    public AccountBalanceViewUpdater(AccountRepository accountRepository,
                                     AccountBalanceViewRepository viewRepository) {
        this.accountRepository = accountRepository;
        this.viewRepository = viewRepository;
    }

    public void updateAccountBalanceView(String accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        AccountBalanceView view = new AccountBalanceView();
        view.setId(account.getId());
        view.setAccountNumber(account.getAccountNumber());
        view.setOwner(account.getOwner());
        view.setCurrentBalance(account.getBalance());

        viewRepository.save(view);
    }

    public void deleteFromView(String accountId) {
        viewRepository.deleteById(accountId);
    }

}
