package net.eindino.addon.widget;

import net.eindino.addon.einDinoAddon;
import net.eindino.addon.user.PlayerCache;
import net.eindino.addon.util.NumberConventions;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.lss.property.annotation.AutoWidget;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;

@SpriteSlot(y = 1, x = 2)
public class GoldWidget extends TextHudWidget<TextHudWidgetConfig> {

  public GoldWidget() {
    super("gold");
  }

  private TextLine textLine;
  public void load(TextHudWidgetConfig config) {
    super.load(config);
    bindCategory(einDinoAddon.EIN_DINO);
    this.textLine = createLine("Gold", 0);
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
    String coinsString = NumberConventions.format(PlayerCache.getUserResponse().getGold(),
        PlayerCache.getUserResponse().getLocale());
    this.textLine.updateAndFlush(coinsString);
  }
}
