package core.entity.marker;

import core.entity.unit.Unit;
import core.terrain.Axial;

import java.util.ArrayList;

public abstract class Marker {
	public Axial position;
	public ArrayList<String> data = new ArrayList<>();
	public MarkerStatus status = MarkerStatus.UNFULFILLED;

	public int count;

	public Marker(Axial position, String type){
		this.position = position;
		this.data.add(type);
	}

	public abstract boolean canSatisfy(Unit u);

	public abstract Marker getPrerequisite();

	public abstract boolean canSkipPrerequisite(Unit u);
	public abstract void satisfy(Unit u);
}
