package net.eindino.addon.api;

public class UserResponse {

  private String name;
  private int coins;
  private int gold;
  private String locale;

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public String getLocale() {
    return locale;
  }

  public void setCoins(int coins) {
    this.coins = coins;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setGold(int gold) {
    this.gold = gold;
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
