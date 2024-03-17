package net.eindino.addon.listeners;

import net.eindino.addon.einDinoAddon;
import net.eindino.addon.user.PlayerCache;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PlayerChatListener {

  private final einDinoAddon addon;
  private final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

  public PlayerChatListener(einDinoAddon addon) {
    this.addon = addon;
    this.scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
  }

  @Subscribe
  public void chatHandling(ChatReceiveEvent event) {
    String plain = event.chatMessage().getOriginalPlainText();

    if (!PlayerCache.isPresent()) {
      return;
    }

    if (plain.startsWith("[einDino] Dir wurden")
        || plain.startsWith("[einDino] You have been")
        || plain.startsWith("[einDino] Dein Rang wurde zu")
        || plain.startsWith("[einDino] Your rank was changed to")) {
      this.scheduledThreadPoolExecutor.schedule(addon::updatePlayerInformation, 10, TimeUnit.SECONDS);
    }

    if (PlayerCache.isStaff()) {
      if (plain.startsWith("[Warn]") || plain.startsWith("[Support]") || plain.toLowerCase().contains("report")) {
        this.scheduledThreadPoolExecutor.schedule(addon::updatePlayerInformation, 10, TimeUnit.SECONDS);
      }
    }
  }

}
