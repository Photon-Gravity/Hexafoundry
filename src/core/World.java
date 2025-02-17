package core;

import core.terrain.Axial;
import core.terrain.HexGrid;
import core.terrain.tile.Floor;
import core.terrain.tile.Ore;
import core.unit.Unit;
import graphics.DrawHelper;
import ui.Camera;
import ui.UIElement;
import util.WorldGen;

import java.awt.*;
import java.util.ArrayList;

public class World {
	public static ArrayList<Unit> units = new ArrayList<>();
	public static float cvx = 0, cvy = 0;

	static float camMoveSpeed = 20;

	public static Camera cam;

	public static HexGrid<Floor> floor;
	public static HexGrid<Ore> ores;

	public static void init(){
		floor = new HexGrid<>(127, 127);
		ores = new HexGrid<>(127, 127);

		WorldGen.generateFloor(floor);
		WorldGen.generateOres(ores);

		cam = new Camera(0, 0, 2);
		@SuppressWarnings("unused")
		Unit u = new Unit(Content.testDrone, 100, 100, 0);
		@SuppressWarnings("unused")
		Unit u2 = new Unit(Content.immobileTestDrone, 0, 0, (float)Math.PI);
	}
	public static void render(Graphics2D g){
		DrawHelper.setGraphics(g);
		//floor layer
		Axial temp = new Axial(0, 0);

		for(int i=0; i < floor.width(); i++){
			for(int j= 0; j < floor.height(); j++){
				temp.set(i, j);
				floor.get(i, j).drawAt(temp);
			}
		}

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
