package core.terrain;

import static util.Const.HALFROOT3;
import static util.Const.ROOT3;

public class Axial {

	public static final int SIZE = 8;
	float q, r;

	/** Axial is a class that represents a grid position on a hexaagonal grid. It is used to move between the pixelated screen and sprites and the hexagonal building tiles.*/
	public Axial(float q, float r){
		this.q = q;
		this.r = r;
	}

	/**Converts Axial coordinates to World Pixel coordinates*/
	public Point toPX(){
		return new Point(
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
}
