package com.exchange.rate.rate.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exchange_rates")
public class ExchangeRate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "rate_sell", nullable = false)
  private String rateSell;

  @Column(name = "rate_buy", nullable = false)
  private String rateBuy;

  @Column(name = "currency", nullable = false)
  private String currency;

  @Column(name = "rate", nullable = false)
  private BigDecimal rate;

  @Column(name = "exchanger", nullable = false)
  @Enumerated(EnumType.STRING)
  private Exchanger exchanger;

  @Column(name = "date", nullable = false)
  private ZonedDateTime createdAt;

}

