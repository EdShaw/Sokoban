package Sokoban;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import Sokoban.controller.ToolManager;
import Sokoban.controller.ToolManagerListener;
import Sokoban.controller.ToolManager.Mode;
import Sokoban.model.Element;
import Sokoban.model.Wall;

class WallListener implements ActionListener, ToolManagerListener {

	protected final ToolManager toolManager;
	public final JTextField button;

	public WallListener(ToolManager toolManager, JTextField button) {
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
		if (currentElement instanceof Wall){
			button.setText( Character.toString(((Wall) currentElement).getChar()));
		}
		button.setVisible(visible());
		button.getParent().validate();
	}

	private boolean visible() {
		boolean isEditMode = toolManager.getTool()==Mode.EDIT;
		boolean isWall = toolManager.getHighlightedElement() instanceof Wall;
		return (isEditMode && isWall);
	}

	public void actionPerformed(ActionEvent e) {
		if (button.isVisible() && toolManager.getHighlightedElement() instanceof Wall){
			char ch = ' ';
			if (button.getText().length()>0) {ch = button.getText().charAt(0);}
			((Wall) toolManager.getHighlightedElement()).setChar(ch);
			button.setText(Character.toString(ch));
		}
	}

	@Override
	public void playModeFailed() {
	}
}