package com.exchange.providers.factory;

import com.exchange.providers.api.CurrencyExchangeProvider;
import com.exchange.rate.model.Exchanger;

public interface CurrencyExchangeProviderFactory {

  CurrencyExchangeProvider getProvider(Exchanger type);

}
