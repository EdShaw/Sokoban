package Sokoban.controller;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class NullHandler implements ActionHandler {

	/**
	 * This mouse handler does absolutely nothing. Well, ok, not quite.
	 * It implements the Null Object design pattern. But that's it.
	 */

	public static final NullHandler INSTANCE = new NullHandler(null);

	public NullHandler(Object object) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void makeActive() {
	}

	public void paint(Graphics2D g) {
	}

	public void keyPressed(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void paint() {
	}

}
