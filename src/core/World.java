package core;

import core.entity.marker.GhostMarker;
import core.entity.marker.Marker;
import core.entity.marker.MarkerStatus;
import core.entity.unit.Unit;
import core.items.AlloyMix;
import core.items.Item;
import core.terrain.Axial;
import core.terrain.HexGrid;
import core.terrain.Vec;
import core.terrain.constrution.blocks.Block;
import core.terrain.constrution.types.BlockType;
import core.terrain.constrution.types.MultiBlockType;
import core.terrain.ghost.Ghost;
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

	public static ArrayList<Ghost> ghosts = new ArrayList<>();


	public static ArrayList<Marker> markers = new ArrayList<>();
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
		Unit u = new Unit(Content.testDrone, 1000, 600, 0);

		Content.warehouse.placeBlock(new Axial(50, 50), 0);
		Content.warehouse.placeBlock(new Axial(55, 50), 0);

		for(int i=0;i<100;i++){
			World.blocks.get(new Axial(50, 50)).getItems().add(new Item(Content.ingot, new AlloyMix(Content.methane, 1f)));
			World.blocks.get(new Axial(55, 50)).getItems().add(new Item(Content.cog, new AlloyMix(Content.methane, 1f)));
		}

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

		//ghost
		for(Ghost ghost : ghosts){
			ghost.type.drawGhost(ghost.pos, ghost.rotation);
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
			ghosts.add(new Ghost(mouseTile, cursorRotation, cursor));
		}
	}

	public static void tryDeconstructCursor(){
		for(Ghost ghost : ghosts){
			if(ghost.pos.eq(mouseTile)){
				for(int i =0; i < World.markers.size(); i++){
					Marker m = World.markers.get(i);
					if(m instanceof GhostMarker gm && gm.owner == ghost){
						markers.remove(m);
						m.status = MarkerStatus.FULFILLED;
						i--;
					}
				}
				ghosts.remove(ghost);
				break;
			}
		}

		if(blocks.inBounds(mouseTile) && blocks.get(mouseTile) != null) {
			blocks.get(mouseTile).type.onDestroy(blocks.get(mouseTile));
			blocks.set(null, mouseTile);
		}
	}

	public static ArrayList<Axial> getOccupiedTiles(){
		ArrayList<Axial> out = new ArrayList<>();
		for(Ghost ghost : ghosts){
			out.add(ghost.pos);
			if(ghost.type instanceof MultiBlockType multighost){
				for(Axial pos : multighost.shape){
					out.add(pos.rotate(ghost.rotation).trns(ghost.pos));
				}
			}
		}
		return out;
	}

}
