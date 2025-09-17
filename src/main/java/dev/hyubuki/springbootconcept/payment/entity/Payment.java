package dev.hyubuki.springbootconcept.payment.entity;

import dev.hyubuki.springbootconcept.payment.entity.vo.Currency;
import dev.hyubuki.springbootconcept.utils.SnowFlake;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Payment {

  private final String paymentId;
  private final Long orderId;
  private final Currency currency;
  private final BigDecimal  foreignCurrencyAmount;
  private final BigDecimal exRate;
  private final BigDecimal convertedAmount;
  private final LocalDateTime validUntil;

  public Payment(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exRate, BigDecimal convertedAmount, LocalDateTime validUntil) {
    this.paymentId = SnowFlake.generate();
    this.orderId = orderId;
    this.currency = Currency.from(currency);
    this.foreignCurrencyAmount = foreignCurrencyAmount;
    this.exRate = exRate;
    this.convertedAmount = convertedAmount;
    this.validUntil = validUntil;
  }

}
