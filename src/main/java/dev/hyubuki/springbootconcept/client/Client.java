package dev.hyubuki.springbootconcept.client;

import dev.hyubuki.springbootconcept.payment.entity.Payment;
import dev.hyubuki.springbootconcept.payment.service.PaymentService;
import dev.hyubuki.springbootconcept.payment.service.SimpleExRatePaymentService;
import java.io.IOException;
import java.math.BigDecimal;

public class Client {

  public static void main(String[] args) throws IOException {
    PaymentService service = new SimpleExRatePaymentService();
    Payment payment = service.prepare(100L, "USD", BigDecimal.valueOf(50.7));

    System.out.println(payment.toString());
  }
}
