package dev.hyubuki.springbootconcept.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SortTest {
  static class Sort {
    public List<String> sortByLength(List<String> list) {
      return list.stream().sorted((o1, o2) -> o1.toString().length() - o2.toString().length()).toList();
    }
  }
  Sort sort;

  @BeforeEach
  void init() {
    sort = new Sort();
  }

  @Test
  void sort() throws IOException {
    List<String> response = sort.sortByLength(List.of("banana", "apple", "orange"));
    assertThat(response).isEqualTo(List.of("apple", "banana", "orange"));
    System.out.println(this);
  }

  @Test
  void sort2() {
    // 全てのテストはbeforeEachで初期化されることと隔離されていることを確認
    System.out.println(this);
  }
}