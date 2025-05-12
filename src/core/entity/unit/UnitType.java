package core.entity.unit;

import graphics.DrawHelper;
import util.FileLoader;

import java.awt.image.BufferedImage;

public class UnitType {
	public int id;
	public static int lastId = -1;
	String name;

	public UnitAI ai;

	public float speed = 1, rotationSpeed = 6f;

	public BufferedImage region;

	public int itemCap = 5;

	public UnitType(String name){
		this.id = ++lastId;

		this.name = name;

		this.region = FileLoader.find("assets/sprites/units/"+name+".png");
	}

	public void draw(Unit u){
		DrawHelper.drawRegion(u.x, u.y, u.rotation +(float)Math.PI/2, region);
	}
}
