package com.kgalarza.simplecqrs.cqrs.command;

public class UpdateAccountCommand {

    private String id;
    private Double newBalance;

    public UpdateAccountCommand() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(Double newBalance) {
        this.newBalance = newBalance;
    }
}
