package com.kgalarza.simplecqrs.controller;

import com.kgalarza.simplecqrs.cqrs.command.CreateAccountCommand;
import com.kgalarza.simplecqrs.cqrs.command.DeleteAccountCommand;
import com.kgalarza.simplecqrs.cqrs.command.UpdateAccountCommand;
import com.kgalarza.simplecqrs.cqrs.query.GetAccountQuery;
import com.kgalarza.simplecqrs.cqrs.query.GetAllAccountsQuery;
import com.kgalarza.simplecqrs.handler.AccountCommandHandler;
import com.kgalarza.simplecqrs.handler.AccountQueryHandler;
import com.kgalarza.simplecqrs.model.Account;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountCommandHandler commandHandler;
    private final AccountQueryHandler queryHandler;

    public AccountController(AccountCommandHandler commandHandler, AccountQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping
    public void createAccount(@RequestBody CreateAccountCommand command) {
        commandHandler.handle(command);
    }

    @PutMapping("/{id}")
    public void updateAccount(@PathVariable String id, @RequestBody UpdateAccountCommand command) {
        command.setId(id);
        commandHandler.handle(command);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable String id) {
        DeleteAccountCommand command = new DeleteAccountCommand(id);
        commandHandler.handle(command);
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable String id) {
        return queryHandler.handle(new GetAccountQuery(id));
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return queryHandler.handle(new GetAllAccountsQuery());
    }
}
