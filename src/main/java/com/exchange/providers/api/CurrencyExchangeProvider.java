package com.exchange.providers.api;

import java.util.List;

import com.exchange.rate.dto.ExchangeRateCreateDto;

public interface CurrencyExchangeProvider {

  List<ExchangeRateCreateDto> getExchangeRate();

}
