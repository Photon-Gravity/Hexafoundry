package core.terrain.constrution;

import core.World;
import core.items.Item;
import core.terrain.Axial;
import core.terrain.Vec;
import graphics.DrawHelper;
import util.FileLoader;

import java.awt.image.BufferedImage;

public class PneumaticDuctType extends RotatableBlockType {

	BufferedImage[] bottomTextures = new BufferedImage[6];

	float speed = 10/60f;

	public PneumaticDuctType(String name) {
		super(name);

		for(int i=0;i<6;i++){
			bottomTextures[i] = FileLoader.find("assets/sprites/tiles/blocks/"+name+"-bottom-"+i+".png");
		}
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
	public void draw(Block block) {
		Vec drawP = block.pos.toPX();

		DrawHelper.drawRegion(drawP, 0, bottomTextures[block.rotation]);

		if(block.items.size() > 0){
			Vec itemPos = (block.pos.trns(Axial.d6[block.rotation].scl(-0.5f)).trns(Axial.d6[block.rotation].scl(block.progress))).toPX();

			block.items.get(0).draw(itemPos);
		}

		DrawHelper.drawRegion(drawP, 0, textures[block.rotation]);
	}


	@Override
	public boolean acceptsItem(Item item, Block source, Block target) {
		Axial expectedSource = target.pos.trns(Axial.d6[3].rotate(target.rotation));

		return target.items.size() < 1 && (expectedSource.eq(source.pos) || (World.blocks.get(expectedSource) instanceof PointerBlock pointer && pointer.target.eq(source.pos)));
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
