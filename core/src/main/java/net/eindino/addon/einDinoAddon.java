package net.eindino.addon;

import net.eindino.addon.config.AddonConfig;
import net.eindino.addon.listeners.PlayerInformationListener;
import net.eindino.addon.widget.CoinsWidget;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class einDinoAddon extends LabyAddon<AddonConfig> {

  @Override
  protected void enable() {
    registerSettingCategory();

    registerListener(new PlayerInformationListener());

    labyAPI().hudWidgetRegistry().register(new CoinsWidget());
  }

  @Override
  protected Class<? extends AddonConfig> configurationClass() {
    return AddonConfig.class;
  }
}
