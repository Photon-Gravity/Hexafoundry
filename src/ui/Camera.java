package ui;

public class Camera {
	public float zoom, x, y;

	public static final float CAM_ZOOM_MIN = 0.1f, CAM_ZOOM_MAX = 10;
	public Camera(float x, float y, float zoom){
		this.x = x;
		this.y = y;
		this.zoom = zoom;
	}
}
