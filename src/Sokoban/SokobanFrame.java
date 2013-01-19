package Sokoban;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import Sokoban.controller.ToolManager;
import Sokoban.model.MazeInterface;
import Sokoban.model.Player;
import Sokoban.model.Pos;
import Sokoban.model.Pos.Dir;
import Sokoban.model.Wall;
import Sokoban.view.MazeView;

/**
 * Creates a window and assembles the mazeView, the initial maze and toolManager.
 *
 */
public class SokobanFrame extends JFrame {

	private final ToolManager toolManager;
	private final MazeInterface maze;


	public SokobanFrame() {
		super("The Maze Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		maze = buildMaze();

		toolManager = new ToolManager(maze);
		JPanel mazeView = new MazeView(maze, toolManager);

		JToolBar toolbar = new SokobanFrameToolbar(maze, toolManager);

		// Put the mazeView in a scrollPane, incase the mazeView is too large for the window.
		JScrollPane scrollPane = new JScrollPane(mazeView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED	, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


		// The SokobanFrame will contain a toolbar and a mazeView wrapped in a ScrollPane.
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.add(BorderLayout.NORTH, toolbar);
		contentPane.add(BorderLayout.CENTER, scrollPane);
		contentPane.setPreferredSize(new Dimension(800, 600));

		setContentPane(contentPane);
		pack();
		scrollPane.setFocusable(false);
		toolbar.setFocusable(false);
	}


	private MazeInterface buildMaze() {
		MazeInterface maze = new MazeInterface(10,10);
		maze.add(new Wall(maze, new Pos(0,0), 'A'));
		maze.add(new Wall(maze, new Pos(1,0), 'B'));
		maze.add(new Wall(maze, new Pos(2,0), 'C'));
		maze.add(new Wall(maze, new Pos(0,1), 'D'));
		maze.add(new Wall(maze, new Pos(1,1), 'E'));
		maze.add(new Wall(maze, new Pos(3,3), '!'));
		maze.add(new Wall(maze, new Pos(3,4), '?'));
		maze.add(new Wall(maze, new Pos(4,3), '@'));
		maze.add(new Wall(maze, new Pos(4,4), '|'));

		maze.add(new Player(maze, new Pos(2,2), Dir.STILL));
		return maze;
	}

	public static void main(String[] args) {
		SokobanFrame sokobanFrame = new SokobanFrame();
		sokobanFrame.setVisible(true);
	}
}
