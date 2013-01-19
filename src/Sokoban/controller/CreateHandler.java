package Sokoban.controller;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;

import Sokoban.model.Element;
import Sokoban.model.MazeInterface;
import Sokoban.model.Pos;
import Sokoban.view.MazeView;

/**
 * Handles the mouse related logic for the Create modes.
 *
 * @author edward
 *
 */

public class CreateHandler extends AbstractActionHandler {

	private Constructor<? extends Element> elementConstructor;

	public CreateHandler(MazeView view, Class<? extends Element> elementClass) {
		super(view);
		highlightSquare = true;
		// All elements should have a Element(Maze, Pos) constructor, which gives sane defaults for Element type specific construction.
		Class<?>[] args = {MazeInterface.class, Pos.class};
		try {
			elementConstructor = elementClass.getConstructor(args);
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		}
	}

	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		Pos pos = getEventPos(e);

		Object[] initargs = {view.getMaze(), pos};
		Element element = null;
		try {
			element = elementConstructor.newInstance(initargs);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (!view.getMaze().add(element)){
			view.message("Can't create here.");
		}
		view.repaint();

	}

	public void setMouseHandler(AbstractActionHandler h){

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
		mousePoint = e.getPoint();
		view.repaint();
	}

	void makeActive() {
	}

	public void paint(Graphics2D g) {
		super.paint(g);
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}
