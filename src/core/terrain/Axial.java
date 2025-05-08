package core.terrain;

import static util.Const.HALFROOT3;
import static util.Const.ROOT3;

public class Axial {
	public static Axial[] d6 = new Axial[]{new Axial(1, 0), new Axial(1, -1), new Axial(0, -1), new Axial(-1, 0), new Axial(-1, 1), new Axial(0, 1)};
	public static final int SIZE = 8;
	float q, r;

	/** Axial is a class that represents a grid position on a hexaagonal grid. It is used to move between the pixelated screen and sprites and the hexagonal building tiles.*/
	public Axial(float q, float r){
		this.q = q;
		this.r = r;
	}

	/**Converts Axial coordinates to World Pixel coordinates*/
	public Vec toPX(){
		return new Vec(
				SIZE * (ROOT3 * q + HALFROOT3 * r),
				SIZE * (                 3f/2 * r)
		);
	}

	/**Rounds the coordinates to the nearest integer position.*/
	public void round(){
		var s = -q-r;

		var qR = Math.round(q);
		var rR = Math.round(r);
		var sR = Math.round(s);

		var q_diff = Math.abs(qR - q);
		var r_diff = Math.abs(rR - r);
		var s_diff = Math.abs(sR - s);

		if (q_diff > r_diff && q_diff > s_diff){
			qR = -rR-sR;
		} else if (r_diff > s_diff){
			rR = -qR-sR;
		}

		q = qR;
		r = rR;
	}

	public void set(int q, int r){
		this.q = q;
		this.r = r;
	}

	public Axial trns(Axial by){
		return new Axial(q + by.q, r + by.r);
	}

	public Axial rotate(int rotation){
		var s = -q-r;

		return switch (rotation%6) {
			case 0 -> new Axial(q, r);
			case 1 -> new Axial(-s, -q);
			case 2 -> new Axial(r, s);
			case 3 -> new Axial(-q, -r);
			case 4 -> new Axial(s, q);
			default -> new Axial(-r, -s);
		};
	}

	public boolean eq(Axial other){
		return this.q == other.q && this.r == other.r;
	}

	public Axial scl(float factor){
		return new Axial(q * factor, r * factor);
	}
}
