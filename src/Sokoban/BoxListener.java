package Sokoban;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import Sokoban.controller.ToolManager;
import Sokoban.controller.ToolManager.Mode;
import Sokoban.controller.ToolManagerListener;
import Sokoban.model.Box;
import Sokoban.model.Element;

class BoxListener implements ActionListener, ToolManagerListener {

	protected final ToolManager toolManager;
	public final JComboBox button;
	Color[] colors = {Color.BLACK, Color.RED, Color.BLUE, Color.YELLOW, Color.PINK, Color.CYAN, Color.GRAY};

	public BoxListener(ToolManager toolManager, JComboBox button) {
		this.toolManager = toolManager;
		this.button = button;
		toolManager.addListener(this);
		button.addActionListener(this);
	}

	public void destroy() {
		toolManager.removeListener(this);
		button.removeActionListener(this);
	}

	@Override
	public void currentToolChanged(Mode currentTool) {
		button.setVisible(visible());
		button.getParent().validate();
	}

	@Override
	public void currentHighlightedObjectChanged(Element currentElement) {
		button.setVisible(visible());
		button.getParent().validate();
	}

	private boolean visible() {
		boolean isEditMode = toolManager.getTool()==Mode.EDIT;
		boolean isBox = toolManager.getHighlightedElement() instanceof Box;
		return (isEditMode && isBox);
	}

	public void actionPerformed(ActionEvent e) {
		if (button.isVisible() && toolManager.getHighlightedElement() instanceof Box){
			Color color = colors[button.getSelectedIndex()];
			((Box) toolManager.getHighlightedElement()).setColor(color);
		}
	}

	@Override
	public void playModeFailed() {
	}
}