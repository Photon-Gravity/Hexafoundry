package core.items;

import core.terrain.Vec;
import graphics.DrawHelper;

import java.awt.image.BufferedImage;

public class Item {
    public ItemType type;
    public AlloyMix composition;

    public Item(ItemType type, AlloyMix composition){
        this.type = type;
        this.composition = composition;
    }


    public void draw(Vec pos){
        BufferedImage temp = DrawHelper.copyImage(type.region);
        DrawHelper.tintTexture(temp, composition.getColor());
        DrawHelper.drawRegion(pos, 0, temp, 0.5f);
    }

    public String qualify(){
        String qualification = "";
        for(Qualification q : Qualification.allQualifications){
            if(q.qualifies(this)){
                qualification += (qualification == "" ? q.name : " " + q.name);
            }
        }
        return qualification;
    }
}
