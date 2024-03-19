package net.eindino.addon.widget.staff;

import net.eindino.addon.einDinoAddon;
import net.eindino.addon.user.PlayerCache;
import net.eindino.addon.user.StaffStatsCache;
import net.eindino.addon.util.NumberConventions;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine.State;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;

@SpriteSlot( x = 3)
public class SupportWidget extends TextHudWidget<TextHudWidgetConfig> {

  public SupportWidget() {
    super("supports");
  }

  private TextLine textLine;
  public void load(TextHudWidgetConfig config) {
    super.load(config);
    bindCategory(einDinoAddon.EIN_DINO);
    this.textLine = createLine("SupportChats", "0/0");
    updateTextLine();
  }

  public void onTick(boolean isEditorContext) {
    updateTextLine();
  }

  private void updateTextLine() {
    if (!PlayerCache.isPresent()) {
      this.textLine.updateAndFlush("0/0");
      return;
    }
    String supports = NumberConventions.format(StaffStatsCache.getValue("support"),
        PlayerCache.getUserResponse().getLocale()) + "/" + NumberConventions.format(StaffStatsCache.getValue("support.rating"),
        PlayerCache.getUserResponse().getLocale());
    this.textLine.updateAndFlush(supports);
  }

  @Override
  public boolean isVisibleInGame() {
    return PlayerCache.isStaff();
  }
}
