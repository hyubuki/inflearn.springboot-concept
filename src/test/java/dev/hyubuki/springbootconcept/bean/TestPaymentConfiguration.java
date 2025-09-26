package dev.hyubuki.springbootconcept.bean;

import dev.hyubuki.springbootconcept.exrate.ExRateProviderStub;
import dev.hyubuki.springbootconcept.payment.ExRateProvider;
import dev.hyubuki.springbootconcept.payment.service.PaymentService;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestPaymentConfiguration {

  @Bean
  public ExRateProviderStub exRateProvider() {
    return new ExRateProviderStub(BigDecimal.valueOf(500));
  }

  @Bean
  public PaymentService paymentService(ExRateProvider exRateProvider, Clock clock) {
    return new PaymentService(exRateProvider, clock);
  }

  @Bean
  public Clock clock() {
    return Clock.fixed(Instant.now(), ZoneId.systemDefault());
  }
}