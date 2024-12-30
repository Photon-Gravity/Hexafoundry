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
		Unit u = new Unit(Content.testDrone, 100, 100, 0);
	}
	public static void render(Graphics2D g){
		DrawHelper.setGraphics(g);

		//ground unit layer
		for(int i = 0; i < units.size(); i++){
			units.get(i).draw();
		}

		//UI
		for(int i = 0 ;i < UIElement.allUI.size(); i++){
			UIElement.allUI.get(i).render();
		}
	}

	public static void update(){
		cam.x += cvx / cam.zoom * camMoveSpeed;
		cam.y += cvy / cam.zoom * camMoveSpeed;

		for(int i = 0; i < units.size(); i++){
			units.get(i).update();
		}

		for(int i = 0; i < UIElement.allUI.size(); i++){
			UIElement.allUI.get(i).update();
		}
	}
}
