package Sokoban.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;

import javax.swing.JPanel;

import Sokoban.controller.ActionHandler;
import Sokoban.controller.CreateHandler;
import Sokoban.controller.EditHandler;
import Sokoban.controller.NullHandler;
import Sokoban.controller.PlayHandler;
import Sokoban.controller.PlayerCreateHandler;
import Sokoban.controller.ToolManager;
import Sokoban.model.Arrow;
import Sokoban.model.Box;
import Sokoban.model.Element;
import Sokoban.model.MazeInterface;
import Sokoban.model.Player;
import Sokoban.model.Target;
import Sokoban.model.Wall;
import Sokoban.view.painters.ArrowPainter;
import Sokoban.view.painters.BoxPainter;
import Sokoban.view.painters.ElementPainter;
import Sokoban.view.painters.NullPainter;
import Sokoban.view.painters.PlayerPainter;
import Sokoban.view.painters.TargetPainter;
import Sokoban.view.painters.WallPainter;


public class MazeView extends JPanel {

	private final MazeInterface maze;
	public ToolManager toolManager;
	public MazeViewListener mazeViewListener;
	private ActionHandler currentMouseHandler;
	private final ActionHandler toolEdit;
	private final ActionHandler toolWall;
	private final ActionHandler toolPlayer;
	private final ActionHandler toolCrate;
	private final ActionHandler toolTarget;
	private final ActionHandler toolArrow;
	private final ActionHandler toolPlay;
	public int ytranslate = 0;
	public int xtranslate = 0;


	/**
	 * Registry of Elements and their painters.
	 * Model View separation is maintained as the model knows nothing of the painters.
	 */
	protected static final Map<Class<? extends Element>,ElementPainter> painters = 	new HashMap<Class<? extends Element>,ElementPainter>();
	static {
		painters.put(Wall.class, WallPainter.INSTANCE);
		painters.put(Player.class, PlayerPainter.INSTANCE);
		painters.put(Box.class, BoxPainter.INSTANCE);
		painters.put(Arrow.class, ArrowPainter.INSTANCE);
		painters.put(Target.class, TargetPainter.INSTANCE);
	}

	private boolean printMessage;

	public static final int gridwidth = 50;

	private String message;
	private final int mazeheight;
	private final int mazewidth;

	public MazeView (MazeInterface maze, ToolManager toolManager){
		this.maze=maze;
		this.toolManager = toolManager;
		MouseEventForwarder forwarder =	new MouseEventForwarder();
		mazeViewListener = new MazeViewListener(toolManager, this);
		addMouseListener(forwarder);
		addMouseMotionListener(forwarder);
		addKeyListener(forwarder);
		maze.mazeManager.addListener(mazeViewListener);
		toolManager.addListener(mazeViewListener);
		this.setFocusable(true);
		this.requestFocusInWindow();
		mazewidth = maze.xsize*gridwidth;
		mazeheight = maze.ysize*gridwidth;
		setPreferredSize(new Dimension(mazewidth, mazeheight));
		currentMouseHandler = NullHandler.INSTANCE;
		toolEdit = new EditHandler(this);
		toolWall = new CreateHandler(this, Wall.class);
		toolPlayer = new PlayerCreateHandler(this);
		toolCrate = new CreateHandler(this, Box.class);
		toolTarget = new CreateHandler(this, Target.class);
		toolArrow = new CreateHandler(this, Arrow.class);
		toolPlay = new PlayHandler(this);
		updateMouseHandler();
	}


	protected ElementPainter getMazeElementPainter(Class<? extends Element> e) {
		ElementPainter painter;
		painter = NullPainter.INSTANCE;
		if (painters.containsKey(e)) {painter = painters.get(e);}
		return painter;
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int paneheight = this.getHeight();
        int panewidth = this.getWidth();
        Color oldColor = g.getColor();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
        xtranslate = (panewidth-mazewidth)/2;
		ytranslate = (paneheight-mazeheight)/2;
		g.translate(xtranslate, ytranslate);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getMaze().xsize*gridwidth, getMaze().ysize*gridwidth);
		g.setColor(oldColor);

		Iterator<Element> iterator = getMaze().iterator();
		while (iterator.hasNext()) {
			Element e = iterator.next();
			System.out.print(e);
			getMazeElementPainter(e.getClass()).paint(g2d,e);
		}
		currentMouseHandler.paint(g2d);
		if (printMessage) {
			g.setColor(new Color(20,20,20,200));
			int messageWidth = g.getFontMetrics().stringWidth(message);
			int messageHeight = g.getFontMetrics().getHeight();
			int messagex1 = (mazewidth-messageWidth)/2-100;
			int messagey1 = (mazeheight-messageHeight)/2-30;
			int messagex2 = messageWidth+200;
			int messagey2 = messageHeight+60;
			g.fillRoundRect(messagex1, messagey1, messagex2, messagey2, 20, 20);
			int strx = messagex1 + messagex2/2 - messageWidth/2;
			int stry = messagey1 + messagey2/2 + messageHeight/2;
			g.setColor(Color.WHITE);
			g.drawString(message, strx, stry);
			g.setColor(oldColor);
		}
	}

	/**
	 * A class designed to forward all mouse events to
	 * the current mouse handler. The current mouse
	 * handler is determined by the mode selected from
	 * the toolbar.
	 *
	 */

	class MouseEventForwarder implements ActionHandler {
		public void mouseEntered(MouseEvent e) {
			currentMouseHandler.mouseEntered(e);
		}
		public void mouseExited(MouseEvent e) {
			currentMouseHandler.mouseExited(e);
		}
		public void mouseClicked(MouseEvent e) {
			currentMouseHandler.mouseClicked(e);
		}
		public void mousePressed(MouseEvent e) {
			currentMouseHandler.mousePressed(e);
		}
		public void mouseReleased(MouseEvent e) {
			currentMouseHandler.mouseReleased(e);
		}
		public void mouseDragged(MouseEvent e) {
			currentMouseHandler.mouseDragged(e);
		}
		public void mouseMoved(MouseEvent e) {
			currentMouseHandler.mouseMoved(e);
		}
		public void keyPressed(KeyEvent e) {
			currentMouseHandler.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			currentMouseHandler.keyReleased(e);
		}
		public void keyTyped(KeyEvent e) {
			currentMouseHandler.keyTyped(e);
		}

		public void paint(Graphics2D g) {
			currentMouseHandler.paint(g);
		}
	}

	public void updateMouseHandler(){
		if (getMaze() == null) {
			currentMouseHandler = NullHandler.INSTANCE;
		} else {
			switch (toolManager.getTool()) {
				case EDIT:
					currentMouseHandler = toolEdit;
					break;
				case CREATEWALL:
					currentMouseHandler = toolWall;
					break;
				case CREATETARGET:
					currentMouseHandler = toolTarget;
					break;
				case CREATEBOX:
					currentMouseHandler = toolCrate;
					break;
				case CREATEARROW:
					currentMouseHandler = toolArrow;
					break;
				case CREATEPLAYER:
					currentMouseHandler = toolPlayer;
					break;
				case PLAY:
					currentMouseHandler = toolPlay;
					break;
				default:
					currentMouseHandler = NullHandler.INSTANCE;
			}
		}
		repaint();
	}

	Timer timer = new Timer();

	public void message(String string) {
		System.out.println(string);
		message = string;
		Thread task = new Thread(){
			public void run() {
				printMessage = true;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				printMessage = false;
				}
		};
		task.start();
	}

	/**
	 * @return the maze
	 */
	public MazeInterface getMaze() {
		return maze;
	}
}
