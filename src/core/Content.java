package core;

import core.items.AlloyMix;
import core.items.Item;
import core.items.ItemType;
import core.items.MetalType;
import core.terrain.tile.Floor;
import core.terrain.tile.Ore;
import core.unit.MoveForwardsAI;
import core.unit.UnitType;

import java.awt.*;

public class Content {

	public static MetalType petrometal, ketonesteel;
	public static ItemType chunk, ingot;
	public static Floor testFloor, voidFloor;
	public static Ore petrometalOre;
	static UnitType testDrone, immobileTestDrone;

	public static void load(){
		//metals
		petrometal = new MetalType("petrometal", Color.CYAN); //TODO actual color
		ketonesteel = new MetalType("ketonesteel", Color.BLUE); //TODO actual color

		//item types
		chunk = new ItemType("chunk");
		ingot = new ItemType("ingot");

		//floor
		testFloor = new Floor("test-floor");
		voidFloor = new Floor("void-floor");

		//ore
		petrometalOre = new Ore("basic-ore", new Item(chunk, new AlloyMix(petrometal, 1f)));

		//ai

		//unit
		testDrone = new UnitType("test-drone"){{
			ai = new MoveForwardsAI();
			speed = 2;
		}};

		immobileTestDrone = new UnitType("test-drone"){{
			ai = new MoveForwardsAI();
			speed = 0;
		}};
	}
}
