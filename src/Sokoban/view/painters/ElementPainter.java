package Sokoban.view.painters;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import Sokoban.model.Element;
import Sokoban.view.MazeView;

public abstract class ElementPainter {
	
	static final int gridwidth = MazeView.gridwidth;
	
	public abstract void paint (Graphics2D g, Element e);

	protected void drawChar(Graphics2D g, int xpos, int ypos,
			Color color, char ch) {
				char[] chars = {ch};
				String str = new String(chars);
				Font font = new Font(Font.SANS_SERIF, Font.BOLD, gridwidth/2);
			
			    g.setColor(color);
				g.setFont(font);
			
				FontMetrics fontMetrics = g.getFontMetrics();
			    int charheight = fontMetrics.getAscent();
			    int charwidth = fontMetrics.stringWidth(str);
			
				int xchar = (xpos*gridwidth)+(gridwidth-charwidth)/2;
				int ychar = (ypos*gridwidth)+(gridwidth+charheight)/2;
				g.drawString(str, xchar, ychar);
			}
	
}
