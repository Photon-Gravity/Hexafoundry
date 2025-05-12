package core.terrain;

import core.World;
import core.entity.marker.WarehouseMarker;
import core.items.Item;
import core.terrain.constrution.Block;
import core.terrain.constrution.MultiBlockType;
import core.entity.marker.Marker;

public class WarehouseType extends MultiBlockType {
	public int itemCap = 100;
	public WarehouseType(String name) {
		super(name);

	}

	@Override
	public boolean acceptsItem(Item item, Block source, Block target) {
		return target.getItems().size() < itemCap;
	}

	@Override
	public void handleItem(Item item, Block source, Block target) {
		super.handleItem(item, source, target);
	}


	@Override
	public void placeBlock(Axial pos, int rotation) {
		super.placeBlock(pos, rotation);
		System.out.println("Marker SHOULD be added");
		World.markers.add(new WarehouseMarker(pos));
	}

	@Override
	public void update(Block block) {
		super.update(block);

		for(Marker m : World.markers){
			if(m.position.eq(block.pos)){
				for(int i =0; i < block.getItems().size();i++){
					if(m.data.size() <= i){
						m.data.add(block.getItems().get(i).qualify());
					} else {
						m.data.set(i, block.getItems().get(i).qualify());
					}
				}
				return;
			}
		}
	}
}
