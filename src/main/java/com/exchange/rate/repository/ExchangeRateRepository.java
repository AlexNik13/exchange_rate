package com.exchange.rate.repository;

import java.time.ZonedDateTime;
import java.util.List;

import com.exchange.rate.dto.response.AverageExchangeRateDto;
import com.exchange.rate.model.ExchangeRate;

public interface ExchangeRateRepository {

  List<ExchangeRate> saveAll(List<ExchangeRate> entities);

  List<AverageExchangeRateDto> mathAllAverageRate();

  List<AverageExchangeRateDto> mathAllAverageRateDateBetween(
      ZonedDateTime from,
      ZonedDateTime to
  );

}
