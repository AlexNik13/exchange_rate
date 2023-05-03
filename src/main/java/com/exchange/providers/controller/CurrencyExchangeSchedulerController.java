package com.exchange.providers.controller;

import java.util.List;

import com.exchange.providers.api.CurrencyExchangeProvider;
import com.exchange.providers.factory.CurrencyExchangeProviderFactory;
import com.exchange.rate.dto.EventExchangeRateAllCreate;
import com.exchange.rate.dto.ExchangeRateCreateDto;
import com.exchange.rate.model.Exchanger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CurrencyExchangeSchedulerController {

  private final ApplicationEventPublisher applicationEventPublisher;
  private final CurrencyExchangeProviderFactory currencyExchangeProviderFactory;

  @Autowired
  public CurrencyExchangeSchedulerController(
      ApplicationEventPublisher applicationEventPublisher,
      CurrencyExchangeProviderFactory currencyExchangeProviderFactory
  ) {
    this.applicationEventPublisher = applicationEventPublisher;
    this.currencyExchangeProviderFactory = currencyExchangeProviderFactory;
  }

  @Scheduled(cron = "0 * * * * ?")
  public void fetchAndEventPublisherExchangeRates() {
    for (Exchanger exchanger : Exchanger.values()) {
      try {
        CurrencyExchangeProvider currencyExchangeProvider
            = currencyExchangeProviderFactory.getProvider(exchanger);

        List<ExchangeRateCreateDto> exchangeRateCreateDtos
            = currencyExchangeProvider.getExchangeRate();

        EventExchangeRateAllCreate event
            = new EventExchangeRateAllCreate(exchangeRateCreateDtos);

        applicationEventPublisher.publishEvent(event);
      } catch (Exception e) {
        String message = String.format("API error %s", exchanger);
        log.error(message);
      }
    }
  }

}
