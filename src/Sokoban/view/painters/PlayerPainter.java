package Sokoban.view.painters;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import Sokoban.model.Element;
import Sokoban.model.Player;

public class PlayerPainter extends ElementPainter {

	public static final PlayerPainter INSTANCE = new PlayerPainter();
	private PlayerPainter(){
	}

	public void paint(Graphics2D g, Element e2) {

		Player e = (Player) e2;

		int xpaint = e.getPos().getX() * gridwidth;
		int ypaint = e.getPos().getY() * gridwidth;
		Stroke s = new BasicStroke((float) gridwidth/20, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		Color oldColor = g.getColor();
		Stroke oldStroke = g.getStroke();
		g.setStroke(s);
		g.setColor(Color.GREEN);
		int gap = gridwidth/10;
		int x1 = xpaint+gridwidth/2;
		int y1 = ypaint+gridwidth/2;
		int x2 = x1;
		int y2 = y1;

		switch (e.getDir()) {
			case NORTH:
				y2 = ypaint + gap;
				break;
			case EAST:
				x2 = xpaint + gridwidth - gap;
				break;
			case SOUTH:
				y2 = ypaint + gridwidth - gap;
				break;
			case WEST:
				x2 = xpaint + gap;
				break;
			default:
				break;
		}
		g.drawLine(x1, y1, x2, y2);
		g.drawOval(xpaint+gap, ypaint+gap, gridwidth-2*gap, gridwidth-2*gap);
		g.setStroke(oldStroke);
		g.setColor(oldColor);


	}


}
