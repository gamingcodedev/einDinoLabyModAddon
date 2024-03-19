package net.eindino.addon.widget;

import net.eindino.addon.einDinoAddon;
import net.eindino.addon.user.PlayerCache;
import net.eindino.addon.util.NumberConventions;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine.State;
import net.labymod.api.client.gui.lss.property.annotation.AutoWidget;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;

@SpriteSlot
public class CoinsWidget extends TextHudWidget<TextHudWidgetConfig> {

  public CoinsWidget() {
    super("coins");
  }

  private TextLine textLine;

  public void load(TextHudWidgetConfig config) {
    super.load(config);
    bindCategory(einDinoAddon.EIN_DINO);
    this.textLine = createLine("Coins", 0);
    updateTextLine();
  }

  public void onTick(boolean isEditorContext) {
    updateTextLine();
  }

  private void updateTextLine() {
    if (!PlayerCache.isPresent()) {
      this.textLine.updateAndFlush(0);
      return;
    }
    String coinsString = NumberConventions.format(PlayerCache.getUserResponse().getCoins(),
        PlayerCache.getUserResponse().getLocale());
    this.textLine.updateAndFlush(coinsString);
  }

  @Override
  public boolean isVisibleInGame() {
    return PlayerCache.isPresent();
  }
}
