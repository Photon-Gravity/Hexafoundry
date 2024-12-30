package ui;

import java.util.ArrayList;

public abstract class UIElement {
	public boolean visible;
	public int id;

	public static int lastID = -1;
	public static ArrayList<UIElement> allUI = new ArrayList<>();

	public UIElement(){
		init();
	}
	public abstract void render();
	public abstract void update();
	public abstract void onClick();
	public abstract boolean withinBounds(float x, float y);

	public void init(){
		this.id = ++lastID;
		allUI.add(this);

	}
}
