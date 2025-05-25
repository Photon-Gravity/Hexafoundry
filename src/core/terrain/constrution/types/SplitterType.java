package core.terrain.constrution.types;

import core.World;
import core.items.Item;
import core.terrain.Axial;
import core.terrain.constrution.blocks.Block;
import core.terrain.constrution.blocks.CounterBlock;

public class SplitterType extends BlockType {

	int itemCap = 1;

	public SplitterType(String name) {
		super(name);
	}

	@Override
	public boolean canPlace(Axial pos, int rotation) {
		return super.canPlace(pos, rotation);
	}

	@Override
	public void placeBlock(Axial pos, int rotation) {
		CounterBlock instance = new CounterBlock(pos, this, rotation);
		World.blocks.set(instance, pos);
	}

	@Override
	public void update(Block block) {
		super.update(block);

		if(block.items.size() > 0){
			for(int i=0; i < 6; i++){
				Block target = World.blocks.get(block.pos.trns(Axial.d6[((CounterBlock)block).counter]));
				if(target != null && target.type.acceptsItem(block.items.get(0), block, target)){
					target.type.handleItem(block.items.get(0), block, target);
					block.items.remove(0);
					break;
				} else {
					((CounterBlock)block).counter++;
					if(((CounterBlock)block).counter >= 6){
						((CounterBlock)block).counter%=6;
					}
				}
			}
		}
	}

	@Override
	public boolean acceptsItem(Item item, Block source, Block target) {
		return target.items.size() < itemCap;
	}

	@Override
	public void drawGhost(Axial pos, int rotation) {
		super.drawGhost(pos, rotation);
	}
}
