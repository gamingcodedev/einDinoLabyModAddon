package net.eindino.addon.config;

import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.annotation.SpriteTexture;
import net.labymod.api.configuration.loader.property.ConfigProperty;

@ConfigName("settings")
@SpriteTexture("settings")
public class AddonConfig extends net.labymod.api.addon.AddonConfig {

  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SwitchSetting
  private final ConfigProperty<Boolean> coins = new ConfigProperty<>(true);

  @SwitchSetting
  private final ConfigProperty<Boolean> gold = new ConfigProperty<>(true);

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<Boolean> coinsEnabled() {
    return coins;
  }

  public ConfigProperty<Boolean> goldEnabled() {
    return gold;
  }
}
