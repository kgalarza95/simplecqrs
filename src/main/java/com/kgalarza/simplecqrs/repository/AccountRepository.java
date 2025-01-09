package com.kgalarza.simplecqrs.repository;

import com.kgalarza.simplecqrs.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
}
