package com.kgalarza.simplecqrs.handler;

import com.kgalarza.simplecqrs.cqrs.command.CreateAccountCommand;
import com.kgalarza.simplecqrs.cqrs.command.DeleteAccountCommand;
import com.kgalarza.simplecqrs.cqrs.command.UpdateAccountCommand;
import com.kgalarza.simplecqrs.handler.service.AccountBalanceViewUpdater;
import com.kgalarza.simplecqrs.model.Account;
import com.kgalarza.simplecqrs.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountCommandHandler {

    private final AccountRepository accountRepository;
    private final AccountBalanceViewUpdater viewUpdater;

    public AccountCommandHandler(AccountRepository accountRepository,
                                 AccountBalanceViewUpdater viewUpdater) {
        this.accountRepository = accountRepository;
        this.viewUpdater = viewUpdater;
    }

    public void handle(CreateAccountCommand command) {
        Account account = new Account();
        account.setId(null); // MongoDB genera el ID automáticamente.
        account.setAccountNumber(command.getAccountNumber());
        account.setOwner(command.getOwner());
        account.setBalance(command.getInitialBalance());
        accountRepository.save(account);

        // Update the materialized view
        viewUpdater.updateAccountBalanceView(account.getId());
    }

    public void handle(UpdateAccountCommand command) {
        Account account = accountRepository.findById(command.getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(command.getNewBalance());
        account = accountRepository.save(account);

        // Update the materialized view
        viewUpdater.updateAccountBalanceView(account.getId());
    }

    public void handle(DeleteAccountCommand command) {
        accountRepository.deleteById(command.getId());

        // Remove from the materialized view
        viewUpdater.deleteFromView(command.getId());
    }
}
