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

				int variant = (int)Math.floor(Math.random() * 4);

				switch (variant){
					case 0:
						result = Content.amalgamFloor1;
						break;
					case 1:
						result = Content.amalgamFloor2;
						break;
					case 2:
						result = Content.amalgamFloor3;
						break;
					case 3:
						result = Content.amalgamFloor4;
						break;
				}


				if(i+j < floor.width()/2 || i + j > floor.width() /2 * 3-2) result = Content.voidFloor;

				floor.set(result, i, j);
			}
		}
	}

	public static void generateOres(HexGrid<Ore> ores){
		for(int i=0; i < ores.width(); i++){
			for(int j= 0; j < ores.height(); j++){
				Ore result = null;

				if(i+j >= ores.width()/2 && i + j <= ores.width() / 2 * 3 -2 && Math.random() < 0.1f) {
					result = Content.methaneOre;
				}

				ores.set(result, i, j);
			}
		}
	}
}
