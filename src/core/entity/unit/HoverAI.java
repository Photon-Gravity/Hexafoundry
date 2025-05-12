package core.entity.unit;

import core.World;
import core.entity.marker.Marker;
import core.terrain.Vec;

public class HoverAI extends UnitAI {
	@Override
	public void processUnit(Unit u) {
		if(u.destination != null){

			Vec destPos = u.destination.position.toPX();
			//rotate to target
			float tr = (float)Math.atan2((destPos.y - u.y), (destPos.x - u.x));
			if(tr > u.rotation){
				u.rotation += u.type.rotationSpeed;
				if(tr < u.rotation){
					u.rotation = tr;
				}
			} else if(tr < u.rotation){
				u.rotation -= u.type.rotationSpeed;
				if(tr > u.rotation){
					u.rotation = tr;
				}
			}

			//move to target
			float dist = (float)Math.sqrt((destPos.x - u.x) * (destPos.x - u.x) + (destPos.y - u.y) * (destPos.y - u.y));

			if(tr == u.rotation && dist > 8){
				u.x += Math.cos(tr) * u.type.speed;
				u.y += Math.sin(tr) * u.type.speed;
			}

			//satisfy marker if close enough
			if(dist <= 8){
				u.destination.satisfy(u);
				u.destination = null;
			}
		} else {
			u.destination = getDestination(u);
		}
	}

	@Override
	public Marker getDestination(Unit u) {
		for(Marker m : World.markers){
			if(m.canSatisfy(u)){
				return (m.canSkipPrerequisite(u) || m.getPrerequisite() == null) ? m : m.getPrerequisite();
			}
		}
		return null;
	}
}
