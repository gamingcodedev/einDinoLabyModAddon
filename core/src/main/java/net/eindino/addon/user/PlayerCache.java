package net.eindino.addon.user;

import net.eindino.addon.api.UserResponse;
import net.eindino.addon.einDinoAddon;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;

public class PlayerCache {

  private static UserResponse userResponse;

  public static boolean isPresent() {
    return PlayerCache.userResponse != null;
  }

  public static void invalidate() {
    PlayerCache.userResponse = null;

    Laby.labyAPI().hudWidgetRegistry().unregister("supports");
    Laby.labyAPI().hudWidgetRegistry().unregister("warns");
    Laby.labyAPI().hudWidgetRegistry().unregister("reports");
  }

  public static boolean isStaff() {
    if (PlayerCache.isPresent()) {
      return getUserResponse().getAccess_level() >= 500;
    }
    return false;
  }

  public static void createOrUpdate(UserResponse userResponse, einDinoAddon addon) {

    if (PlayerCache.userResponse == null) {
      insert(userResponse, addon);

      addon.pushNotification(
          Component.translatable("eindino.notification.success.title"),
          Component.translatable("eindino.notification.success.insert"));
      return;
    }

    if (isSame(userResponse)) return;

    addon.pushNotification(
        Component.translatable("eindino.notification.success.title"),
        Component.translatable("eindino.notification.success.update"));

    insert(userResponse, addon);
  }

  private static boolean isSame(UserResponse userResponse) {
    return userResponse.getCoins() == getUserResponse().getCoins() && userResponse.getGold() == getUserResponse().getGold();
  }

  private static void insert(UserResponse userResponse, einDinoAddon addon) {
    PlayerCache.userResponse = userResponse;

    if (isStaff()) {
      addon.fetchStaffStats();
    }
   }

  public static UserResponse getUserResponse() {
    return userResponse;
  }
}
