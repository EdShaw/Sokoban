package Sokoban.view;

import Sokoban.controller.ToolManager;
import Sokoban.controller.ToolManager.Mode;
import Sokoban.controller.ToolManagerListener;
import Sokoban.model.Element;
import Sokoban.model.MazeInterface;
import Sokoban.model.MazeListener;

public class MazeViewListener implements MazeListener, ToolManagerListener {

	private final MazeView view;
	private final ToolManager tools;
	private final MazeInterface initMaze;
	private MazeInterface playMaze;

	public MazeViewListener(ToolManager tools, MazeView view) {
		this.tools = tools;
		this.view = view;
		initMaze = view.getMaze();
	}

	public void currentToolChanged(Mode currentTool) {
		switch (currentTool) {
		case PLAY:
			view.getMaze().play();
			break;

		default:
			view.getMaze().edit();
			break;
		}
		mazeChanged();
		view.updateMouseHandler();
	}

	public void currentHighlightedObjectChanged(Element currentElement) {
		view.repaint();
	}

	@Override
	public void mazeChanged() {
		view.repaint();
	}

	@Override
	public void mazeWon() {
		if (tools.getTool() == Mode.PLAY) {
			view.message("You win!");
		}
	}

	@Override
	public void playModeFailed() {
		view.message("Create player first.");
	}



}
