package core.unit;

import core.World;
import core.unit.UnitType;

public class Unit {
	float x, y, rotation;
	int id;

	public static int lastID = -1;

	UnitType type;

	public Unit(UnitType type, float x, float y, float rotation){
		this.x = x;
		this.y = y;
		this.rotation = rotation;
		this.type = type;

		this.id = ++lastID;

		World.units.add(this);
	}

	public void draw(){
		type.draw(this);
	}

	public void update(){
		this.type.ai.processUnit(this);
	}
}
