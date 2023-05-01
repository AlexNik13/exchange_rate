package com.exchange.rate.rate.repository;

import com.exchange.rates.exchange_rate.rate.repository.spring.ExchangeRateRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExchangeRateRepositoryImpl implements ExchangeRateRepository {

  private final ExchangeRateRepositorySpring delegate;

  @Autowired
  public ExchangeRateRepositoryImpl(ExchangeRateRepositorySpring delegate) {
    this.delegate = delegate;
  }

}
