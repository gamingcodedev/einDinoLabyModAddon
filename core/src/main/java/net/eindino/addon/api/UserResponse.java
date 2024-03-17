package net.eindino.addon.api;

public class UserResponse {

  private int id;
  private String name;
  private int coins;
  private int gold;
  private String locale;

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

  public String getName() {
    return name;
  }
}
