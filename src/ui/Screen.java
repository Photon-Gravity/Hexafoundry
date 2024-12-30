package ui;

import core.Game;
import core.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Screen extends JPanel implements KeyListener, MouseListener {
	public int width, height, mouseX, mouseY;

	Graphics2D g2D;
	public static Color bgColor = new Color(0, 16, 48);

	Game game;

	public Screen(int width, int height) {
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);

		this.width = width;
		this.height = height;

		game = new Game(this);

		Thread gameThread = new Thread(game);
		gameThread.start();

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g2D = (Graphics2D)g;
		g2D.clearRect(0, 0, width, height);
		g2D.setColor(bgColor);
		g2D.fillRect(0, 0, width, height);
		g2D.setColor(Color.WHITE);

		World.render(g2D);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		game.handleKeyPress(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		game.handleKeyRelease(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse click at (" + e.getX() + ", " + e.getY() + ")");
		game.handleMouseClick(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse press");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Mouse release");
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
