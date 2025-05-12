package graphics;

import core.terrain.Vec;
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

	public static void  drawCircle(Vec pos, float radius){
		drawCircle(pos.x, pos.y, radius);
	}

	public static void drawRegion(float x, float y, float rotation, BufferedImage region){
		x -= region.getWidth()/2f;
		y -= region.getHeight()/2f;

		AffineTransform t = new AffineTransform();
		t.translate((int)((x - cam.x) * cam.zoom), (int)((y - cam.y)*cam.zoom));
		t.scale(cam.zoom, cam.zoom);
		t.rotate(rotation);

		g.drawImage(region, t, null);
	}

	public static void drawRegion(Vec pos, float rotation, BufferedImage region, float scl){
		float x = pos.x - region.getWidth()/2f * scl;
		float y = pos.y - region.getHeight()/2f * scl;

		AffineTransform t = new AffineTransform();
		t.translate((int)((x - cam.x) * cam.zoom), (int)((y - cam.y)*cam.zoom));
		t.scale(cam.zoom, cam.zoom);
		t.scale(scl, scl);
		t.rotate(rotation);

		g.drawImage(region, t, null);
	}



	public static void drawRegion(Vec pos, float rotation, BufferedImage region){
		drawRegion(pos.x, pos.y, rotation, region);
	}
	public static void drawRegionColored(Vec pos, float rotation, BufferedImage region, Color color){
		AffineTransform t = new AffineTransform();
		t.translate((int)((pos.x- cam.x) * cam.zoom), (int)((pos.y - cam.y)*cam.zoom));
		t.scale(cam.zoom, cam.zoom);
		t.rotate(rotation);

		//g.drawImage(region, t, null);
		//g.setXORMode(color);
		//g.drawImage(region, t, null);
		//g.setXORMode(new Color(255, 255, 255, 255));
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

	public static void drawImageButton(BufferedImage icon, float x, float y, float width, float height, boolean clicked){
		if(!clicked) color(new Color(227, 222, 119));
		g.fillRect((int)x, (int)y, (int)width,(int)height);
		color(Screen.bgColor);
		g.fillRect((int)x + 5, (int)y + 5, (int)width - 10, (int)height - 10);
		reset();
		if(!clicked) color(new Color(227, 222, 119));
		g.drawImage(icon, (int)x+10, (int)y + 10, null);
		reset();
	}

	public static void color(Color c){
		g.setColor(c);
	}

	public static void reset(){
		g.setColor(Color.WHITE);
	}

	public static void tintTexture(BufferedImage texture, Color color) {
		for(int i=0; i < texture.getWidth(); i++){
			for(int j=0; j < texture.getHeight(); j++){
				int pixel = texture.getRGB(i, j);

				int newPixel = (int)(color.getAlpha()/255f * (pixel & 0xFF000000)) + (int)(color.getRed()/255f * (pixel & 0xFF0000)) + (int)(color.getGreen()/255f * (pixel & 0xFF00)) + (int)(color.getBlue()/255f * (pixel & 0xFF));

				texture.setRGB(i, j, newPixel);
			}
		}
	}

	public static BufferedImage copyImage(BufferedImage source){
		BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
		Graphics g = b.getGraphics();
		g.drawImage(source, 0, 0, null);
		g.dispose();
		return b;
	}
}
