package Sokoban.controller;

import Sokoban.controller.ToolManager.Mode;
import Sokoban.model.Element;

public interface ToolManagerListener {

	void currentToolChanged(Mode currentTool);

	void currentHighlightedObjectChanged(Element currentElement);

	void playModeFailed();
}
