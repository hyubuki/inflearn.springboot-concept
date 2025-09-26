package dev.hyubuki.springbootconcept.bean;

import dev.hyubuki.springbootconcept.exrate.CachedExRateProvider;
import dev.hyubuki.springbootconcept.exrate.WebApiExRatePaymentProvider;
import dev.hyubuki.springbootconcept.payment.ExRateProvider;
import dev.hyubuki.springbootconcept.payment.service.PaymentService;
import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PaymentConfiguration {

  @Bean
  public PaymentService paymentService() {
    return new PaymentService(cachedExRateProvider(), clock());
  }

  @Bean
  public Clock clock() {
    return Clock.systemDefaultZone();
  }

  @Bean
  public ExRateProvider cachedExRateProvider() {
    return new CachedExRateProvider(exRateProvider());
  }

  @Bean
  public ExRateProvider exRateProvider() {
    return new WebApiExRatePaymentProvider();
//    return new SimpleExRateProvider();
  }
}
