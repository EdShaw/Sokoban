package Sokoban;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import Sokoban.controller.ToolManager;
import Sokoban.controller.ToolManagerListener;

abstract class ButtonListener implements ActionListener, ToolManagerListener {

	protected final ToolManager toolManager;
	public final JButton button;

	public ButtonListener(ToolManager toolManager, JButton button) {
		this.toolManager = toolManager;
		this.button = button;
		toolManager.addListener(this);
		button.addActionListener(this);
	}

	public void destroy() {
		toolManager.removeListener(this);
		button.removeActionListener(this);
	}
}
