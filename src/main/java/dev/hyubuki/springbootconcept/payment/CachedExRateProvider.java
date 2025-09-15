package dev.hyubuki.springbootconcept.payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
* Change: Decorate Pattern -> 캐쉬 기능
* */
public class CachedExRateProvider implements ExRateProvider{

  private final ExRateProvider target;

  private BigDecimal cachedExRate;
  private LocalDateTime cachedExpiryTime;

  public CachedExRateProvider(ExRateProvider target) {
    this.target = target;
  }

  @Override
  public BigDecimal getExRate(String currency) throws IOException {
    if(cachedExRate == null || cachedExpiryTime.isBefore(LocalDateTime.now())) {
      cachedExRate = target.getExRate(currency);
      cachedExpiryTime = LocalDateTime.now().plusSeconds(1);

      System.out.println("Fetched new exchange rate: " + cachedExRate);
    }
    return cachedExRate;

  }
}
