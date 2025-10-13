package dev.hyubuki.springbootconcept;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = "dev.hyubuki.springbootconcept"
)
public class SpringbootConceptApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootConceptApplication.class, args);
  }
}