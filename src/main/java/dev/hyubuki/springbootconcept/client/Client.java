package dev.hyubuki.springbootconcept.client;


import dev.hyubuki.springbootconcept.bean.ObjectFactory;
import dev.hyubuki.springbootconcept.payment.entity.Payment;
import dev.hyubuki.springbootconcept.payment.service.PaymentService;
import java.io.IOException;
import java.math.BigDecimal;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

  public static void main(String[] args) throws IOException {
    BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
    PaymentService service = beanFactory.getBean(PaymentService.class);

    Payment payment = service.prepare(100L, "USD", BigDecimal.valueOf(50.7));
    System.out.println(payment.toString());
  }
}
