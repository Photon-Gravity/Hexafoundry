package core.items;

import java.awt.*;

public class MetalType {
	public int id;
	public static int lastId = -1;
	String name;

	public Color color;

	public MetalType(String name){
		this.id = ++lastId;

		this.name = name;
	}

	public MetalType(String name, Color color){
		this.id = ++lastId;

		this.name = name;
		this.color = color;
	}
}
