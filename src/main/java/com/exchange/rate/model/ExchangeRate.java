package com.exchange.rate.model;

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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exchange_rates")
@Getter
@Setter
public class ExchangeRate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "currency", nullable = false)
  private String currency;
  @Column(name = "rate_sell")
  private BigDecimal rateSell;

  @Column(name = "rate_buy", nullable = false)
  private BigDecimal rateBuy;

  @Column(name = "exchanger", nullable = false)
  @Enumerated(EnumType.STRING)
  private Exchanger exchanger;

  @Column(name = "date", nullable = false)
  private ZonedDateTime date;

  @Column(name = "created_at", nullable = false)
  private ZonedDateTime createdAt;

}

