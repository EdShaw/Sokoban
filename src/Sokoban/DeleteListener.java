package Sokoban;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import Sokoban.controller.ToolManager;
import Sokoban.controller.ToolManager.Mode;
import Sokoban.model.Element;

public class DeleteListener extends ButtonListener {


	public DeleteListener(ToolManager toolManager, JButton button) {
		super(toolManager, button);
	}

	public void actionPerformed(ActionEvent arg0) {
		toolManager.getHighlightedElement().delete();
		toolManager.setHighlightedObject(null);
	}

	public void currentToolChanged(Mode currentTool) {
		switch (currentTool){
		case EDIT:
			button.setVisible(true);
			break;
		default:
			button.setVisible(false);
			break;
		}
	}



	public void currentHighlightedObjectChanged(Element currentElement) {
		if (currentElement != null){
			button.setEnabled(true);
		} else {
			button.setEnabled(false);
		}
	}

	@Override
	public void playModeFailed() {
	}
}
