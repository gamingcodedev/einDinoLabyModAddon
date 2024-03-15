package net.eindino.addon.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;

public final class NumberConventions {
  private static final Format GERMAN_DECIMAL_FORMAT = NumberFormat.getNumberInstance(Locale.GERMAN);
  private static final Format ENGLISH_DECIMAL_FORMAT = NumberFormat.getNumberInstance(Locale.ENGLISH);
  public static final int MEGABYTES = 1024 * 1024;

  private NumberConventions() {}

  public static double round(double value, int decimals) {
    // use BigDecimal.valueOf
    return new BigDecimal(value).setScale(decimals, RoundingMode.HALF_UP).doubleValue();
  }

  public static double round(int first, int second, int decimals) {
    if (first == 0) {
      return 0;
    }
    if (second == 0) {
      return first;
    }
    return round((double) first / (double) second, decimals);
  }

  public static String format(int number, String locale) {
    return locale.equals("de") ? GERMAN_DECIMAL_FORMAT.format(number) : ENGLISH_DECIMAL_FORMAT.format(number);
  }

  public static String format(long number, String locale) {
    return locale.equals("de") ? GERMAN_DECIMAL_FORMAT.format(number) : ENGLISH_DECIMAL_FORMAT.format(number);
  }
}
