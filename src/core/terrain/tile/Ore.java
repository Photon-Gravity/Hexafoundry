package core.terrain.tile;

import core.items.Item;
import core.terrain.Axial;
import core.terrain.Point;
import graphics.DrawHelper;
import util.FileLoader;

import java.awt.image.BufferedImage;

public class Ore {

    BufferedImage texture;

    public int id;

    String name;
    Item dropMaterial;

    public static int lastId = -1;
    public Ore(String name, Item dropMaterial){
        this.id = ++lastId;

        this.name = name;
        this.dropMaterial = dropMaterial;

        texture = FileLoader.find("assets/sprites/tiles/ore/"+name+".png");
    }

    public void drawAt(Axial pos){
        Point drawP = pos.toPX();

        DrawHelper.color(dropMaterial.composition.getColor());
        DrawHelper.drawRegion(drawP, 0, texture);
        DrawHelper.reset();
    }
}
