package ui;

public class Camera {
	public float zoom, x, y;

	public static final float CAM_ZOOM_MIN = 0.25f, CAM_ZOOM_MAX = 4;
	public Camera(float x, float y, float zoom){
		this.x = x;
		this.y = y;
		this.zoom = zoom;
	}
}
