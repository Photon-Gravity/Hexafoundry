package core.terrain.constrution;

import core.World;
import core.items.Item;
import core.terrain.Axial;

public class MergerType extends RotatableBlockType{

	public float speed = 10/60f;
	public MergerType(String name) {
		super(name);
	}

	@Override
	public void update(Block block) {
		super.update(block);

		Axial next = block.pos.trns(Axial.d6[0].rotate(block.rotation));

		if(block.progress < 1f && block.items.size() > 0){
			block.progress += speed;
			if(block.progress > 1f){
				block.progress = 1;
			}
		}

		if(block.progress == 1 && World.blocks.inBounds(next) && World.blocks.get(next) != null && World.blocks.get(next).type.acceptsItem(block.items.get(0), block, World.blocks.get(next))){
			block.progress = 0;
			World.blocks.get(next).type.handleItem(block.items.get(0), block, World.blocks.get(next));
			block.items.remove(0);
		}
	}


	@Override
	public boolean acceptsItem(Item item, Block source, Block target) {
		return target.items.size() < 1;
	}

	@Override
	public void handleItem(Item item, Block source, Block target) {
		super.handleItem(item, source, target);
	}


	@Override
	public boolean canPlace(Axial pos, int rotation) {
		return super.canPlace(pos, rotation);
	}
}
