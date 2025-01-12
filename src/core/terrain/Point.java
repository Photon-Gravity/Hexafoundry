package core.terrain;

import core.World;

import static core.terrain.Axial.SIZE;
import static util.Const.ROOT3;

public class Point {
	float x, y;

	public Point(float x, float y){
		this.x = x;
		this.y = y;
	}

	/**Converts a position of a pixel on a screen into a World Pixel coordinate. World Pixel coordinates are orthogonal coordinates where the X unit vector faces right and the Y unit vector faces down, scaled such that 1 unit is equal to one pixel of the ground texture.*/
	public Point toPX(){
		return new Point(World.cam.x + x/World.cam.zoom, World.cam.y + y/World.cam.zoom);
	}
	/**Converts World Pixel coordinates into a position on the screen. World Pixel coordinates are orthogonal coordinates where the X unit vector faces right and the Y unit vector faces down, scaled such that 1 unit is equal to one pixel of the ground texture..*/
	public Point toScreen(){
		return new Point((x - World.cam.x) * World.cam.zoom, (y - World.cam.y) * World.cam.zoom);
	}

	public Axial toAxial(){
		return new Axial(
				(ROOT3/3 * x - 1f/3 * y) / SIZE,
				(              2f/3 * y) / SIZE
		);
	}
}
