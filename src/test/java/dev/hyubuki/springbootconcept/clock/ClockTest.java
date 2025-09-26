package dev.hyubuki.springbootconcept.clock;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;

public class ClockTest {
  @Test
  void testClock() {
    Clock dynamicClock = Clock.systemDefaultZone();
    LocalDateTime now1 = LocalDateTime.now(dynamicClock);
    LocalDateTime now2 = LocalDateTime.now(dynamicClock);

    assertThat(now1).isBefore(now2);

    Clock clock = Clock.fixed(Instant.now().plus(3L, ChronoUnit.MINUTES), ZoneId.systemDefault());
    LocalDateTime fnow = LocalDateTime.now(clock);
    LocalDateTime fnow2 = LocalDateTime.now(clock);

    assertThat(fnow).isEqualTo(fnow2);
  }
}
