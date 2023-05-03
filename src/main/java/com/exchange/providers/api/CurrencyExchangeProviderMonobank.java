package com.exchange.providers.api;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import com.exchange.providers.exception.CurrencyExchangeProviderException;
import com.exchange.providers.utils.CurrencyUtils;
import com.exchange.rate.dto.ExchangeRateCreateDto;
import com.exchange.rate.model.Exchanger;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyExchangeProviderMonobank implements CurrencyExchangeProvider {

  private static final String URL_API = "https://api.monobank.ua/bank/currency";
  private static final int CURRENCY_CODE_UAH = 980;
  private static final int CURRENCY_CODE_USD = 840;

  private static final Exchanger EXCHANGER = Exchanger.MONOBANK;
  private BigDecimal rateBuyUSDtoUAH;
  private BigDecimal rateSellUSDtoUAH;

  private final RestTemplate restTemplate;

  @Autowired
  public CurrencyExchangeProviderMonobank(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List<ExchangeRateCreateDto> getExchangeRate() {
    MonobankCurrencyRate[] rates = restTemplate.getForObject(URL_API, MonobankCurrencyRate[].class);

    if (rates == null) {
      throw new CurrencyExchangeProviderException(EXCHANGER);
    }

    appendStaticExchangeRateUSD(rates);

    return Arrays.stream(rates)
        .map(this::mapToExchangeRateCreateDto)
        .toList();
  }

  private void appendStaticExchangeRateUSD(MonobankCurrencyRate[] rates) {
    for (MonobankCurrencyRate rate : rates) {
      if (rate.currencyCodeA == CURRENCY_CODE_USD && rate.currencyCodeB == CURRENCY_CODE_UAH) {
        this.rateBuyUSDtoUAH = rate.rateBuy;
        this.rateSellUSDtoUAH = rate.rateSell;
        break;
      }
    }
  }

  private ExchangeRateCreateDto mapToExchangeRateCreateDto(MonobankCurrencyRate monobankRate) {
    ExchangeRateCreateDto dto = new ExchangeRateCreateDto();
    int currencyCode = monobankRate.currencyCodeA != CURRENCY_CODE_UAH
        ? monobankRate.currencyCodeA
        : monobankRate.currencyCodeB;

    final String currency = CurrencyUtils.getCurrencySymbol(currencyCode);
    dto.setCurrency(currency);
    dto.setExchanger(EXCHANGER);
    dto.setRateBuy(monobankRate.getRateBuy());
    dto.setRateSell(monobankRate.getRateSell());

    final BigDecimal rateCross = monobankRate.rateCross;
    if (rateCross.compareTo(BigDecimal.ZERO) != 0) {
      final BigDecimal rateSell = rateCross.divide(rateBuyUSDtoUAH, RoundingMode.HALF_EVEN)
          .multiply(rateSellUSDtoUAH);

      dto.setRateSell(rateSell);
      dto.setRateBuy(rateCross);
    }

    final ZonedDateTime date = monobankRate.date
        .atZone(Clock.systemUTC().getZone());
    dto.setDate(date);

    return dto;
  }


  @Getter
  @Setter
  static class MonobankCurrencyRate {

    private int currencyCodeA;
    private int currencyCodeB;
    private BigDecimal rateBuy;
    private BigDecimal rateSell;
    private BigDecimal rateCross;
    private Instant date;

  }

}
