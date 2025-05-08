package core.terrain.tile;

import core.terrain.Axial;

public class VoidFloor extends Floor{
	public VoidFloor(String name) {
		super(name);
	}

	@Override
	public void drawAt(Axial pos) {
		return;
	}
}
