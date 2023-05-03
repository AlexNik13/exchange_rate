package com.exchange.providers.factory;

import com.exchange.providers.api.CurrencyExchangeProvider;
import com.exchange.providers.api.CurrencyExchangeProviderMonobank;
import com.exchange.providers.api.CurrencyExchangeProviderNbu;
import com.exchange.providers.api.CurrencyExchangeProviderPrivatbank;
import com.exchange.rate.model.Exchanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyExchangeProviderFactoryImpl implements CurrencyExchangeProviderFactory {

  private final CurrencyExchangeProviderMonobank currencyExchangeProviderMonobank;
  private final CurrencyExchangeProviderNbu currencyExchangeProviderNbu;
  private final CurrencyExchangeProviderPrivatbank currencyExchangeProviderPrivatbank;

  @Autowired
  public CurrencyExchangeProviderFactoryImpl(
      CurrencyExchangeProviderMonobank currencyExchangeProviderMonobank,
      CurrencyExchangeProviderNbu currencyExchangeProviderNbu,
      CurrencyExchangeProviderPrivatbank currencyExchangeProviderPrivatbank
  ) {
    this.currencyExchangeProviderMonobank = currencyExchangeProviderMonobank;
    this.currencyExchangeProviderNbu = currencyExchangeProviderNbu;
    this.currencyExchangeProviderPrivatbank = currencyExchangeProviderPrivatbank;
  }

  @Override
  public CurrencyExchangeProvider getProvider(Exchanger type) {
    return switch (type) {
      case MONOBANK -> currencyExchangeProviderMonobank;
      case PRIVATBANK -> currencyExchangeProviderPrivatbank;
      case NBU -> currencyExchangeProviderNbu;
      default -> throw new IllegalArgumentException("Unknown currency exchange provider: " + type);
    };
  }

}
