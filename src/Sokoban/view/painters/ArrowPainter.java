package Sokoban.view.painters;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import Sokoban.model.Arrow;
import Sokoban.model.Element;

public class ArrowPainter extends ElementPainter {

	public static final ArrowPainter INSTANCE = new ArrowPainter();

	private ArrowPainter() {
	}

	public void paint(Graphics2D g2, Element e2) {

		Arrow e = (Arrow) e2;
		int xpaint = e.getPos().getX() * gridwidth;
		int ypaint = e.getPos().getY() * gridwidth;
		Graphics2D g = (Graphics2D) g2.create(xpaint, ypaint, gridwidth,
				gridwidth);

		Stroke s = new BasicStroke((float) gridwidth / 20,
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		Color oldColor = g.getColor();
		Stroke oldStroke = g.getStroke();
		g.setStroke(s);
		g.setColor(Color.BLACK);
		int gap = gridwidth / 10;
		switch (e.getDir()) {
		case SOUTH:
			g.rotate(Math.PI, gridwidth / 2, gridwidth / 2);
			break;
		case EAST:
			g.rotate(Math.PI/2, gridwidth / 2, gridwidth / 2);
			break;
		case NORTH:
			break;
		case WEST:
			g.rotate(3*Math.PI/2, gridwidth / 2, gridwidth / 2);
			break;
		default:
			break;
		}

		g.drawLine(gridwidth / 2, gap, gridwidth / 2, gridwidth - gap);
		g.drawLine(gridwidth / 2, gap, gridwidth / 4, 4 * gap);
		g.drawLine(gridwidth / 2, gap, 3 * gridwidth / 4, 4 * gap);

		g.setStroke(oldStroke);
		g.setColor(oldColor);

	}

}
