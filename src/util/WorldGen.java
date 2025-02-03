package util;

import core.Content;
import core.terrain.HexGrid;
import core.terrain.tile.Floor;

public class WorldGen {
	public static void generateFloor(HexGrid<Floor> floor){
		for(int i=0; i < floor.width(); i++){
			for(int j= 0; j < floor.height(); j++){
				Floor result = Content.testFloor;

				if(i+j < 64 || i + j > 190) result = Content.voidFloor;

				floor.set(result, i, j);
			}
		}
	}
}
