package core;

import core.entity.unit.Unit;
import ui.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import static core.World.cam;
import static core.World.cursor;

public class Game implements Runnable {
	long lastT = System.nanoTime(), delta = 0, accumT = 0, lastC = 0, frameCount = 0;

	public static final double TICK_TIME = 1000000000/60.0;

	public boolean running = true;

	Screen renderScreen;

	public static PointerInfo mouseInfo;

	public Game(Screen renderScreen){
		this.renderScreen = renderScreen;

		mouseInfo = MouseInfo.getPointerInfo();

		Content.load();

		new UIButton(10, 10, 150, 35, "Spawn Test Drone", ()->{
			new Unit(Content.testDrone, 100, 100, 0);
			return null;
		});

		new UIImageButton(170, 10, 150, 35, Content.basicDrill.texture, ()->{
			cursor = Content.basicDrill;
			return null;
		});

		new UIImageButton(225, 10, 150, 35, Content.pneumaticDuct.texture, ()->{
			cursor = Content.pneumaticDuct;
			return null;
		});

		new UIImageButton(250, 10, 150, 35, Content.merger.texture, ()->{
			cursor = Content.merger;
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
				mouseInfo = MouseInfo.getPointerInfo();
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
		if(e.getKeyChar() == 'w'){ //camera movement
			World.cvy = -1;
		} else if(e.getKeyChar() == 's'){
			World.cvy = 1;
		} else if(e.getKeyChar() == 'a'){
			World.cvx = -1;
		} else if(e.getKeyChar() == 'd'){
			World.cvx = 1;
		}
		else if(e.getKeyChar() == 'q'){ //controls
			World.cursor = null;
		} else if(e.getKeyChar() == 'r'){
			World.cursorRotation++;
			World.cursorRotation %= 6;
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
		boolean buttonClicked = false;
		for(int i=0; i < UIElement.allUI.size(); i++){
			if(UIElement.allUI.get(i).withinBounds(e.getX(), e.getY())){
				UIElement.allUI.get(i).onClick();
				buttonClicked = true;
			}
		}

		if(!buttonClicked && e.getButton() == MouseEvent.BUTTON1) {
			World.tryPlaceCursor();
		} else if (!buttonClicked && e.getButton() == MouseEvent.BUTTON3) {
			World.tryDeconstructCursor();
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
