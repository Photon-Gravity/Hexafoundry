package core.terrain.constrution.blocks;

import core.terrain.Axial;
import core.terrain.constrution.types.BlockType;

public class CounterBlock extends Block {
	public int counter = 0;
	public CounterBlock(Axial pos, BlockType type, int rotation) {
		super(pos, type, rotation);
	}
}
