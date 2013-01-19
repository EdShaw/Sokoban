package Sokoban;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import Sokoban.controller.ToolManager;
import Sokoban.controller.ToolManagerListener;
import Sokoban.controller.ToolManager.Mode;
import Sokoban.model.Arrow;
import Sokoban.model.Element;
import Sokoban.model.Pos.Dir;

class ArrowListener implements ActionListener, ToolManagerListener {

	protected final ToolManager toolManager;
	public final JComboBox button;

	public ArrowListener(ToolManager toolManager, JComboBox button) {
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
		if (currentElement instanceof Arrow){
			button.setSelectedItem(((Arrow) currentElement).getDir());
		}
		button.setVisible(visible());
		button.getParent().validate();
	}

	private boolean visible() {
		boolean isEditMode = toolManager.getTool()==Mode.EDIT;
		boolean isArrow = toolManager.getHighlightedElement() instanceof Arrow;
		return (isEditMode && isArrow);
	}

	public void actionPerformed(ActionEvent e) {
		if (button.isVisible() && toolManager.getHighlightedElement() instanceof Arrow){
			((Arrow) toolManager.getHighlightedElement()).setDir((Dir) button.getSelectedItem());
		}
	}

	@Override
	public void playModeFailed() {
	}
}