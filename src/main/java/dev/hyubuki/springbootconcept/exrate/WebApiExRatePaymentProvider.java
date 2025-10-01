package dev.hyubuki.springbootconcept.exrate;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hyubuki.springbootconcept.api.ApiTemplate;
import dev.hyubuki.springbootconcept.api.SimpleApiExecutor;
import dev.hyubuki.springbootconcept.payment.ExRateProvider;
import java.math.BigDecimal;

public class WebApiExRatePaymentProvider implements ExRateProvider {

  @Override
  public BigDecimal getExRate(String currency) {
    {
      ApiTemplate apiTemplate = new ApiTemplate();

      String url = "https://open.er-api.com/v6/latest/" + currency;
      // Strategy パターン　method injection - callback
      return apiTemplate.getExRate(url, new SimpleApiExecutor(), (response) -> {
        ObjectMapper mapper = new ObjectMapper();
        ExRateData exRateData = mapper.readValue(response, ExRateData.class);
        return exRateData.rates().get("KRW");
      });
    }
  }
}