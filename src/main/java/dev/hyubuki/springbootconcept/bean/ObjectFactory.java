package dev.hyubuki.springbootconcept.bean;

import dev.hyubuki.springbootconcept.payment.ExRateProvider;
import dev.hyubuki.springbootconcept.payment.SimpleExRateProvider;
import dev.hyubuki.springbootconcept.payment.WebApiExRatePaymentProvider;
import dev.hyubuki.springbootconcept.payment.service.PaymentService;

public class ObjectFactory {

  public PaymentService paymentService() {
    return new PaymentService(exRateProvider());
  }

  public ExRateProvider exRateProvider() {
    return new WebApiExRatePaymentProvider();
  }
}
