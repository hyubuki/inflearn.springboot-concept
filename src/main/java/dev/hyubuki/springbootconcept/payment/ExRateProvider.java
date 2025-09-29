package dev.hyubuki.springbootconcept.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public interface ExRateProvider {

  // CHANGE: 클래스 분리
  BigDecimal getExRate(String currency);
}
