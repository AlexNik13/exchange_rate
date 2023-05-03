package com.exchange.providers.utils;

import java.util.Currency;

public final class CurrencyUtils {


  private CurrencyUtils() {
  }

  public static String getCurrencySymbol(int currencyCode) {
    for(Currency currency : Currency.getAvailableCurrencies()) {
      if(currency.getNumericCode() == currencyCode) {
        return currency.getCurrencyCode();
      }
    }
    throw new IllegalArgumentException("Unkown currency code: " + currencyCode);
  }

}