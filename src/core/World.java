package core;

import core.unit.Unit;
import graphics.DrawHelper;
import ui.Camera;
import ui.UIElement;

import java.awt.*;
import java.util.ArrayList;

public class World {
	public static ArrayList<Unit> units = new ArrayList<>();
	public static float cvx = 0, cvy = 0;

	static float camMoveSpeed = 20;

	public static Camera cam;

	public static void init(){
		cam = new Camera(0, 0, 2);
		@SuppressWarnings("unused")
		Unit u = new Unit(Content.testDrone, 100, 100, 0);
		@SuppressWarnings("unused")
		Unit u2 = new Unit(Content.immobileTestDrone, 0, 0, (float)Math.PI);
	}
	public static void render(Graphics2D g){
		DrawHelper.setGraphics(g);

		//ground unit layer
		for (Unit unit : units) {
			unit.draw();
		}

		//UI
		for(UIElement element : UIElement.allUI){
			element.render();
		}
	}

	public static void update(){
		cam.x += cvx / cam.zoom * camMoveSpeed;
		cam.y += cvy / cam.zoom * camMoveSpeed;

		for (Unit unit : units) {
			unit.update();
		}

		for(UIElement element : UIElement.allUI){
			element.update();
		}
	}
}
