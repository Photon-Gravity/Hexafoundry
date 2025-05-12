package core.entity.unit;

import core.entity.marker.Marker;

public abstract class UnitAI {
	public abstract void processUnit(Unit u);

	public abstract Marker getDestination(Unit u);
}
