package dev.hyubuki.springbootconcept.client;


import dev.hyubuki.springbootconcept.bean.ObjectFactory;
import dev.hyubuki.springbootconcept.payment.service.PaymentService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

  public static void main(String[] args) throws IOException, InterruptedException {
    BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
    PaymentService service = beanFactory.getBean(PaymentService.class);

    System.out.println(service.prepare(100L, "USD", BigDecimal.valueOf(50.7)).toString());
    System.out.println("-----------------------------------");
    System.out.println(service.prepare(100L, "USD", BigDecimal.valueOf(50.7)).toString());
    System.out.println("-----------------------------------");
    TimeUnit.SECONDS.sleep(1);
    System.out.println(service.prepare(100L, "USD", BigDecimal.valueOf(50.7)).toString());

  }
}
