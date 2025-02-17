package core.items;

import core.terrain.Point;
import graphics.DrawHelper;

public class Item {
    public ItemType type;
    public AlloyMix composition;

    public Item(ItemType type, AlloyMix composition){
        this.type = type;
        this.composition = composition;
    }


    public void draw(Point pos){
        DrawHelper.color(composition.getColor());
        DrawHelper.drawRegion(pos, 0, type.region);
        DrawHelper.reset();
    }
}
