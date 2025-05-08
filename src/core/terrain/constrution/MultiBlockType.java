package core.terrain.constrution;

import core.Content;
import core.World;
import core.terrain.Axial;

public class MultiBlockType extends RotatableBlockType{

	public Axial[] shape;
	int rotation;
	public MultiBlockType(String name) {
		super(name);
	}

	@Override
	public void placeBlock(Axial pos, int rotation) {
		super.placeBlock(pos, rotation);

		Block instance = World.blocks.get(pos);

		for(Axial subunit : shape){
			Axial pointerPos = instance.pos.trns(subunit.rotate(instance.rotation));

			PointerBlock pointerBlock = new PointerBlock(pointerPos, Content.pointer);

			World.blocks.set(pointerBlock, pointerPos);

			pointerBlock.setTarget(pos);
		}
	}

	@Override
	public boolean canPlace(Axial pos, int rotation) {
		boolean valid = validTile(pos);
		for(Axial subunit : shape){
			valid = valid && validTile(pos.trns(subunit.rotate(rotation)));
		}

		return valid;
	}


	public boolean validTile(Axial pos){
		return World.floor.inBounds(pos) && World.floor.get(pos).solid && World.blocks.get(pos) == null;
	}
	@Override
	public void onDestroy(Block instance) {
		super.onDestroy(instance);

		for(Axial subunit : shape){
			Axial pointerPos = instance.pos.trns(subunit.rotate(instance.rotation));

			World.blocks.set(null, pointerPos);
		}
	}
}
