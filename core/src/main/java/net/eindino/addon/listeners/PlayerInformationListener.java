package net.eindino.addon.listeners;

import net.eindino.addon.api.UserRequest;
import net.eindino.addon.api.UserResponse;
import net.eindino.addon.user.PlayerCache;
import net.labymod.api.Laby;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.network.playerinfo.PlayerInfoUpdateEvent;

public class PlayerInformationListener {

  @Subscribe
  public void updatePlayerData(PlayerInfoUpdateEvent event) {
    this.updatePlayerInformation();
  }

  private void updatePlayerInformation() {
    UserRequest userRequest = new UserRequest();
    userRequest.sendRequestAsync(Laby.labyAPI().getName()).thenRun(() -> {
      if (userRequest.isSuccessful()) {
        UserResponse userResponse = userRequest.getUserResponse();
        PlayerCache.createOrUpdate(userResponse);
      }
    }).exceptionally((throwable) -> {
      return null;
    });
  }

}
