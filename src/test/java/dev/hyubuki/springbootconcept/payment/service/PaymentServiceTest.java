package dev.hyubuki.springbootconcept.payment.service;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import dev.hyubuki.springbootconcept.exrate.ExRateProviderStub;
import dev.hyubuki.springbootconcept.payment.entity.Payment;
import java.io.IOException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class PaymentServiceTest {

  @Test
  void prepare() throws IOException {
    getPayment(valueOf(500), valueOf(5_000));
    getPayment(valueOf(110), valueOf(1_100));


//    // 支払い有効期限を確認
//    assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//    assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
  }

  private static void getPayment(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
    // 外部のAPIを利用して制御できない。したがって、StubーClassを利用する。
    PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));

    Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    // 両替え情報を確認
    assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

    // 両替え後の金額を確認
    assertThat(convertedAmount).isEqualTo(payment.getConvertedAmount());
    assertThat(payment.getConvertedAmount()).isEqualTo(exRate.multiply(BigDecimal.TEN));
  }
}
