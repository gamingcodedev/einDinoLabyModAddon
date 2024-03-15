package net.eindino.addon.widget;

import net.eindino.addon.config.AddonWidgetConfig;
import net.eindino.addon.user.PlayerCache;
import net.eindino.addon.util.NumberConventions;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.client.gui.hud.hudwidget.SimpleHudWidget;
import net.labymod.api.client.gui.hud.position.HudSize;
import net.labymod.api.client.gui.mouse.MutableMouse;
import net.labymod.api.client.render.font.RenderableComponent;
import net.labymod.api.client.render.matrix.Stack;
import net.labymod.api.util.Color;

public class GoldWidget extends SimpleHudWidget<AddonWidgetConfig> {

  public GoldWidget() {
    super("gold", AddonWidgetConfig.class);
  }

  @Override
  public void render(Stack stack, MutableMouse mouse, float partialTicks, boolean isEditorContext,
      HudSize size) {
    size.setWidth(0);
    size.setHeight(0);

    if (PlayerCache.getUserResponse() == null) return;

    String goldString = NumberConventions.format(PlayerCache.getUserResponse().getGold(),
        PlayerCache.getUserResponse().getLocale());

    Color labelColor = labyAPI.hudWidgetRegistry().globalHudWidgetConfig().labelColor().get();
    Color valueColor = labyAPI.hudWidgetRegistry().globalHudWidgetConfig().valueColor().get();

    renderComponent(Component.translatable("eindino.hudWidget.eindino.gold").color(TextColor.color(labelColor.getRed(), labelColor.getGreen(), labelColor.getBlue()))
        .append(Component.text(goldString))
        .color(TextColor.color(valueColor.getRed(), valueColor.getGreen(), valueColor.getBlue())), stack, size);
  }

  private void renderComponent(Component component, Stack stack, HudSize size) {
    RenderableComponent renderableComponent = RenderableComponent.of(component);
    if (stack != null)
      this.labyAPI.renderPipeline().componentRenderer().builder()
          .text(renderableComponent)
          .pos(1.0F, 1.0F)
          .render(stack);
    size.setWidth((int)(renderableComponent.getWidth() + 2.0F));
    size.setHeight((int)(renderableComponent.getHeight() + 2.0F));
  }

  @Override
  public boolean isVisibleInGame() {
    return true;
  }
}
