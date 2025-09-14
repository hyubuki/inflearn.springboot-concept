package dev.hyubuki.springbootconcept.payment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hyubuki.springbootconcept.payment.dto.ExRateData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class WebApiExRatePaymentService extends PaymentService{

  @Override
  public BigDecimal getExRate(String currency) throws IOException {
    {
      URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String response = reader.lines().collect(Collectors.joining());
      reader.close();

      ObjectMapper mapper = new ObjectMapper();
      ExRateData exRateData = mapper.readValue(response, ExRateData.class);
      BigDecimal krwRate = exRateData.rates().get("KRW");
      return krwRate;
    }
  }
}
