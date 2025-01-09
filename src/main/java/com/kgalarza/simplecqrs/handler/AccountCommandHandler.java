package com.kgalarza.simplecqrs.handler;

import com.kgalarza.simplecqrs.cqrs.command.CreateAccountCommand;
import com.kgalarza.simplecqrs.cqrs.command.DeleteAccountCommand;
import com.kgalarza.simplecqrs.cqrs.command.UpdateAccountCommand;
import com.kgalarza.simplecqrs.model.Account;
import com.kgalarza.simplecqrs.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountCommandHandler {

    private final AccountRepository accountRepository;

    public AccountCommandHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void handle(CreateAccountCommand command) {
        Account account = new Account();
        account.setId(null); // MongoDB genera el ID automáticamente.
        account.setAccountNumber(command.getAccountNumber());
        account.setOwner(command.getOwner());
        account.setBalance(command.getInitialBalance());
        accountRepository.save(account);
    }

    public void handle(UpdateAccountCommand command) {
        Optional<Account> optionalAccount = accountRepository.findById(command.getId());
        if (optionalAccount.isEmpty()) {
            throw new RuntimeException("Account not found");
        }

        Account account = optionalAccount.get();
        account.setBalance(command.getNewBalance());
        accountRepository.save(account);
    }

    public void handle(DeleteAccountCommand command) {
        accountRepository.deleteById(command.getId());
    }
}
