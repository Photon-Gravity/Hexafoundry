package ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{

	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	public MainWindow() {
		Screen screen = new Screen(width, height);
		add(screen);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setTitle("Hexafoundry a0.0");
		setVisible(true);
	}
}
