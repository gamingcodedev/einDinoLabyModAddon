package net.eindino.addon.user;

import java.util.HashMap;
import java.util.Map;

public class StaffStatsCache {

  private static final Map<String, Integer> STATS_MAP = new HashMap<>();

  public static Integer getValue(String key) {
    return STATS_MAP.getOrDefault(key, 0);
  }

  public static void apply(String key, Integer value) {
    STATS_MAP.put(key, value);
  }

}
