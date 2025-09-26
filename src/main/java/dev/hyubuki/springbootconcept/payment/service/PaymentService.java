package dev.hyubuki.springbootconcept.payment.service;

import dev.hyubuki.springbootconcept.payment.ExRateProvider;
import dev.hyubuki.springbootconcept.payment.entity.Payment;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

  // CHANGE: 클라이언트에서 구현체만 변경하면 되도록 작업
  private final ExRateProvider exRateProvider;
  private final Clock clock;

  public PaymentService(ExRateProvider exRateProvider, Clock clock) {
    System.out.println(exRateProvider.getClass().getName());
    this.exRateProvider = exRateProvider;
     this.clock = clock;
  }

  public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {

    BigDecimal krwRate = exRateProvider.getExRate(currency);
    BigDecimal convertedAmount = foreignCurrencyAmount.multiply(krwRate);
    LocalDateTime validUntil = LocalDateTime.now(clock).plusMinutes(30);

    return new Payment(orderId, currency, foreignCurrencyAmount, krwRate, convertedAmount, validUntil);
  }
}