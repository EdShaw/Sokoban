package Sokoban;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import Sokoban.controller.ToolManager;
import Sokoban.controller.ToolManager.Mode;
import Sokoban.model.Element;

public class SelectToolListener extends ButtonListener {

	private final Mode tool;

	public SelectToolListener(ToolManager toolManager, JButton button, Mode tool) {
		super(toolManager, button);
		this.tool = tool;
		button.setSelected(toolManager.getTool() == tool);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		toolManager.setTool(tool);
	}

	public void currentToolChanged(Mode currentTool) {
		button.setSelected(toolManager.getTool() == tool);
	}

	public void currentHighlightedObjectChanged(Element currentElement) {
	}

	@Override
	public void playModeFailed() {
	}

}
