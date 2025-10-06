package dev.hyubuki.springbootconcept;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(
    scanBasePackages = "dev.hyubuki.springbootconcept"
)
public class SpringbootConceptApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootConceptApplication.class, args);
  }
}
b