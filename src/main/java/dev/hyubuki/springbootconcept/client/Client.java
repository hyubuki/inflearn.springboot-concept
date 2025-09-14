package dev.hyubuki.springbootconcept.client;


import dev.hyubuki.springbootconcept.bean.ObjectFactory;
import dev.hyubuki.springbootconcept.payment.entity.Payment;
import dev.hyubuki.springbootconcept.payment.service.PaymentService;
import java.io.IOException;
import java.math.BigDecimal;

public class Client {

  public static void main(String[] args) throws IOException {
    ObjectFactory objectFactory = new ObjectFactory();
    PaymentService service = objectFactory.paymentService();
    Payment payment = service.prepare(100L, "USD", BigDecimal.valueOf(50.7));

    System.out.println(payment.toString());
  }
}
