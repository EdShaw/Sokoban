package Sokoban.view.painters;

import java.awt.Color;
import java.awt.Graphics2D;

import Sokoban.model.Element;
import Sokoban.model.Wall;
import Sokoban.view.MazeView;


public class WallPainter extends ElementPainter {


	// A singleton instance of each painter handles painting all of each MazeElement type.
	public static final ElementPainter INSTANCE = new WallPainter();
	protected WallPainter() {
	}

	public void paint(Graphics2D g, Element e) {

		Wall wallE = (Wall) e;

		int gridwidth = MazeView.gridwidth;
		int xpos = e.getPos().getX();
		int ypos = e.getPos().getY();
		int xpaint = xpos * gridwidth;
		int ypaint = ypos * gridwidth;

		Color oldColor = g.getColor();

		g.setColor(Color.BLACK);
		g.fillRect(xpaint, ypaint, gridwidth, gridwidth);
		Color color = Color.WHITE;
		char ch = wallE.getChar();

		drawChar(g, xpos, ypos, color, ch);

		g.setColor(oldColor);
	}

}
