package ui;

import graphics.DrawHelper;

import java.util.function.Supplier;

public class UIButton extends UIElement{

	float x, y, width, height;

	float maxClickCooldown = 15, clickCooldown = 0;

	String text;

	Supplier<Void> onClick;
	public UIButton(float x, float y, float width, float height, String text, Supplier<Void> onClick){
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		visible = true;
		clickable = true;
		this.onClick = onClick;
	}


	@Override
	public void render() {
		if(visible){
			DrawHelper.drawButton(text, x, y, width, height, clickCooldown > 0);
		}
	}
	@Override
	public void onClick() {
		clickCooldown = maxClickCooldown;

		onClick.get();
	}
	@Override
	public void update(){
		if(clickCooldown > 0) clickCooldown--;
	}

	@Override
	public boolean withinBounds(float x, float y) {
		return clickable && x > this.x && x < this.x + width && y > this.y && y < this.y + height;
	}
}
