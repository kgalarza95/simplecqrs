package com.kgalarza.simplecqrs.cqrs.query;

public class GetAccountQuery {
    private String id;

    public GetAccountQuery(String id) {
        this.id = id;
    }

    public GetAccountQuery() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
