package dev.hyubuki.springbootconcept.bean;

import dev.hyubuki.springbootconcept.payment.ExRateProvider;
import dev.hyubuki.springbootconcept.payment.SimpleExRateProvider;
import dev.hyubuki.springbootconcept.payment.service.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ObjectFactory {

  @Bean
  public PaymentService paymentService() {
    return new PaymentService(exRateProvider());
  }

  @Bean
  public ExRateProvider exRateProvider() {
//    return new WebApiExRatePaymentProvider();
    return new SimpleExRateProvider();
  }
}
