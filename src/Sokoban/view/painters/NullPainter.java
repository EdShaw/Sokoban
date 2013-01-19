package Sokoban.view.painters;

import java.awt.Color;
import java.awt.Graphics2D;

import Sokoban.model.Element;

public class NullPainter extends ElementPainter {

	public static NullPainter INSTANCE = new NullPainter();
	private NullPainter(){
	}

	public void paint(Graphics2D g, Element e) {
		drawChar(g, e.getPos().getX(), e.getPos().getY(), Color.RED, '?');
	}

}
