package dev.hyubuki.springbootconcept.utils;

import java.util.UUID;

public class SnowFlake {

  public static String generate() {
    return UUID.randomUUID().toString().replace("-", "");
  }
}
