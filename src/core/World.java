package core;

import core.entity.unit.Unit;
import core.terrain.Axial;
import core.terrain.HexGrid;
import core.terrain.Vec;
import core.terrain.constrution.Block;
import core.terrain.constrution.BlockType;
import core.terrain.tile.Floor;
import core.terrain.tile.Ore;
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

	public static Vec mousePos = new Vec(0, 0);
	public static Axial mouseTile = new Axial(0, 0);

	public static HexGrid<Floor> floor;
	public static HexGrid<Ore> ores;
	public static HexGrid<Block> blocks;

	public static final int WORLD_SIZE = 127;

	public static BlockType cursor;

	public static int cursorRotation = 0;

	public static void init(){
		floor = new HexGrid<>(WORLD_SIZE, WORLD_SIZE);
		ores = new HexGrid<>(WORLD_SIZE, WORLD_SIZE);
		blocks = new HexGrid<>(WORLD_SIZE, WORLD_SIZE);

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

		//ores
		for(int i=0; i < ores.width(); i++){
			for(int j= 0; j < ores.height(); j++){
				if(ores.get(i, j) != null){
					temp.set(i, j);
					ores.get(i, j).drawAt(temp);
				}
			}
		}

		//block
		for(int i=0; i < blocks.height(); i++){
			for(int j= 0; j < blocks.width(); j++){
				if(blocks.get(j, i) != null){
					blocks.get(j, i).draw();
				}
			}
		}

		//cursor
		if(blocks.inBounds(mouseTile) && cursor != null && cursor.canPlace(mouseTile, cursorRotation)) {
			cursor.drawGhost(mouseTile, cursorRotation);
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

		Point mousePoint = Game.mouseInfo.getLocation();
		mousePos = (new Vec((float)mousePoint.getX()-12, (float)mousePoint.getY()-24)).toPX();

		mouseTile = mousePos.toAxial();
		mouseTile.round();

		for (Unit unit : units) {
			unit.update();
		}

		for(UIElement element : UIElement.allUI){
			element.update();
		}

		for(Block block : blocks){
			if(block != null){
				block.update();
			}
		}
	}

	public static void tryPlaceCursor(){
		if(blocks.inBounds(mouseTile) && cursor != null && cursor.canPlace(mouseTile, cursorRotation)) {
			cursor.placeBlock(mouseTile, cursorRotation);
		}
	}

	public static void tryDeconstructCursor(){
		if(blocks.inBounds(mouseTile) && blocks.get(mouseTile) != null) {
			blocks.get(mouseTile).type.onDestroy(blocks.get(mouseTile));
			blocks.set(null, mouseTile);
		}
	}

}
