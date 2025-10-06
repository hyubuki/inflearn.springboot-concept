package dev.hyubuki.springbootconcept.exrate;

import dev.hyubuki.springbootconcept.api.ApiTemplate;
import dev.hyubuki.springbootconcept.payment.ExRateProvider;
import java.math.BigDecimal;

public class WebApiExRatePaymentProvider implements ExRateProvider {

  private final ApiTemplate apiTemplate;

  public WebApiExRatePaymentProvider(ApiTemplate apiTemplate) {
    this.apiTemplate = apiTemplate;
  }

  @Override
  public BigDecimal getExRate(String currency) {
    {
      ApiTemplate apiTemplate = new ApiTemplate();

      String url = "https://open.er-api.com/v6/latest/" + currency;
      // Strategy パターン　method injection - callback
      return apiTemplate.getExRate(url);
    }
  }
}