package dev.hyubuki.springbootconcept.exrate;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hyubuki.springbootconcept.api.ApiTemplate;
import dev.hyubuki.springbootconcept.payment.ExRateProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import org.apache.commons.lang3.exception.UncheckedException;

public class WebApiExRatePaymentProvider implements ExRateProvider {

  @Override
  public BigDecimal getExRate(String currency) {
    {
      ApiTemplate apiTemplate = new ApiTemplate();

      String url = "https://open.er-api.com/v6/latest/" + currency;
      // Strategy パターン　method injection - callback
      return apiTemplate.getExRate(url, (uri) -> {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

            try {
              try (HttpClient client = HttpClient.newBuilder().build()) {
                return client.send(request, BodyHandlers.ofString()).body();
              }
            } catch (InterruptedException | IOException e) {
              throw new RuntimeException(e);
            }
          }
          , (response) -> {
            ObjectMapper mapper = new ObjectMapper();
            ExRateData exRateData = mapper.readValue(response, ExRateData.class);
            return exRateData.rates().get("KRW");
          });
    }
  }
}