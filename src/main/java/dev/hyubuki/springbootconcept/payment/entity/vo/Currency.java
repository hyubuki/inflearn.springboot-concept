package dev.hyubuki.springbootconcept.payment.entity.vo;

import java.util.Arrays;
import java.util.Locale;

public enum Currency {
  USD("미국"),
  JPY("일본"),
  KRW("한국"),
  EUR("유럽");

  private String label;

  Currency(String label) {
    this.label = label;
  }

  public static Currency from(String codeOrLabel) {

    if(codeOrLabel == null || codeOrLabel.isBlank()) {
      throw new IllegalArgumentException("Currency label is null or blank");
    }

    return Arrays.stream(values())
        .filter(c -> c.label.equals(codeOrLabel) || c.name().equals(codeOrLabel.toUpperCase(Locale.ROOT)))
        .findFirst()
        .orElseThrow(null);
  }
}
