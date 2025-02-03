package graphics;

import core.terrain.Point;
import ui.Screen;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static core.World.cam;

public class DrawHelper {
	public static Graphics2D g;


	public static void setGraphics(Graphics2D gNew){
		g = gNew;
	}
	public static void drawCircle(float x, float y, float radius){
		g.fillOval((int)((x - cam.x) * cam.zoom), (int)((y - cam.y)*cam.zoom), (int)(radius * cam.zoom), (int)(radius * cam.zoom));
	}

	public static void drawRegion(float x, float y, float rotation, BufferedImage region){
		AffineTransform t = new AffineTransform();
		t.translate((int)((x - cam.x) * cam.zoom), (int)((y - cam.y)*cam.zoom));
		t.scale(cam.zoom, cam.zoom);
		t.rotate(rotation);

		g.drawImage(region, t, null);
	}

	public static void drawRegion(Point pos, float rotation, BufferedImage region){
		drawRegion(pos.x, pos.y, rotation, region);
	}

	public static void drawButton(String text, float x, float y, float width, float height, boolean clicked){
		if(!clicked) color(new Color(227, 222, 119));
		g.fillRect((int)x, (int)y, (int)width,(int)height);
		color(Screen.bgColor);
		g.fillRect((int)x + 5, (int)y + 5, (int)width - 10, (int)height - 10);
		reset();
		if(!clicked) color(new Color(227, 222, 119));
		g.drawString(text, x+10, y + 10+g.getFont().getSize());
		reset();
	}

	public static void color(Color c){
		g.setColor(c);
	}

	public static void reset(){
		g.setColor(Color.WHITE);
	}
}
