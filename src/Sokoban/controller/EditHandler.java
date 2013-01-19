package Sokoban.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Sokoban.model.Pos;
import Sokoban.view.MazeView;

/**
 *  Handles the mouse related logic for the edit modes.
 * @author edward
 *
 */

public class EditHandler extends AbstractActionHandler {

	public EditHandler(MazeView view) {
		super(view);
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Pos pos = getEventPos(e);
			setCurrentObj(view.getMaze().getTopObj(pos));
			if (getCurrentObj() != null){
				highlightSquare=true;
				mousePoint = e.getPoint();
			}
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (getCurrentObj() != null){
			highlightSquare=true;
			mousePoint = e.getPoint();
			view.repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (getCurrentObj() != null){
			elementDragFinished(e.getPoint());
		}
		highlightSquare=false;
		view.repaint();
	}


	private void elementDragFinished(Point p) {
		Pos pos = coordinateToMaze(p);
		if (!getCurrentObj().getPos().equals(pos) && !getCurrentObj().moveTo(pos,true)) {
			view.message("Can not move here.");
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}



	public void makeActive() {
	}

	public void paint(Graphics2D g) {
		super.paint(g);
		if (getCurrentObj() != null) {
			Pos currentObjPos = getCurrentObj().getPos();
			Color color = Color.RED;
			Stroke stroke = new BasicStroke( (float) MazeView.gridwidth/20);
			drawGridSquare(g, currentObjPos, color, stroke);
		}
	}

	public void mouseMoved(MouseEvent e) {
	}


	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}
