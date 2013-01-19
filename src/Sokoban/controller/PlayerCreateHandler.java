package Sokoban.controller;

import java.awt.event.MouseEvent;

import Sokoban.model.Player;
import Sokoban.view.MazeView;
/**
 * The mode for creating players. Deals with the special cases of not being allowed !=1 players.
 * @author edward
 *
 */
public class PlayerCreateHandler extends CreateHandler {

	public PlayerCreateHandler(MazeView view) {
		super(view, Player.class);
	}

	public void mouseClicked(MouseEvent e) {
		if (view.getMaze().getPlayer() == null){
			super.mouseClicked(e);
		} else {
			view.message("This town ain't big enough for the both of us.");
			view.repaint();
		}

	}




}
