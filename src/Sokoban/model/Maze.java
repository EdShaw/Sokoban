package Sokoban.model;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Maze is essentially a map from Pos to List<Elements>.
 * Except is has a max size.
 */

public class Maze {
	public LinkedList<Element>[][] array;
	public final int xsize;
	public final int ysize;

	@SuppressWarnings("unchecked")
	public Maze(int x, int y) {
		xsize = x;
		ysize = y;
		array = new LinkedList[x][y];
		for (int i = 0; i < x; i++){
			for (int j = 0; j < y; j++){
				array[i][j]  = new LinkedList<Element>();
			}
		}
	}

	protected LinkedList<Element> getTile(Pos pos) {
		return array[pos.getX()][pos.getY()];
	}

	/**
	 * Returns an iterator that iterates over all the elements in the maze.
	 *  An element returned is guaranteed to be on top of all Elements returned before it.
	 * @param mazeInterface TODO
	 */
	public Iterator<Element> iterator(){
		return new MazeIterator(this);
	}

	public boolean posOutsideMaze(Pos pos) {
		int x = pos.getX();
		int y = pos.getY();
		return (x >= xsize || x < 0 || y >= ysize || y < 0);
	}
}