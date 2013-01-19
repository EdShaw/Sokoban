package Sokoban.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseEvent;

import Sokoban.model.Element;
import Sokoban.model.Pos;
import Sokoban.view.MazeView;

/**
 * MouseHandler and it's subclasses handle all mouse related logic.
 * It uses the template pattern to avoid duplicating
 * common code between the edit and create modes.
 *
 * @author
 *
 */

public abstract class AbstractActionHandler implements ActionHandler {

	MazeView view;
	protected boolean highlightSquare;
	protected Point mousePoint;
	private final ToolManager toolManager;


	public AbstractActionHandler(MazeView view){
		this.view = view;
		this.toolManager = view.toolManager;
	}

	/**
	 * Called by the MazeView to paint the MouseHandler.
	 * Used to paint information relating to the mouse handling.
	 *
	 * @param g The object on which is painted.
	 */

	public void mouseClicked(MouseEvent e){
		view.requestFocusInWindow();
	}

	public void paint(Graphics2D g){
		if (highlightSquare && mousePoint!=null) {
			Pos pos = coordinateToMaze(mousePoint);
			Color color = Color.GRAY;
			Stroke stroke = new BasicStroke( (float) MazeView.gridwidth/20);
			drawGridSquare(g, pos, color, stroke);
		}

	}

	protected void drawGridSquare(Graphics2D g, Pos pos,
			Color color, Stroke stroke) {
				int xpaint = pos.getX() * MazeView.gridwidth;
				int ypaint = pos.getY() * MazeView.gridwidth;
				Color oldColor = g.getColor();
				Stroke oldStroke = g.getStroke();
				g.setColor(color);
				g.setStroke(stroke);
				g.drawRect(xpaint, ypaint, MazeView.gridwidth, MazeView.gridwidth);
				g.setStroke(oldStroke);
				g.setColor(oldColor);
			}

	protected Pos getEventPos(MouseEvent e) {
		Point point = e.getPoint();
		return coordinateToMaze(point);
	}

	protected Pos coordinateToMaze(Point p) {
		int xpos = (int) Math.floor((p.x - view.xtranslate)/(float)MazeView.gridwidth);
		int ypos = (int) Math.floor((p.y - view.ytranslate)/(float)MazeView.gridwidth);
		return new Pos(xpos,ypos);
	}

	/**
	 * @return the currentObj
	 */
	protected Element getCurrentObj() {
		return toolManager.getHighlightedElement();
	}

	/**
	 * @param currentObj the currentObj to set
	 */
	protected void setCurrentObj(Element currentObj) {
		toolManager.setHighlightedObject(currentObj);
	}

}
