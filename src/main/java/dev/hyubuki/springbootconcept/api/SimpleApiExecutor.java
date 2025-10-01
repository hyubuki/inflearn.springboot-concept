package dev.hyubuki.springbootconcept.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.stream.Collectors;

public class SimpleApiExecutor implements ApiExecutor {

  @Override
  public String execute(URI uri) {
    String response;
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(uri.toURL().openStream()))) {
      response = reader.lines().collect(Collectors.joining());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return response;
  }
}
