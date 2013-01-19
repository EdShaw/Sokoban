package Sokoban.model;
/**
 * An interface for listening to maze events.
 * Add your listener to the maze's mazeManager to be informed of that maze's events.
 *
 */

public interface MazeListener {

	/**
	 * Is called when the maze is changed.
	 */
	public void mazeChanged();

	/**
	 * Is called when the maze enters the win state.
	 * Will not get called again until the maze has left the win state.
	 */
	public void mazeWon();

}

