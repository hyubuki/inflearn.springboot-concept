package dev.hyubuki.springbootconcept.payment.service;

import dev.hyubuki.springbootconcept.payment.entity.Payment;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

abstract public class PaymentService {

  public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {

    BigDecimal krwRate = getExRate(currency);
    BigDecimal convertedAmount = foreignCurrencyAmount.multiply(krwRate);
    LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

    return new Payment(orderId, currency, foreignCurrencyAmount, krwRate, convertedAmount, validUntil);
  }

  // CHANGE: 관심사 분리 - 환율 조회 기능을 추상 메서드로 분리
  // 확장성 - 다양한 환율 조회 방법을 지원하기 위해 서브클래스에서 구현하도록 함
  abstract public BigDecimal getExRate(String currency) throws IOException;
}
