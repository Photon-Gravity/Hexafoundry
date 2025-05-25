package core.terrain.constrution.blocks;

import core.items.Item;
import core.terrain.Axial;
import core.terrain.constrution.types.BlockType;

import java.util.ArrayList;

/** This class represents the in-world entity of a block.*/
public class Block {

	public Axial pos;
	public BlockType type;

	public ArrayList<Item> items;

	public int rotation;

	public float progress = 0;

	public Block(Axial pos, BlockType type, int rotation){
		this.pos = pos;
		this.type = type;
		this.rotation = rotation;

		items = new ArrayList<>();
	}

	public void update(){
		type.update(this);
	}

	public void draw(){
		type.draw(this);
	}

	public ArrayList<Item> getItems(){
		return items;
	}

}
