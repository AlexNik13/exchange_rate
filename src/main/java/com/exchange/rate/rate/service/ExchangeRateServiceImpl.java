package com.exchange.rate.rate.service;

import java.time.Clock;

import com.exchange.rates.exchange_rate.rate.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

  private final ExchangeRateRepository exchangeRateRepository;
  private final Clock clock;

  @Autowired
  public ExchangeRateServiceImpl(
      ExchangeRateRepository exchangeRateRepository,
      Clock clock
  ) {
    this.exchangeRateRepository = exchangeRateRepository;
    this.clock = clock;
  }

}
