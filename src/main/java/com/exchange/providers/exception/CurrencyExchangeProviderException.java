package com.exchange.providers.exception;

import com.exchange.application.exception.APIException;
import com.exchange.rate.model.Exchanger;

public class CurrencyExchangeProviderException extends APIException {

  public CurrencyExchangeProviderException(Exchanger exchanger) {
    super("exception.apiException.providers.failed", exchanger);
  }

}
