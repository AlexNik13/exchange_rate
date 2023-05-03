package com.exchange.rate.service;

import java.time.ZonedDateTime;
import java.util.List;

import com.exchange.rate.dto.ExchangeRateCreateDto;
import com.exchange.rate.dto.response.AverageExchangeRateDto;

public interface ExchangeRateService {

  void create(List<ExchangeRateCreateDto> dtos);

  List<AverageExchangeRateDto> mathAllAverageRate();

  List<AverageExchangeRateDto> mathAllAverageRateDateBetween(
      ZonedDateTime from,
      ZonedDateTime to
  );

}
