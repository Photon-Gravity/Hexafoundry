package core.terrain;

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
	@SuppressWarnings("unchecked")
	public E get(Axial pos){
		return (E) contents[width * (int)pos.q + (int)pos.r];
	}

	public void set(E e, int x, int y){
		contents[width*y+x] = e;
	}

	public int width(){
		return width;
	}

	public int height(){
		return height;
	}
}
