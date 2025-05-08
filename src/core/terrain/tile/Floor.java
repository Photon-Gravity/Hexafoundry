package core.terrain.tile;

import core.terrain.Axial;
import core.terrain.Vec;
import graphics.DrawHelper;
import util.FileLoader;

import java.awt.image.BufferedImage;

public class Floor {
	BufferedImage texture;

	public int id;

	String name;

	public static int lastId = -1;

	public boolean solid = true;
	public Floor(String name){
		this.id = ++lastId;

		this.name = name;

		texture = FileLoader.find("assets/sprites/tiles/floor/"+name+".png");
	}

	public void drawAt(Axial pos){
		Vec drawP = pos.toPX();

		DrawHelper.drawRegion(drawP, 0, texture);
	}
}
