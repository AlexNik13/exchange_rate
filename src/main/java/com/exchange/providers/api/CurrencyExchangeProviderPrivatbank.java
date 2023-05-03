package com.exchange.providers.api;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import com.exchange.providers.exception.CurrencyExchangeProviderException;
import com.exchange.rate.dto.ExchangeRateCreateDto;
import com.exchange.rate.model.Exchanger;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyExchangeProviderPrivatbank implements CurrencyExchangeProvider {

  private static final String URL_API = "https://api.privatbank.ua/p24api/pubinfo";
  private static final Exchanger EXCHANGER = Exchanger.PRIVATBANK;

  private final RestTemplate restTemplate;

  public CurrencyExchangeProviderPrivatbank(
      RestTemplate restTemplate
  ) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List<ExchangeRateCreateDto> getExchangeRate() {
    PrivatbankCurrencyRate[] rates = restTemplate.getForObject(URL_API,
        PrivatbankCurrencyRate[].class);

    if (rates == null) {
      throw new CurrencyExchangeProviderException(EXCHANGER);
    }

    return Arrays.stream(rates)
        .map(this::mapToExchangeRateCreateDto)
        .toList();
  }

  private ExchangeRateCreateDto mapToExchangeRateCreateDto(
      PrivatbankCurrencyRate privatbankCurrencyRate) {
    ExchangeRateCreateDto dto = new ExchangeRateCreateDto();

    dto.setCurrency(privatbankCurrencyRate.getBase_ccy());
    dto.setExchanger(EXCHANGER);
    dto.setRateBuy(privatbankCurrencyRate.getBuy());
    dto.setRateSell(privatbankCurrencyRate.getSale());

    final ZonedDateTime date = ZonedDateTime.now(Clock.systemUTC());

    dto.setDate(date);

    return dto;
  }

  @Getter
  @Setter
  static class PrivatbankCurrencyRate {

    private String ccy;
    private String base_ccy;
    private BigDecimal buy;
    private BigDecimal sale;

  }

}
