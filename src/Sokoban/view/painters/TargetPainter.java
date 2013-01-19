package Sokoban.view.painters;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import Sokoban.model.Element;
import Sokoban.model.Target;

public class TargetPainter extends ElementPainter {

	public static final TargetPainter INSTANCE = new TargetPainter();

	private TargetPainter(){
	}

	public void paint(Graphics2D g, Element e2) {

		Target e = (Target) e2;

		int xpaint = e.getPos().getX() * gridwidth;
		int ypaint = e.getPos().getY() * gridwidth;
		Stroke s = new BasicStroke((float) gridwidth/20, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		Color oldColor = g.getColor();
		Stroke oldStroke = g.getStroke();
		g.setStroke(s);
		g.setColor(Color.PINK);
		int gap = (int)(gridwidth/2.5);
		g.fillOval(xpaint+gap, ypaint+gap, gridwidth-2*gap, gridwidth-2*gap);
		g.setStroke(oldStroke);
		g.setColor(oldColor);


	}


}
