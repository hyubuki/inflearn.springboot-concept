package dev.hyubuki.springbootconcept.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hyubuki.springbootconcept.exrate.ExRateData;
import java.math.BigDecimal;

public class ErExRataExtractor implements ExRateExtractor{

  @Override
  public BigDecimal extract(String response) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    ExRateData exRateData = mapper.readValue(response, ExRateData.class);
    return exRateData.rates().get("KRW");
  }
}
