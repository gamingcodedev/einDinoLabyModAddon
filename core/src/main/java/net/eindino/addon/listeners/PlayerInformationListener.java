package net.eindino.addon.listeners;

import net.eindino.addon.api.UserRequest;
import net.eindino.addon.api.UserResponse;
import net.eindino.addon.einDinoAddon;
import net.eindino.addon.user.PlayerCache;
import net.labymod.api.Laby;
import net.labymod.api.client.network.server.ServerAddress;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.network.playerinfo.PlayerInfoUpdateEvent;
import net.labymod.api.event.client.network.server.ServerDisconnectEvent;
import net.labymod.api.event.client.network.server.ServerLoginEvent;
import net.labymod.api.event.client.session.SessionUpdateEvent;

public class PlayerInformationListener {

  private final einDinoAddon addon;

  public PlayerInformationListener(einDinoAddon addon) {
    this.addon = addon;
  }

  @Subscribe
  public void updatePlayerData(ServerLoginEvent event) {
    ServerAddress serverAddress = event.serverData().address();

    if (event.getDisconnectReason() != null) return;

    if (serverAddress.matches("eindino.net", 25565, true)
        || serverAddress.matches("gamingcode.dev", 25565, true)
        || serverAddress.matches("eindino.dev", 25565, true)
        || serverAddress.matches("eindino.eu", 25565, true)
        || serverAddress.matches("eindino.de", 25565, true)
        || serverAddress.matches("magicalsmp.de", 25565, true)) {
      addon.updatePlayerInformation();
    }
  }

  @Subscribe
  public void invalidatePlayerData(ServerDisconnectEvent event) {
    if (PlayerCache.isPresent()) {
      PlayerCache.invalidate();
    }
  }

  @Subscribe
  public void updatePlayerData(SessionUpdateEvent event) {
    if (PlayerCache.isPresent()) {
      PlayerCache.invalidate();

      addon.updatePlayerInformation();
    }
  }

}
