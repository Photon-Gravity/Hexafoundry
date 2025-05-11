package core.terrain.constrution;

import core.World;
import core.items.Item;
import core.terrain.Axial;
import core.terrain.Vec;

/**Used internally as a part of multiblocks. Defers all operations onto target. Isn't drawn.*/
public class PointerBlockType extends BlockType{

	public PointerBlockType(String name) {
		this.id = ++lastId;

		this.name = name;
	}

	@Override
	public void draw(Block block) {
		Vec drawP = block.pos.toPX();

//		DrawHelper.color(Color.red);
//		DrawHelper.drawCircle(drawP.x-5, drawP.y-5, 10);
//		DrawHelper.reset();
	}

	@Override
	public void onDestroy(Block instance) {
		Axial target = ((PointerBlock)instance).target;

		World.blocks.get(target).type.onDestroy(World.blocks.get(target));
		World.blocks.set(null, target);

	}

	@Override
	public void handleItem(Item item, Block source, Block target) {
		Block pointerTarget = World.blocks.get(((PointerBlock)target).target);

		pointerTarget.type.handleItem(item, source, pointerTarget);
	}

	@Override
	public boolean acceptsItem(Item item, Block source, Block target) {
		Block pointerTarget = World.blocks.get(((PointerBlock)target).target);

		return pointerTarget.type.acceptsItem(item, source, pointerTarget);
	}
}
