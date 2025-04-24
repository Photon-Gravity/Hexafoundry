package core.terrain.constrution;

import core.items.ItemStack;
import core.terrain.Axial;
import graphics.DrawHelper;

import java.util.ArrayList;

/** This class represents the in-world entity of a block.*/
public class Block {
	Axial pos;
	BlockType type;

	ArrayList<ItemStack> items;

	public Block(Axial pos, BlockType type){
		this.pos = pos;
		this.type = type;

		items = new ArrayList<>();
	}

	public void update(){
		type.update(this);
	}

	public void draw(){
		DrawHelper.drawRegion(pos.toPX(), 0, type.texture);
	}
}
