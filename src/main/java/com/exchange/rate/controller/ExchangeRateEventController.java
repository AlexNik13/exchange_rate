package com.exchange.rate.controller;

import java.util.List;

import com.exchange.rate.dto.EventExchangeRateAllCreate;
import com.exchange.rate.dto.ExchangeRateCreateDto;
import com.exchange.rate.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateEventController {

  private ExchangeRateService exchangeRateService;

  @Autowired
  public ExchangeRateEventController(
      ExchangeRateService exchangeRateService
  ) {
    this.exchangeRateService = exchangeRateService;
  }

  @EventListener
  public void create(EventExchangeRateAllCreate event) {
    final List<ExchangeRateCreateDto> exchangeRateCreateDtos = event.exchangeRateCreateDtos();
    exchangeRateService.create(exchangeRateCreateDtos);
  }

}
