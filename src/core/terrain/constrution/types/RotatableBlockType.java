package core.terrain.constrution.types;

import core.terrain.Axial;
import core.terrain.Vec;
import core.terrain.constrution.blocks.Block;
import graphics.DrawHelper;
import util.FileLoader;

import java.awt.image.BufferedImage;

public class RotatableBlockType extends BlockType {

	BufferedImage[] textures = new BufferedImage[6], ghostTextures = new BufferedImage[6];
	public RotatableBlockType(String name){
		super(name);

		for(int i=0; i < 6;i++){
			textures[i] = FileLoader.find("assets/sprites/tiles/blocks/"+name+"-"+i+".png");
			ghostTextures[i] = FileLoader.find("assets/sprites/tiles/blocks/"+name+"-"+i+".png");

			DrawHelper.tintTexture(ghostTextures[i], BlockType.GHOST_COLOR);
		}
	}
	@Override
	public void draw(Block block){
		Vec drawP = block.pos.toPX();

		DrawHelper.drawRegion(drawP, 0, textures[block.rotation]);
	}

	@Override
	public void drawGhost(Axial pos, int rotation) {
		Vec drawP = pos.toPX();

		DrawHelper.drawRegion(drawP, 0, ghostTextures[rotation]);
	}
}
