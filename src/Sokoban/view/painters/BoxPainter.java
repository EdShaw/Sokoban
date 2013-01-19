package Sokoban.view.painters;

import java.awt.Color;
import java.awt.Graphics2D;

import Sokoban.model.Box;
import Sokoban.model.Element;
import Sokoban.view.MazeView;


public class BoxPainter extends ElementPainter {


	// A singleton instance of each painter handles painting all of each MazeElement type.
	public static final ElementPainter INSTANCE = new BoxPainter();
	protected BoxPainter() {
	}

	public void paint(Graphics2D g, Element e2) {

		Box e = (Box) e2;

		int gridwidth = MazeView.gridwidth;
		int xpos = e.getPos().getX();
		int ypos = e.getPos().getY();
		int xpaint = xpos * gridwidth;
		int ypaint = ypos * gridwidth;

		Color oldColor = g.getColor();

		g.setColor(e.getColor());
		g.fillRoundRect(xpaint, ypaint, gridwidth, gridwidth, gridwidth/2, gridwidth/2);

		g.setColor(oldColor);
	}

}
