package dev.hyubuki.springbootconcept.payment.service;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import dev.hyubuki.springbootconcept.bean.TestPaymentConfiguration;
import dev.hyubuki.springbootconcept.exrate.ExRateProviderStub;
import dev.hyubuki.springbootconcept.payment.entity.Payment;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// SpringExtensionを利用して、SpringのDIコンテナを利用できるようにする。
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestPaymentConfiguration.class})
public class PaymentServiceTest {

  @Autowired
  private PaymentService paymentService;

  @Autowired
  private ExRateProviderStub exRateProviderStub;

  @Autowired
  private Clock clock;

  @Test
  void convertedAmount() throws IOException {
    testAmount(valueOf(500), valueOf(5_000), this.clock);

    exRateProviderStub.setExRate(valueOf(110));
    testAmount(valueOf(110), valueOf(1_100), this.clock);
  }

  @Test
  void validUntil() throws IOException {
   Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

   LocalDateTime now = LocalDateTime.now(clock);
    assertThat(payment.getValidUntil()).isEqualTo(now.plusMinutes(30));
  }

  private void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) throws IOException {
    // 外部のAPIを利用して制御できない。したがって、StubーClassを利用する。
    // PaymentService paymentService = beanFactory.getBean(PaymentService.class);

    Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    // 両替え情報を確認
    assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

    // 両替え後の金額を確認
    assertThat(convertedAmount).isEqualTo(payment.getConvertedAmount());
    assertThat(payment.getConvertedAmount()).isEqualTo(exRate.multiply(BigDecimal.TEN));
  }
}
