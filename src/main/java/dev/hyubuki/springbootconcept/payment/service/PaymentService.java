package dev.hyubuki.springbootconcept.payment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hyubuki.springbootconcept.payment.dto.ExRateData;
import dev.hyubuki.springbootconcept.payment.entity.Payment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PaymentService {

  public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {

    // TODO: implement exchange rate logic
    URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

    String response = reader.lines().collect(Collectors.joining());
    reader.close();

    ObjectMapper mapper = new ObjectMapper();
    ExRateData exRateData = mapper.readValue(response, ExRateData.class);

    BigDecimal krwRate = exRateData.rates().get("KRW");

    // TODO: implement converted amount logic
    BigDecimal convertedAmount = foreignCurrencyAmount.multiply(krwRate);

    // TODO: implement valid until logic
    LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

    return new Payment(orderId, currency, foreignCurrencyAmount, krwRate, convertedAmount, validUntil);
  }

  public static void main(String[] args) throws IOException {
    PaymentService service = new PaymentService();
    Payment payment = service.prepare(100L, "USD", BigDecimal.valueOf(50.7));

    System.out.println(payment.toString());
  }
}
