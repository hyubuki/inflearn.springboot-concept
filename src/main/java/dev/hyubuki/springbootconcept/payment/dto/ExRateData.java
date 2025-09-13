package dev.hyubuki.springbootconcept.payment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.Map;

// JsonIgnoreProperties: JSON 변환 시 매핑되지 않는 필드를 무시하도록 설정
@JsonIgnoreProperties(ignoreUnknown = true)
public record ExRateData (
    String result,
    Map<String, BigDecimal> rates
){

}
