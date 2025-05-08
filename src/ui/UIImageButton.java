package ui;

import graphics.DrawHelper;

import java.awt.image.BufferedImage;
import java.util.function.Supplier;

public class UIImageButton extends UIButton {
	BufferedImage icon;

	public UIImageButton(float x, float y, float width, float height, BufferedImage icon, Supplier<Void> onClick) {
		super(x, y, icon.getWidth() + 20, icon.getHeight() + 20, "", onClick);
		this.icon = icon;
	}

	@Override
	public void render() {
		if (visible) {
			DrawHelper.drawImageButton(icon, x, y, width, height, clickCooldown > 0);
		}
	}
}