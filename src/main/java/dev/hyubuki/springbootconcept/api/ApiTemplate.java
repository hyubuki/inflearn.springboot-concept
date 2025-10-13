package dev.hyubuki.springbootconcept.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiTemplate {

  private final ApiExecutor defaultApiExecutor;
  private final ExRateExtractor defaultExRateExtractor;

  public ApiTemplate() {
    this.defaultApiExecutor = new HttpClientApiExecutor();
    this.defaultExRateExtractor = new ErExRataExtractor();
  }

//  ClassLoader
  // beanを生成する時、DIを利用して注入
  public ApiTemplate(ApiExecutor defaultApiExecutor, ExRateExtractor defaultExRateExtractor) {
    this.defaultApiExecutor = defaultApiExecutor;
    this.defaultExRateExtractor = defaultExRateExtractor;
  }

  // デフォルトコールバック
  public BigDecimal getExRate(String url) {
    return getExRate(url, this.defaultApiExecutor, this.defaultExRateExtractor);
  }

  // テンプレート
  public BigDecimal getExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
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