package Sokoban.model;

import java.util.ArrayList;
import java.util.List;


public class MazeManager {

	private final MazeInterface maze;

	boolean mazeWon = false;

	public MazeManager(MazeInterface maze){
		this.maze = maze;
	}

	private final List<MazeListener> listeners = new ArrayList<MazeListener>();

	public void mazeChanged(){
		for (MazeListener l : listeners) {
			l.mazeChanged();
		}
		if (!mazeWon && maze.isWon()) {
			mazeWon();
			mazeWon = true;
		}
		else if (mazeWon && !maze.isWon()){
			mazeWon = false;
		}
	}

	public void mazeWon(){
		for (MazeListener l : listeners) {
			l.mazeWon();
		}
	}

	public void addListener(MazeListener l){
		listeners.add(l);
	}

	public void removeListener(MazeListener l){
		listeners.remove(l);
	}
}
