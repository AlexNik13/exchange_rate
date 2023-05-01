package com.exchange.rate.rate.controller;

import com.exchange.rates.exchange_rate.rate.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchanges")
public class ExchangeRateController {

  private final ExchangeRateService exchangeRateService;

  @Autowired
  public ExchangeRateController(ExchangeRateService exchangeRateService) {
    this.exchangeRateService = exchangeRateService;
  }

  @GetMapping
  public ResponseEntity<Void> getAllAverage() {

    return ResponseEntity.ok()
        .build();
  }

  @GetMapping("/periods")
  public ResponseEntity<Void> getAllAveragePeriod(
      //TODO will bring dates
  ) {

    return ResponseEntity.ok()
        .build();
  }

}
