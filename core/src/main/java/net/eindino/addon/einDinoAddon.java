package net.eindino.addon;

import net.eindino.addon.api.UserRequest;
import net.eindino.addon.api.UserResponse;
import net.eindino.addon.config.AddonConfig;
import net.eindino.addon.listeners.PlayerInformationListener;
import net.eindino.addon.user.PlayerCache;
import net.eindino.addon.widget.CoinsWidget;
import net.eindino.addon.widget.GoldWidget;
import net.labymod.api.Laby;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.binding.category.HudWidgetCategory;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.models.addon.annotation.AddonMain;
import net.labymod.api.notification.Notification;

@AddonMain
public class einDinoAddon extends LabyAddon<AddonConfig> {

  public static final HudWidgetCategory EIN_DINO = new HudWidgetCategory("ein_dino");

  @Override
  protected void enable() {
    registerSettingCategory();

    registerListener(new PlayerInformationListener(this));

    labyAPI().hudWidgetRegistry().categoryRegistry().register(EIN_DINO);
    labyAPI().hudWidgetRegistry().register(new CoinsWidget());
    labyAPI().hudWidgetRegistry().register(new GoldWidget());
  }

  public void pushNotification(Component title, Component text) {
    Notification.Builder builder = Notification.builder().title(title).text(text).icon(
        Icon.texture(ResourceLocation.create("eindino", "textures/icon.png"))).type(Notification.Type.SYSTEM);
    labyAPI().notificationController().push(builder.build());
  }

  public void updatePlayerInformation() {
    UserRequest userRequest = new UserRequest();
    userRequest.sendRequestAsync(Laby.labyAPI().getName()).thenRun(() -> {
      if (userRequest.isSuccessful()) {
        UserResponse userResponse = userRequest.getUserResponse();
        PlayerCache.createOrUpdate(userResponse, this);
      }
    }).exceptionally((throwable) -> {
      this.pushNotification(Component.translatable("eindino.notification.failed.title"),
          Component.translatable("eindino.notification.failed.load"));
      return null;
    });
  }

  @Override
  protected Class<? extends AddonConfig> configurationClass() {
    return AddonConfig.class;
  }
}
