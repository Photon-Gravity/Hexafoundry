package core;

import core.entity.unit.HoverAI;
import core.entity.unit.UnitType;
import core.items.*;
import core.terrain.Axial;
import core.terrain.constrution.types.WarehouseType;
import core.terrain.constrution.types.*;
import core.terrain.tile.Floor;
import core.terrain.tile.Ore;
import core.terrain.tile.VoidFloor;

import java.awt.*;

public class Content {

	public static MetalType methane, wroughtKetone;
	public static ItemType chunk, ingot, cog;
	public static Floor testFloor, amalgamFloor1, amalgamFloor2, amalgamFloor3, amalgamFloor4, voidFloor;
	public static Ore methaneOre;
	static UnitType testDrone;

	public static BlockType pointer, basicDrill, pneumaticDuct, merger, warehouse, smelter, splitter, cogPress;

	public static void load(){
		//metals
		methane = new MetalType("methane", Color.CYAN); //TODO actual color
		wroughtKetone = new MetalType("wrought-ketone", Color.BLUE); //TODO actual color

		//item types
		chunk = new ItemType("chunk");
		ingot = new ItemType("ingot");
		cog = new ItemType("cog");

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
			ai = new HoverAI();
			speed = 8;
		}};

		//blocktypes

		pointer = new PointerBlockType("pointer");
		basicDrill = new DrillType("basic-drill"){{
			extractionSpeed = 0.25f/60f;
			shape = new Axial[]{new Axial(0, 1), new Axial(-1, 1)};
			itemTarget = new Axial(-1, 2);

			cost = new ItemIngredient[]{
					new ItemIngredient("methane-ingot", 5),
					new ItemIngredient("methane-cog", 2)
			};
		}};

		pneumaticDuct = new PneumaticDuctType("pneumatic-duct"){{
			cost = new ItemIngredient[]{
					new ItemIngredient("methane-ingot", 1)
			};
		}};

		merger = new MergerType("merger"){{
			cost = new ItemIngredient[]{
					new ItemIngredient("methane-ingot", 2)
			};
		}};

		warehouse = new WarehouseType("warehouse"){{
			shape = Axial.d6;
			cost = new ItemIngredient[]{
				new ItemIngredient("methane-ingot", 20),
				new ItemIngredient("methane-cog", 5),
			};

		}};

		smelter = new ReshaperType("smelter"){{
			shape = new Axial[]{new Axial(0, 1), new Axial(-1, 1), new Axial(-1, 0)};
			cost = new ItemIngredient[]{
					new ItemIngredient("methane-ingot", 6)
			};
			ingredientType = chunk;
			resultType = ingot;
			outputTarget = Axial.d6[0];
		}};
		cogPress = new ReshaperType("cog-press"){{
			shape = new Axial[]{new Axial(0, 1), new Axial(-1, 1), new Axial(0, -1), new Axial(1, -1)};
			cost = new ItemIngredient[]{
					new ItemIngredient("methane-ingot", 10),
					new ItemIngredient("methane-cog", 4)
			};
			ingredientType = ingot;
			resultType = cog;
			outputTarget = Axial.d6[0];
		}};

		splitter = new SplitterType("splitter"){{
			cost = new ItemIngredient[]{
					new ItemIngredient("methane-ingot", 3)
			};
		}};

		new Qualification("methane-ingot", item -> (item.type == ingot && item.composition.closeEnoughTo(new AlloyMix(methane, 1f), 0.9f)));
		new Qualification("methane-cog", item -> (item.type == cog && item.composition.closeEnoughTo(new AlloyMix(methane, 1f), 0.9f)));

		new Qualification("ketonesteel-ingot", item -> (item.type == ingot && item.composition.closeEnoughTo(new AlloyMix(methane, 0.75f, wroughtKetone, 0.25f), 0.9f)));
	}
}
