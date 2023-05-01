package com.exchange.rate.rate.utils;

import java.util.Currency;

public final class CurrencyUtils {


  private CurrencyUtils() {
  }

  public static int getInstance(String code){
    return Currency.getInstance(code);
  }


}
