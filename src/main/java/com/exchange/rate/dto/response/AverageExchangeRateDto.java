package com.exchange.rate.dto.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AverageExchangeRateDto {

  private String currency;
  private BigDecimal averageRateBuy;
  private BigDecimal averageRateSell;

  public AverageExchangeRateDto(
      String currency,
      Double averageRateBuy,
      Double averageRateSell
  ) {
    this.currency = currency;
    this.averageRateBuy = averageRateBuy != null ? BigDecimal.valueOf(averageRateBuy) : null;
    this.averageRateSell = averageRateSell != null ? BigDecimal.valueOf(averageRateSell) : null;
  }

}
