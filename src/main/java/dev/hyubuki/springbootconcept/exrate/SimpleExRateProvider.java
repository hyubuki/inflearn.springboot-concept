package dev.hyubuki.springbootconcept.exrate;

import dev.hyubuki.springbootconcept.payment.ExRateProvider;
import java.math.BigDecimal;

public class SimpleExRateProvider implements ExRateProvider {

  @Override
  public BigDecimal getExRate(String currency) {
    if(currency.equals("USD")){
      return BigDecimal.valueOf(1000);
    }

    throw new IllegalArgumentException("Invalid currency, only USD is supported");
  }
}
