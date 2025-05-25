package core.terrain.ghost;

import core.World;
import core.entity.marker.GhostMarker;
import core.items.ItemIngredient;
import core.terrain.Axial;
import core.terrain.constrution.types.BlockType;

import java.util.ArrayList;

public class Ghost {
	public Axial pos;
	public int rotation;
	public BlockType type;
	public ArrayList<ItemIngredient> costs = new ArrayList<>();
	public Ghost(Axial pos, int rotation, BlockType type){
		this.pos = pos;
		this.rotation = rotation;
		this.type = type;

		for(ItemIngredient ingredient : type.cost){
			costs.add(new ItemIngredient(ingredient.qualification, ingredient.count));
			GhostMarker marker = new GhostMarker(pos, "ghost", this);
			marker.data.set(1, ingredient.qualification);
			marker.count = ingredient.count;
			World.markers.add(marker);

			System.out.println("Created ghost marker for ingredient: " + ingredient.qualification + ", " + ingredient.count);
		}
	}

	public void checkFulfillment(){
		for(ItemIngredient ing : costs){
			System.out.println(ing.qualification + ", " + ing.count);

			if(ing.count > 0){
				return;
			}
		}
		type.placeBlock(pos, rotation);
		World.ghosts.remove(this);
	}
}
