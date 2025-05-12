package core.entity.marker;

import core.World;
import core.entity.unit.Unit;
import core.items.Item;
import core.items.ItemIngredient;
import core.terrain.Axial;
import core.terrain.ghost.Ghost;

import java.util.Objects;

public class GhostMarker extends Marker{

	public Ghost owner;
	public GhostMarker(Axial position, String type, Ghost owner) {
		super(position, type);
		this.owner = owner;
		this.data.add("");
	}

	@Override
	public boolean canSatisfy(Unit u) {
		System.out.println("GhostMarker: " + (status == MarkerStatus.UNFULFILLED) + ", " + (u.inventory.size() == 0) +"," + (canSkipPrerequisite(u)));
		return status == MarkerStatus.UNFULFILLED && (u.inventory.size() == 0 || canSkipPrerequisite(u));
	}

	@Override
	public Marker getPrerequisite() {
		for(Marker m : World.markers){
			if(m instanceof WarehouseMarker wm && wm.data.contains(data.get(1))){
				return wm;
			}
		}
		return null;
	}

	@Override
	public boolean canSkipPrerequisite(Unit u) {
		return u.hasQualifiedItem(data.get(1));
	}

	@Override
	public void satisfy(Unit u) {
		int i = 0;
		while(i < u.inventory.size()) {
			Item item = u.inventory.get(i);
			if(Objects.equals(item.qualify(), data.get(1))){
				count -= 1;
				for(ItemIngredient ing : owner.costs){
					if(Objects.equals(ing.qualification, data.get(1))){
						ing.count -= 1;
						break;
					}
				}

				u.inventory.remove(item);
				if(count == 0){
					status = MarkerStatus.FULFILLED;
					World.markers.remove(this);
					owner.checkFulfillment();
					return;
				}
			} else {
				i++;
			}
		}
	}
}
