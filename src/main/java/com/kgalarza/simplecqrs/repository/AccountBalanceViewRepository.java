package com.kgalarza.simplecqrs.repository;

import com.kgalarza.simplecqrs.model.AccountBalanceView;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountBalanceViewRepository extends MongoRepository<AccountBalanceView, String> {
}
