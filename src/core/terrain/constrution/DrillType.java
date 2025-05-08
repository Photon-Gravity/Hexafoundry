package core.terrain.constrution;

import core.World;
import core.terrain.Axial;

public class DrillType extends MultiBlockType{

	public Axial itemTarget = new Axial(1, 0);

	public float extractionSpeed = 1/60f;
	public DrillType(String name) {
		super(name);
	}

	@Override
	public void draw(Block block) {
		super.draw(block);

//		Vec drawP = block.pos.toPX();
//		DrawHelper.color(Color.green);
//		DrawHelper.drawCircle(drawP.x-5, drawP.y-5, 10);
//		DrawHelper.reset();

//		Axial oreSource = block.pos;
//
//		if(World.ores.get(oreSource) != null){
//			Vec drawP = block.pos.toPX();
//			DrawHelper.color(Color.blue);
//			DrawHelper.drawCircle(drawP.x-5, drawP.y-5, 10);
//			DrawHelper.reset();
//		}
//
//		for(Axial subunit : shape){
//			oreSource = block.pos.trns(subunit.rotate(block.rotation));
//
//			if(World.ores.get(oreSource) != null){
//					Vec drawP = block.pos.trns(subunit.rotate(block.rotation)).toPX();
//					DrawHelper.color(Color.blue);
//					DrawHelper.drawCircle(drawP.x-5, drawP.y-5, 10);
//					DrawHelper.reset();
//			}
//		}

	}

	@Override
	public boolean canPlace(Axial pos, int rotation) {


		return super.canPlace(pos, rotation);
	}

	@Override
	public void update(Block block) {
		super.update(block);

		System.out.println(block.items.size() > 0);

		Block outputTarget = World.blocks.get(block.pos.trns(itemTarget.rotate(block.rotation)));

		if(outputTarget != null && block.items.size() > 0 && outputTarget.type.acceptsItem(block.items.get(0), block, outputTarget)){
			outputTarget.type.handleItem(block.items.get(0), block, outputTarget);
			block.items.remove(0);
		}

		if(block.progress < 1 && block.items.size() < 5){
			block.progress += extractionSpeed;
		}

		if(block.progress >= 1){
			block.progress = 0;

			int oreSourceIndex = (int)Math.floor(Math.random() * (shape.length+1))-1;

			Axial oreSource = block.pos;
			if(oreSourceIndex >= 0){
				oreSource = block.pos.trns(shape[oreSourceIndex].rotate(block.rotation));
			}
			if(World.ores.get(oreSource) != null){
				block.items.add(World.ores.get(oreSource).dropMaterial);
			}
		}
	}
}
