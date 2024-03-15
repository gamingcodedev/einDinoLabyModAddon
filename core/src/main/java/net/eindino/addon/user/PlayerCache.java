package net.eindino.addon.user;

import net.eindino.addon.api.UserResponse;

public class PlayerCache {

  private static UserResponse userResponse;

  public static void createOrUpdate(UserResponse userResponse) {
    PlayerCache.userResponse = userResponse;
  }

  public static UserResponse getUserResponse() {
    return userResponse;
  }
}
