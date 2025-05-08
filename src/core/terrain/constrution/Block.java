package core.terrain.constrution;

import core.items.Item;
import core.terrain.Axial;

import java.util.ArrayList;

/** This class represents the in-world entity of a block.*/
public class Block {

	Axial pos;
	public BlockType type;

	ArrayList<Item> items;

	int rotation;

	float progress = 0;

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
}
