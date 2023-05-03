package com.exchange.rate.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.exchange.rate.model.Exchanger;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRateCreateDto {

  private String currency;

  private BigDecimal rateSell;

  private BigDecimal rateBuy;

  private Exchanger exchanger;

  private ZonedDateTime date;

}
