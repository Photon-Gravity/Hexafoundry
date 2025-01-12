package core.terrain;

import java.util.ArrayList;

public class HexGrid<E> {


	Object[] contents;
	int width, height;
	public HexGrid(int width, int height){
		this.width = width;
		this.height = height;
		this.contents = new Object[width * height];
	}

	@SuppressWarnings("unchecked")
	public E get(int x, int y){
		return (E) contents[width * y + x];
	}

	public void set(E e, int x, int y){
		contents[width*y+x] = e;
	}
}
