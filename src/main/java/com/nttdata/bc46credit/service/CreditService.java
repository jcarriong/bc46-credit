package com.nttdata.bc46credit.service;


import com.nttdata.bc46credit.model.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {
  Flux<Credit> findAll();

  Mono<Credit> findById(String id);

  Mono<Credit> save(Credit Credit);

  Mono<Credit> updateCredit(Credit Credit, String idCredit);

  Mono<Credit> deleteCredit(String idCredit);
}
