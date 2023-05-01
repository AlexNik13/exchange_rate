package com.exchange.rate.rate.repository.spring;

import com.exchange.rates.exchange_rate.rate.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepositorySpring extends JpaRepository<ExchangeRate, Long> {

}
