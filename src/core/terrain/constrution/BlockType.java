package core.terrain.constrution;

import core.terrain.Point;
import graphics.DrawHelper;
import util.FileLoader;

import java.awt.image.BufferedImage;

public class BlockType {
	BufferedImage texture;

	public int id;

	String name;

	public static int lastId = -1;

	public boolean walkable = true;
	public BlockType(String name){
		this.id = ++lastId;

		this.name = name;

		texture = FileLoader.find("assets/sprites/tiles/blocks/"+name+".png");
	}

	public void update(Block block){

	}

	public void draw(Block block){
		Point drawP = block.pos.toPX();

		DrawHelper.drawRegion(drawP, 0, texture);
	}
}
