package Sokoban.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Sokoban.model.Pos.Dir;
import Sokoban.view.MazeView;
/**
 * The mode for playing the maze.
 * @author edward
 *
 */
public class PlayHandler extends AbstractActionHandler {

	public PlayHandler(MazeView view) {
		super(view);
	}

	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	public void mouseDragged(MouseEvent arg0) {
	}

	public void mouseMoved(MouseEvent arg0) {
	}

	public void keyPressed(KeyEvent e) {
		if (view.getMaze().getPlayer() == null) return;
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			view.getMaze().getPlayer().move(Dir.WEST);
			break;

		case KeyEvent.VK_RIGHT:
			view.getMaze().getPlayer().move(Dir.EAST);
			break;

		case KeyEvent.VK_UP:
			view.getMaze().getPlayer().move(Dir.NORTH);
			break;

		case KeyEvent.VK_DOWN:
			view.getMaze().getPlayer().move(Dir.SOUTH);
			break;
		default:
		}
		view.repaint();
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	void makeActive() {
	}

	public void paint() {
	}

}
