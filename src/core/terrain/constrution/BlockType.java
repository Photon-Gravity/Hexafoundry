package core.terrain.constrution;

import core.World;
import core.items.Item;
import core.terrain.Axial;
import core.terrain.Vec;
import graphics.DrawHelper;
import util.FileLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BlockType {
	public static final Color GHOST_COLOR = new Color(0xFF0066FF, true);
	public BufferedImage texture;
	BufferedImage ghostTexture;

	public int id;

	String name;

	public static int lastId = -1;

	public boolean walkable = true;

	protected BlockType(){
		this.id = ++lastId;
	}
	public BlockType(String name){
		this.id = ++lastId;

		this.name = name;

		texture = FileLoader.find("assets/sprites/tiles/blocks/"+name+".png");
		ghostTexture = FileLoader.find("assets/sprites/tiles/blocks/"+name+".png");

		DrawHelper.tintTexture(ghostTexture, BlockType.GHOST_COLOR);
	}

	public void placeBlock(Axial pos, int rotation){
		Block instance = new Block(pos, this, rotation);
		World.blocks.set(instance, pos);
	}


	public void onDestroy(Block instance){

	}

	public void update(Block block){

	}

	public boolean canPlace(Axial pos, int rotation){
		return World.blocks.get(pos) == null && World.floor.get(pos).solid && World.blocks.get(pos) == null;
	}

	public void draw(Block block){
		Vec drawP = block.pos.toPX();

		DrawHelper.drawRegion(drawP, 0, texture);
	}

	public void drawGhost(Axial pos, int rotation){

	}

	public boolean acceptsItem(Item item, Block source, Block target){
		return false;
	}

	public void handleItem(Item item, Block source, Block target){
		target.items.add(item);
	}
}
