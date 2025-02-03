package core;

import core.terrain.tile.Floor;
import core.unit.MoveForwardsAI;
import core.unit.UnitType;

public class Content {

	public static Floor testFloor, voidFloor;
	static UnitType testDrone, immobileTestDrone;

	public static void load(){
		//floor
		testFloor = new Floor("test-floor");
		voidFloor = new Floor("void-floor");

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
