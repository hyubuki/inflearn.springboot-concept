package dev.hyubuki.springbootconcept.client;


import dev.hyubuki.springbootconcept.bean.PaymentConfiguration;
import dev.hyubuki.springbootconcept.payment.service.PaymentService;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Client implements ApplicationRunner {

  @Override
  public void run(ApplicationArguments args) throws Exception {
    BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfiguration.class);
    PaymentService service = beanFactory.getBean(PaymentService.class);

    System.out.println(service.prepare(100L, "USD", BigDecimal.valueOf(50.7)).toString());
    System.out.println("-----------------------------------");
    System.out.println(service.prepare(100L, "USD", BigDecimal.valueOf(50.7)).toString());
    System.out.println("-----------------------------------");
    TimeUnit.SECONDS.sleep(1);
    System.out.println(service.prepare(100L, "USD", BigDecimal.valueOf(50.7)).toString());
  }
}
