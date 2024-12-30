package core;

import core.unit.MoveForwardsAI;
import core.unit.UnitType;

import java.awt.*;

public class Content {
	static UnitType testDrone;

	public static void load(){
		//ai

		//unit
		testDrone = new UnitType("test-drone"){{
			ai = new MoveForwardsAI();
			speed = 2;
		}};
	}
}
