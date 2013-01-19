package Sokoban;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import Sokoban.controller.ToolManager;
import Sokoban.controller.ToolManager.Mode;
import Sokoban.controller.ToolManagerListener;
import Sokoban.model.Element;
import Sokoban.model.MazeInterface;
import Sokoban.model.Pos.Dir;
/**
 * A special toolbar solely for Sokoban controls.
 */
public class SokobanFrameToolbar extends JToolBar implements
		ToolManagerListener {

	public ToolManager toolManager;
	private final MazeInterface maze;
	private JButton createWall;
	private JButton createCrate;
	private JButton createArrow;
	private JButton createTarget;
	private JButton createPlayer;
	private JButton playButton;
	private JButton editButton;
	private JButton deleteButton;
	private JTextField charBox;
	private JComboBox colorCombo;
	private JComboBox dirCombo;

	public SokobanFrameToolbar(MazeInterface maze, ToolManager toolManager) {
		this.toolManager = toolManager;
		this.maze = maze;
		build();
		update();
	}

	public void build() {

		editButton = new JButton("Edit");
		new SelectToolListener(toolManager, editButton, ToolManager.Mode.EDIT);

		createWall = new JButton("Create Wall");
		new SelectToolListener(toolManager, createWall,
				ToolManager.Mode.CREATEWALL);

		createCrate = new JButton("Create Crate");
		new SelectToolListener(toolManager, createCrate,
				ToolManager.Mode.CREATEBOX);

		createArrow = new JButton("Create Arrow");
		new SelectToolListener(toolManager, createArrow,
				ToolManager.Mode.CREATEARROW);

		createTarget = new JButton("Create Target");
		new SelectToolListener(toolManager, createTarget,
				ToolManager.Mode.CREATETARGET);

		createPlayer = new JButton("Create Player");
		new SelectToolListener(toolManager, createPlayer,
				ToolManager.Mode.CREATEPLAYER);

		playButton = new JButton("Play");
		new SelectToolListener(toolManager, playButton, ToolManager.Mode.PLAY);

		deleteButton = new JButton("Delete!");
		deleteButton.setEnabled(false);
		new DeleteListener(toolManager, deleteButton);

		charBox = new JTextField(1);
		charBox.setVisible(false);
		new WallListener(toolManager, charBox);

		String[] colors = {"BLACK", "RED", "BLUE", "YELLOW", "PINK", "CYAN", "GRAY"};
		colorCombo = new JComboBox(colors);
		colorCombo.setVisible(false);
		new BoxListener(toolManager, colorCombo);

		Dir[] directions = {Dir.NORTH, Dir.EAST, Dir.SOUTH, Dir.WEST};
		dirCombo = new JComboBox(directions);
		dirCombo.setVisible(false);
		new ArrowListener(toolManager, dirCombo);
	}

	/**
	 * Removes all listeners on the components, to prevent memory leak.
	 */
	public void destroy(){
		for (Component i : this.getComponents()){
			for(ButtonListener l : i.getListeners(ButtonListener.class)){
				l.destroy();
			}
		}
	}

	public void update() {
		this.removeAll();
		this.add(editButton);
		this.add(createWall);
		this.add(createCrate);
		this.add(createArrow);
		this.add(createTarget);
		this.add(createPlayer);
		this.add(playButton);
		this.addSeparator();
		this.add(deleteButton);
		this.add(charBox);
		this.add(colorCombo);
		this.add(dirCombo);

	}

	@Override
	public void currentToolChanged(Mode currentTool) {
		build();
	}

	public void currentHighlightedObjectChanged(Element currentElement) {
		build();
	}

	@Override
	public void playModeFailed() {
	}
}