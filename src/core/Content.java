package core;

import core.unit.MoveForwardsAI;
import core.unit.UnitType;

public class Content {
	static UnitType testDrone, immobileTestDrone;

	public static void load(){
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
