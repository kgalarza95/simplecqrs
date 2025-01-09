package com.kgalarza.simplecqrs.cqrs.command;

public class CreateAccountCommand {

    private String accountNumber;
    private String owner;
    private Double initialBalance;

    public CreateAccountCommand() {
    }

    public CreateAccountCommand(String accountNumber, String owner, Double initialBalance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.initialBalance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }
}
