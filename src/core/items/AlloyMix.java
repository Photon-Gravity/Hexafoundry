package core.items;

import java.awt.*;
import java.util.HashMap;

public class AlloyMix {
	HashMap<MetalType, Float> composition = new HashMap<>();

	/**Creates a new AlloyMix out of provided parameters. The parameters must be an alternating sequence of MetalTypes and floats.*/
	public AlloyMix(Object... objects){
		if((objects.length & 1) == 0){
			for(int i=0; i < objects.length; i+=2){
				if(objects[i] instanceof MetalType m && objects[i+1] instanceof Float f && !composition.containsKey(m)){
					composition.put(m, f);
				} else {
					throw new IllegalArgumentException("Incorrect argument type for AlloyMix constructor.");
				}
			}

			float sum = 0;
			for(int i=0; i < composition.size(); i++){
				sum += composition.get(composition.keySet().toArray()[i]);
			}
			if(sum > 1f + 0.0001f || sum < 1f - 0.0001f) throw new IllegalArgumentException("Alloy proprotions do not add up to 100%.");
		} else {
			throw new IllegalArgumentException("Odd argument count for AlloyMix constructor. Cannot map arguments to composition.");
		}
	}

	/**Creates a new AlloyMix out of a ready HashMap. Still checks for valid proportion.*/
	public AlloyMix(HashMap<MetalType, Float> composition){
		this.composition = composition;
		float sum = 0;
		for(int i=0; i < composition.size(); i++){
			sum += (Float)composition.entrySet().toArray()[i];
		}
		if(sum > 1f + 0.0001f || sum < 1f - 0.0001f) throw new IllegalArgumentException("Alloy proprotions do not add up to 100%.");
	}

	/**Mixes all provided alloys in equal proportions*/
	public static AlloyMix mix(AlloyMix... alloys){

		HashMap<MetalType, Float> comp = new HashMap<>();

		for (AlloyMix alloy : alloys) {
			for (int j = 0; j < alloy.composition.size(); j++) {
				MetalType key = (MetalType) alloy.composition.keySet().toArray()[j];
				if (comp.containsKey(key)) {
					comp.put(key, comp.get(key) + alloy.composition.get(key));
				} else {
					comp.put(key, alloy.composition.get(key));
				}
			}
		}

		for(int i=0; i < comp.size();i++){
			MetalType key = (MetalType) comp.keySet().toArray()[i];

			comp.put(key, comp.get(key)/alloys.length);
		}

		return new AlloyMix(comp);
	}

	/**Checks whenever this alloy is close enough to a target alloy.
	 *@param tolerance Tolerated proportion of actual metal to target. Must be less than one, otherwise always returns false.	*/
	public boolean closeEnoughTo(AlloyMix target, float tolerance){
		for(int i=0; i < target.composition.size();i++){
			MetalType key = (MetalType) target.composition.keySet().toArray()[i];

			//alloy does not fit if it doesn't contain any of the required metals or the concentration is outside the tolerable range.
			if(!composition.containsKey(key) || !(target.composition.get(key) * tolerance < composition.get(key) && target.composition.get(key) > composition.get(key) * tolerance)) return false;
		}
		return true;
	}

	public Color getColor(){
		int r = 0, g = 0, b = 0;

		for(MetalType metal : composition.keySet()){
			r += (int)(metal.color.getRed() * composition.get(metal));
			g += (int)(metal.color.getGreen() * composition.get(metal));
			b += (int)(metal.color.getBlue() * composition.get(metal));
		}

		return new Color(r, g, b);
	}
}
