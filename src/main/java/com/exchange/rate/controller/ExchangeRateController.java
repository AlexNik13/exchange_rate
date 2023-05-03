package com.exchange.rate.controller;

import java.time.ZonedDateTime;
import java.util.List;

import com.exchange.rate.dto.response.AverageExchangeRateDto;
import com.exchange.rate.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchanges")
public class ExchangeRateController {

  private final ExchangeRateService exchangeRateService;

  @Autowired
  public ExchangeRateController(ExchangeRateService exchangeRateService) {
    this.exchangeRateService = exchangeRateService;
  }

  @GetMapping
  public ResponseEntity<List<AverageExchangeRateDto>> getAllAverage() {
    List<AverageExchangeRateDto> response = exchangeRateService.mathAllAverageRate();
    return ResponseEntity.ok()
        .body(response);
  }

  @GetMapping("/periods")
  public ResponseEntity<List<AverageExchangeRateDto>> getAllAveragePeriod(
      @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) ZonedDateTime from,
      @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) ZonedDateTime to
  ) {
    List<AverageExchangeRateDto> response
        = exchangeRateService.mathAllAverageRateDateBetween(from, to);

    return ResponseEntity.ok()
        .body(response);
  }

}
