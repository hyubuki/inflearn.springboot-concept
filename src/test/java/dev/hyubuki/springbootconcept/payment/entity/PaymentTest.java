package dev.hyubuki.springbootconcept.payment.entity;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;

class PaymentTest {

  @Test
  void createPrepared() {
    Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    Payment payment = Payment.createPrepared(1L,"USD", BigDecimal.TEN, valueOf(1_000), LocalDateTime.now(clock));

    assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));
    assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
  }

  @Test
  void isValidTime() {
    Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    Payment payment = Payment.createPrepared(1L,"USD", BigDecimal.TEN, valueOf(1_000), LocalDateTime.now(clock));

    assertThat(payment.isValidTime(clock)).isTrue();
  }
}