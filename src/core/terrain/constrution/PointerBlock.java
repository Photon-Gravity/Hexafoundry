package core.terrain.constrution;

import core.terrain.Axial;

public class PointerBlock extends Block{

	Axial target;

	public PointerBlock(Axial pos, BlockType type) {
		super(pos, type, 0);
	}

	public void setTarget(Axial target){
		this.target = target;
	}
}
