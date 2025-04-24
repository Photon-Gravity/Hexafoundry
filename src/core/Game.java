package core;

import core.entity.unit.Unit;
import ui.Camera;
import ui.Screen;
import ui.UIButton;
import ui.UIElement;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import static core.World.cam;

public class Game implements Runnable {
	long lastT = System.nanoTime(), delta = 0, accumT = 0, lastC = 0, frameCount = 0;

	public static final double TICK_TIME = 1000000000/60.0;

	public boolean running = true;

	Screen renderScreen;

	public Game(Screen renderScreen){
		this.renderScreen = renderScreen;

		Content.load();

		new UIButton(10, 10, 100, 35, "Spawn Test Drone", ()->{
			new Unit(Content.testDrone, 100, 100, 0);
			return null;
		});

		World.init();
	}

	@Override
	public void run() {
		while(running){
			delta = System.nanoTime() - lastT;
			lastT += delta;
			accumT += delta;

			if(accumT > TICK_TIME){
				World.update();
				accumT -= TICK_TIME;
				renderScreen.repaint();
				frameCount++;
			}

			if(System.currentTimeMillis() - lastC >= 1000){
				lastC = System.currentTimeMillis();
				System.out.println("FPS: " + frameCount);
				System.out.println("Unit Count: " + (Unit.lastID+1));
				frameCount = 0;
			}
		}
	}

	public void handleKeyPress(KeyEvent e){
		if(e.getKeyChar() == 'w'){
			World.cvy = -1;
		} else if(e.getKeyChar() == 's'){
			World.cvy = 1;
		} else if(e.getKeyChar() == 'a'){
			World.cvx = -1;
		} else if(e.getKeyChar() == 'd'){
			World.cvx = 1;
		}
	}

	public void handleKeyRelease(KeyEvent e){
		if(e.getKeyChar() == 'w' || e.getKeyChar() == 's'){
			World.cvy = 0;
		} else if(e.getKeyChar() == 'a' || e.getKeyChar() == 'd'){
			World.cvx = 0;
		}
	}

	public void handleMouseClick(MouseEvent e){
		for(int i=0; i < UIElement.allUI.size(); i++){
			if(UIElement.allUI.get(i).withinBounds(e.getX(), e.getY())) UIElement.allUI.get(i).onClick();
		}
	}

	public void handleMouseWheel(MouseWheelEvent e) {
		float zoomMult = 1 + e.getUnitsToScroll()*-0.1f;

		float oldZoom = cam.zoom;

		cam.zoom *= zoomMult;

		if(cam.zoom < Camera.CAM_ZOOM_MIN) cam.zoom = Camera.CAM_ZOOM_MIN;
		else if(cam.zoom > Camera.CAM_ZOOM_MAX) cam.zoom = Camera.CAM_ZOOM_MAX;

		float realZoomMult = cam.zoom/oldZoom;

		cam.x += e.getX()/oldZoom * (realZoomMult-1)/realZoomMult;
		cam.y += e.getY()/oldZoom * (realZoomMult-1)/realZoomMult;
	}
}
