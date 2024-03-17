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

@SpriteSlot( x = 2)
public class WarnsWidget extends TextHudWidget<TextHudWidgetConfig> {

  public WarnsWidget() {
    super("warns");
  }

  private TextLine textLine;
  public void load(TextHudWidgetConfig config) {
    super.load(config);
    bindCategory(einDinoAddon.EIN_DINO);
    this.textLine = createLine("Warns", 0);
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
    String warns = NumberConventions.format(StaffStatsCache.getValue("warns"),
        PlayerCache.getUserResponse().getLocale());
    this.textLine.updateAndFlush(warns);
    this.textLine.setState(PlayerCache.isStaff() ? State.VISIBLE : State.DISABLED);
  }
}
