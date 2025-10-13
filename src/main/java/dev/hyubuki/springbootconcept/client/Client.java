package dev.hyubuki.springbootconcept.client;


import dev.hyubuki.springbootconcept.payment.service.PaymentService;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class Client implements ApplicationRunner {

  private final PaymentService service;

  public Client(PaymentService service) {
    this.service = service;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println(service.prepare(100L, "USD", BigDecimal.valueOf(50.7)).toString());
    System.out.println("-----------------------------------");
    System.out.println(service.prepare(100L, "USD", BigDecimal.valueOf(50.7)).toString());
    System.out.println("-----------------------------------");
    TimeUnit.SECONDS.sleep(1);
    System.out.println(service.prepare(100L, "USD", BigDecimal.valueOf(50.7)).toString());
  }
}
