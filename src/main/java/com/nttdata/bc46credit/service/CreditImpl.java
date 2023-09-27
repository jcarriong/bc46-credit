package com.nttdata.bc46credit.service;

import com.nttdata.bc46credit.model.Credit;
import com.nttdata.bc46credit.repository.CreditRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditImpl implements CreditService {
  @Autowired
  CreditRepository creditRepository;

  @Override
  public Flux<Credit> findAll() {
    return creditRepository.findAll();
  }

  @Override
  public Mono<Credit> findById(String id) {
    return creditRepository.findById(id);
  }

  @Override
  public Mono<Credit> save(Credit Credit) {
    return creditRepository.save(Credit);
  }

  @Override
  public Mono<Credit> updateCredit(Credit Credit, String idCredit) {

    return creditRepository.findById(idCredit)
        .flatMap(currentCredit -> {
          currentCredit.setMainAccount(Credit.getMainAccount());
          currentCredit.setAdditionalAccounts(Credit.getAdditionalAccounts());
          currentCredit.setAvailableCredit(Credit.getAvailableCredit());
          currentCredit.setAmountConsumed(Credit.getAmountConsumed());
          currentCredit.setUpdateDatetime(LocalDateTime.now());
          return creditRepository.save(currentCredit);
          //Edición del resto de campos deshabilitada por relación e identidad
        });
  }

  @Override
  public Mono<Credit> deleteCredit(String idCredit) {
    return creditRepository.findById(idCredit)
        .flatMap(existingCredit -> creditRepository.delete(existingCredit)
            .then(Mono.just(existingCredit)));
  }
}
