package util;

import core.Content;
import core.terrain.HexGrid;
import core.terrain.tile.Floor;
import core.terrain.tile.Ore;

/** Class that contains the world gneration functions. */
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

	public static void generateOres(HexGrid<Ore> ores){
		for(int i=0; i < ores.width(); i++){
			for(int j= 0; j < ores.height(); j++){
				Ore result = null;

				if(i+j >= 64 && i + j <= 190 && Math.random() < 0.1f) {
					result = Content.petrometalOre;
				}

				ores.set(result, i, j);
			}
		}
	}
}
