package core.terrain.constrution.types;

import core.World;
import core.entity.marker.Marker;
import core.entity.marker.WarehouseMarker;
import core.items.Item;
import core.terrain.Axial;
import core.terrain.constrution.blocks.Block;

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
	public void onDestroy(Block instance) {
		super.onDestroy(instance);
		for(int i =0; i < World.markers.size();i++){
			if(World.markers.get(i).position.eq(instance.pos)){
				World.markers.remove(i);
				i--;
			}
		}
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

	@Override
	public void draw(Block block) {
		super.draw(block);
		if(block.items.size() > 0 && block.items.get(0) != null){
			block.items.get(0).draw(block.pos.toPX());
		}
	}
}
