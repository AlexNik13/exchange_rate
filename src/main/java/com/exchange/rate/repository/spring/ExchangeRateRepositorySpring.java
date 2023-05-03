package com.exchange.rate.repository.spring;

import java.time.ZonedDateTime;
import java.util.List;

import com.exchange.rate.dto.response.AverageExchangeRateDto;
import com.exchange.rate.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExchangeRateRepositorySpring extends JpaRepository<ExchangeRate, Long> {

  //This query if work
  @Query("""
      SELECT
             new com.exchange.rate.dto.response.AverageExchangeRateDto(
               e.currency,
               AVG(e.rateBuy),
               (SUM(CASE WHEN e.rateSell IS NOT NULL THEN e.rateSell ELSE 0 END) / NULLIF(CAST(COUNT(e.rateSell) AS double), 0))
             )
        FROM ExchangeRate e
       GROUP BY e.currency
      """)
  List<AverageExchangeRateDto> mathAllAverageRate();

  //This query is work
  @Query("""
      SELECT
             new com.exchange.rate.dto.response.AverageExchangeRateDto(
               e.currency,
               AVG(e.rateBuy),
               (SUM(CASE WHEN e.rateSell IS NOT NULL THEN e.rateSell ELSE 0 END) / NULLIF(CAST(COUNT(e.rateSell) AS double), 0))
             )
        FROM ExchangeRate e
       WHERE e.createdAt BETWEEN :from AND :to
       GROUP BY e.currency
      """)
  List<AverageExchangeRateDto> mathAllAverageRateDateBetween(
      ZonedDateTime from,
      ZonedDateTime to
  );

}
