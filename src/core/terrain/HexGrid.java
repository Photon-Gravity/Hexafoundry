package core.terrain;

import java.util.Iterator;

public class HexGrid<E> implements Iterable<E> {


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
		return (E) contents[width * (int)pos.r + (int)pos.q];
	}

	public void set(E e, int x, int y){
		contents[width*y+x] = e;
	}

	public void set(E e, Axial pos){
		contents[width*(int)pos.r+(int)pos.q] = e;
	}

	public int width(){
		return width;
	}

	public int height(){
		return height;
	}

	@Override
	public Iterator iterator() {
		return new HexIterator();
	}

	public boolean inBounds(Axial pos){
		return pos.q >= 0 && pos.r >= 0 && pos.q < width && pos.r < height;
	}

	class HexIterator implements Iterator<E> {

		private int i = 0, j = 0;
		@Override
		public boolean hasNext() {
			return i < width-1 || j < height-1;
		}

		@Override
		public E next() {
			if(i < width-1){
				i++;
			} else {
				i = 0;
				j++;
			}

			return get(i, j);
		}
	}
}
