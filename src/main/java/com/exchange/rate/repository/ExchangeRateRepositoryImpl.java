package com.exchange.rate.repository;

import java.time.ZonedDateTime;
import java.util.List;

import com.exchange.rate.dto.response.AverageExchangeRateDto;
import com.exchange.rate.model.ExchangeRate;
import com.exchange.rate.repository.spring.ExchangeRateRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExchangeRateRepositoryImpl implements ExchangeRateRepository {

  private final ExchangeRateRepositorySpring delegate;

  @Autowired
  public ExchangeRateRepositoryImpl(ExchangeRateRepositorySpring delegate) {
    this.delegate = delegate;
  }

  @Override
  public List<ExchangeRate> saveAll(List<ExchangeRate> entities) {
    return delegate.saveAll(entities);
  }

  @Override
  public List<AverageExchangeRateDto> mathAllAverageRate() {
    return delegate.mathAllAverageRate();
  }

  @Override
  public List<AverageExchangeRateDto> mathAllAverageRateDateBetween(
      ZonedDateTime from,
      ZonedDateTime to
  ) {
    return delegate.mathAllAverageRateDateBetween(from, to);
  }

}
