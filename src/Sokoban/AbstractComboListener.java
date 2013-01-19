package Sokoban;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import Sokoban.controller.ToolManager;
import Sokoban.controller.ToolManagerListener;
import Sokoban.controller.ToolManager.Mode;
import Sokoban.model.Element;

abstract class AbstractComboListener implements ActionListener, ToolManagerListener {

	protected final ToolManager toolManager;
	public final JTextField button;
	private Object eClass;

	public AbstractComboListener(ToolManager toolManager, JTextField button, Class <? extends Element> eClass) {
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
	abstract public void currentHighlightedObjectChanged(Element currentElement);

	abstract protected boolean visible();

	abstract public void actionPerformed(ActionEvent e);
}