package core;

import core.entity.unit.MoveForwardsAI;
import core.entity.unit.UnitType;
import core.items.AlloyMix;
import core.items.Item;
import core.items.ItemType;
import core.items.MetalType;
import core.terrain.Axial;
import core.terrain.constrution.*;
import core.terrain.tile.Floor;
import core.terrain.tile.Ore;
import core.terrain.tile.VoidFloor;

import java.awt.*;

public class Content {

	public static MetalType methane, wroughtKetone;
	public static ItemType chunk, ingot;
	public static Floor testFloor, amalgamFloor1, amalgamFloor2, amalgamFloor3, amalgamFloor4, voidFloor;
	public static Ore methaneOre;
	static UnitType testDrone, immobileTestDrone;

	public static BlockType pointer, basicDrill, pneumaticDuct, merger;

	public static void load(){
		//metals
		methane = new MetalType("methane", Color.CYAN); //TODO actual color
		wroughtKetone = new MetalType("wrought-ketone", Color.BLUE); //TODO actual color

		//item types
		chunk = new ItemType("chunk");
		ingot = new ItemType("ingot");

		//floor
		testFloor = new Floor("test-floor");

		amalgamFloor1 = new Floor("amalgam-floor1");
		amalgamFloor2 = new Floor("amalgam-floor2");
		amalgamFloor3 = new Floor("amalgam-floor3");
		amalgamFloor4 = new Floor("amalgam-floor4");

		voidFloor = new VoidFloor("void-floor");
		voidFloor.solid = false;

		//ore
		methaneOre = new Ore("basic-ore", new Item(chunk, new AlloyMix(methane, 1f)));

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

		//blocktypes

		pointer = new PointerBlockType("pointer");
		basicDrill = new DrillType("basic-drill"){{
			extractionSpeed = 0.25f/60f;
			shape = new Axial[]{new Axial(0, 1), new Axial(-1, 1)};
			itemTarget = new Axial(-1, 2);
		}};

		pneumaticDuct = new PneumaticDuctType("pneumatic-duct");

		merger = new MergerType("merger");
	}
}
