package Sokoban.controller;

import java.util.ArrayList;
import java.util.List;

import Sokoban.model.Element;
import Sokoban.model.MazeInterface;

public class ToolManager {

	/**
	 * An enum of all the valid modes.
	 *
	 * @author edward
	 *
	 */
    public enum Mode
    {
        EDIT,
        CREATEWALL,
        CREATEPLAYER,
        CREATEBOX,
        CREATETARGET,
        CREATEARROW,
        PLAY
    }

	private final List<ToolManagerListener> listeners = new ArrayList<ToolManagerListener>();
	private Mode currentTool = Mode.EDIT;
	private Element currentElement;
	private final MazeInterface maze;

	public ToolManager(MazeInterface maze) {
		this.maze = maze;
	}

	/**
	 * Set the mode. Will notify all of the registered listeners.
	 *
	 * @param tool
	 */
	public void setTool(Mode tool){
		if (tool != Mode.PLAY || maze.getPlayer() != null) {
			currentTool = tool;
		} else {
			currentTool = Mode.CREATEPLAYER;
			for (ToolManagerListener l : listeners) {
				l.playModeFailed();
			}
		}
		for (ToolManagerListener l : listeners) {
			l.currentToolChanged(currentTool);
		}
	}

	/** Get the current tool */
	public Mode getTool() {
		return currentTool;
	}

	/** Set the highlighted object for edit mode. Will notify listeners.*/
	public void setHighlightedObject(Element obj){
		currentElement = obj;
		for (ToolManagerListener l : listeners) {
			l.currentHighlightedObjectChanged(currentElement);
		}
	}

	/** Get the highlighted object for edit mode.
	 * Will be null if there is no item selected. */
	public Element getHighlightedElement(){
		return currentElement;
	}

	public void addListener(ToolManagerListener l){
		listeners.add(l);
	}

	public void removeListener(ToolManagerListener l){
		listeners.remove(l);
	}
}
