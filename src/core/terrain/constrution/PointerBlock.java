package core.terrain.constrution;

import core.World;
import core.items.Item;
import core.terrain.Axial;

import java.util.ArrayList;

public class PointerBlock extends Block{

	public Axial target;

	public PointerBlock(Axial pos, BlockType type) {
		super(pos, type, 0);
	}

	public void setTarget(Axial target){
		this.target = target;
	}

	@Override
	public ArrayList<Item> getItems() {
		return World.blocks.get(target).getItems();
	}
}
