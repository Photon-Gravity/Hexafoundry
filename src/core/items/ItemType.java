package core.items;

import core.unit.UnitAI;
import util.FileLoader;

import java.awt.image.BufferedImage;

public class ItemType {
    public int id;
    public static int lastId = -1;
    String name;

    public BufferedImage region;

    public ItemType(String name) {
        this.id = ++lastId;

        this.name = name;

        this.region = FileLoader.find("assets/sprites/items/" + name + ".png");
    }
}
