package net.eindino.addon.api;

public class UserResponse {

  private int id;
  private String name;
  private int coins;
  private int gold;
  private String locale;
  private int access_level;
  private String color;

  public int getAccess_level() {
    return access_level;
  }

  public String getColor() {
    return color;
  }

  public int getId() {
    return id;
  }

  public String getLocale() {
    return locale;
  }

  public int getCoins() {
    return coins;
  }

  public int getGold() {
    return gold;
  }

  public String getRankName() {
    return name;
  }
}
