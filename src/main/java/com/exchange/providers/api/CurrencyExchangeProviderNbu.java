package com.exchange.providers.api;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import com.exchange.providers.exception.CurrencyExchangeProviderException;
import com.exchange.rate.dto.ExchangeRateCreateDto;
import com.exchange.rate.model.Exchanger;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyExchangeProviderNbu implements CurrencyExchangeProvider {

  private static final String URL_API = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchangenew?json";
  private static final Exchanger EXCHANGER = Exchanger.NBU;

  private final RestTemplate restTemplate;

  public CurrencyExchangeProviderNbu(
      RestTemplate restTemplate
  ) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List<ExchangeRateCreateDto> getExchangeRate() {
    NbuCurrencyRate[] rates = restTemplate.getForObject(URL_API, NbuCurrencyRate[].class);

    if (rates == null) {
      throw new CurrencyExchangeProviderException(EXCHANGER);
    }

    return Arrays.stream(rates)
        .map(this::mapToExchangeRateCreateDto)
        .toList();
  }

  private ExchangeRateCreateDto mapToExchangeRateCreateDto(NbuCurrencyRate nbuCurrencyRate) {
    ExchangeRateCreateDto dto = new ExchangeRateCreateDto();

    dto.setCurrency(nbuCurrencyRate.cc);
    dto.setExchanger(EXCHANGER);
    dto.setRateBuy(nbuCurrencyRate.getRate());
    dto.setRateSell(null);

    final ZonedDateTime date = nbuCurrencyRate.exchangedate
        .atStartOfDay(Clock.systemUTC().getZone());

    dto.setDate(date);

    return dto;
  }

  @Getter
  @Setter
  static class NbuCurrencyRate {

    private int r030;
    private String txt;
    private BigDecimal rate;
    private String cc;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate exchangedate;

  }

}
