package core.entity.unit;

import core.World;
import core.entity.marker.Marker;
import core.items.Item;

import java.util.ArrayList;
import java.util.Objects;

public class Unit {
	float x, y, rotation;
	int id;

	public static int lastID = -1;

	public UnitType type;

	Marker destination;

	public ArrayList<Item> inventory = new ArrayList<>();
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

	public boolean hasQualifiedItem(String qualification){
		for(Item item : inventory){
			if(Objects.equals(item.qualify(), qualification)){
				return true;
			}
		}
		return false;
	}
}
