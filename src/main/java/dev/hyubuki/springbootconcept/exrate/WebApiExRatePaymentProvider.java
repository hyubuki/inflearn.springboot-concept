package dev.hyubuki.springbootconcept.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hyubuki.api.ApiExecutor;
import dev.hyubuki.api.ExRateExtractor;
import dev.hyubuki.api.SimpleApiExecutor;
import dev.hyubuki.springbootconcept.payment.ExRateProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class WebApiExRatePaymentProvider implements ExRateProvider {

  @Override
  public BigDecimal getExRate(String currency) {
    {

      String url = "https://open.er-api.com/v6/latest/" + currency;
      // Strategy パターン　method injection - callback
      return runApiFor(url, new SimpleApiExecutor(), (response) -> {
        ObjectMapper mapper = new ObjectMapper();
        ExRateData exRateData = mapper.readValue(response, ExRateData.class);
        return exRateData.rates().get("KRW");
      });
    }
  }
  // テンプレート
  private static BigDecimal runApiFor(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
    URI uri;
    try {
      uri = new URI(url);
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }

    String response = apiExecutor.execute(uri);
    try {
      return exRateExtractor.extract(response);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}