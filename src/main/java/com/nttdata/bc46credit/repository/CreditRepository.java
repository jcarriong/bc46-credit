package com.nttdata.bc46credit.repository;

import com.nttdata.bc46credit.model.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends ReactiveMongoRepository<Credit, String> {
}
