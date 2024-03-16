package net.eindino.addon.listeners;

import net.eindino.addon.einDinoAddon;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;

public class PlayerChatListener {

  private final einDinoAddon addon;

  public PlayerChatListener(einDinoAddon addon) {
    this.addon = addon;
  }

  @Subscribe
  public void chatHandling(ChatReceiveEvent event) {
    String plain = event.chatMessage().getOriginalPlainText();

    if (plain.startsWith("[einDino] Dir wurden") || plain.startsWith("[einDino] You have been")) {
      addon.updatePlayerInformation();
    }
  }

}
