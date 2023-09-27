package com.nttdata.bc46credit.controller;

import com.nttdata.bc46credit.model.Credit;
import com.nttdata.bc46credit.service.CreditService;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@Slf4j
public class CreditController {

  @Autowired
  private CreditService creditService;

  /**
   * Consultar todos los crédito existentes
   **/
  @GetMapping("/findAll")
  public Flux<Credit> findAll() {
    log.info("All credit card were consulted");
    return creditService.findAll()
        .doOnNext(Credit -> Credit.toString());
  }

  /**
   * Consultar crédito por id
   **/
  @GetMapping("/findById/{id}")
  public Mono<ResponseEntity<Credit>> findById(@PathVariable("id") String id) {
    log.info("Credit card consulted by id " + id);
    return creditService.findById(id)
        .map(ResponseEntity::ok)
        .switchIfEmpty(Mono.error(() -> new RuntimeException("Datos de la tarjeta de crédito no encontrada")));
  }

  /**
   * Registrar crédito
   **/
  @PostMapping("/saveCredit")
  public Mono<ResponseEntity<Credit>> save(@RequestBody Credit Credit) {
    log.info("A credit card was created");
    Credit.setCreationDatetime(LocalDateTime.now());
    return creditService.save(Credit)
        .map(bc -> new ResponseEntity<>(bc, HttpStatus.CREATED))
        .defaultIfEmpty(new ResponseEntity<>(HttpStatus.CONFLICT));
  }

  /**
   * Editar datos de crédito(se restringe la edición de llaves compuestas)
   **/
  @PutMapping("/updateCredit/{idCredit}")
  public Mono<ResponseEntity<Credit>> update(@RequestBody Credit Credit,
                                             @PathVariable("idCredit") String idCredit) {
    log.info("A credit card was changed");
    return creditService.updateCredit(Credit, idCredit)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.badRequest().build());
  }

  /**
   * Eliminar un crédito existente
   **/
  @DeleteMapping("/deleteCredit/{idCredit}")
  public Mono<ResponseEntity<Void>> deleteCredit(@PathVariable(name = "idCredit") String idCredit) {
    log.info("A credit card was deleted");
    return creditService.deleteCredit(idCredit)
        .map(bankCustomer -> ResponseEntity.ok().<Void>build())
        .defaultIfEmpty(ResponseEntity.notFound().build());

  }
}
