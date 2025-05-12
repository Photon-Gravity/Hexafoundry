package core.entity.marker;

import core.World;
import core.entity.unit.Unit;
import core.items.Item;
import core.terrain.Axial;
import core.terrain.WarehouseType;
import core.terrain.constrution.PointerBlock;

public class WarehouseMarker extends Marker {
	public WarehouseMarker(Axial position) {
		super(position, "warehouse");
		status = MarkerStatus.OPTIONAL;
	}

	@Override
	public boolean canSatisfy(Unit u) {
		return false;
	}

	@Override
	public Marker getPrerequisite() {
		return null;
	}

	@Override
	public boolean canSkipPrerequisite(Unit u) {
		return true;
	}

	@Override
	public void satisfy(Unit u) {

		if(World.blocks.get(position).type instanceof WarehouseType || (World.blocks.get(position) instanceof PointerBlock p  && World.blocks.get(p.target).type instanceof WarehouseType)){
			Item removalTarget = null;

			for(Marker m : World.markers){
				if(m instanceof GhostMarker){
					for(Item item : World.blocks.get(position).getItems()){
						for(int i=1; i < m.data.size();i++){
							if(item.qualify().equals(m.data.get(i))){
								removalTarget = item;
								break;
							}
						}
						if(removalTarget != null) break;
					}
				}
				if (removalTarget != null) break;
			}

			if(removalTarget != null) {
				u.inventory.add(removalTarget);
				World.blocks.get(position).getItems().remove(removalTarget);
			}
		}
	}
}
