package com.exchange.rate.service;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;

import com.exchange.rate.dto.ExchangeRateCreateDto;
import com.exchange.rate.dto.response.AverageExchangeRateDto;
import com.exchange.rate.model.ExchangeRate;
import com.exchange.rate.repository.ExchangeRateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExchangeRateServiceImpl implements ExchangeRateService {

  private final ExchangeRateRepository exchangeRateRepository;
  private final Clock clock;

  @Autowired
  public ExchangeRateServiceImpl(
      ExchangeRateRepository exchangeRateRepository,
      Clock clock
  ) {
    this.exchangeRateRepository = exchangeRateRepository;
    this.clock = clock;
  }

  @Override
  public void create(List<ExchangeRateCreateDto> dtos) {
    log.debug("ExchangeRate create");
    final ZonedDateTime createdAt = ZonedDateTime.now(clock);
    final List<ExchangeRate> entities = dtos.stream()
        .map(dto -> Assembler.assembler(dto, createdAt))
        .toList();

    exchangeRateRepository.saveAll(entities);
    log.info("ExchangeRate has be create");
  }

  @Override
  public List<AverageExchangeRateDto> mathAllAverageRate() {
    return exchangeRateRepository.mathAllAverageRate();
  }

  @Override
  public List<AverageExchangeRateDto> mathAllAverageRateDateBetween(
      ZonedDateTime from,
      ZonedDateTime to
  ) {
    return exchangeRateRepository.mathAllAverageRateDateBetween(from, to);
  }


  private static final class Assembler {

    private static ExchangeRate assembler(
        ExchangeRateCreateDto dto,
        ZonedDateTime createdAt
    ) {
      ExchangeRate entity = new ExchangeRate();
      entity.setCurrency(dto.getCurrency());
      entity.setExchanger(dto.getExchanger());
      entity.setRateBuy(dto.getRateBuy());
      entity.setRateSell(dto.getRateSell());
      entity.setDate(dto.getDate());
      entity.setCreatedAt(createdAt);
      return entity;
    }

  }

}
