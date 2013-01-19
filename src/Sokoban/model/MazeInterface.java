package Sokoban.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Sokoban.model.Pos.Dir;

/**
 * The maze interface. Provides interfaces for adding and removing
 * elements in the maze, getting the elements (for drawing and moving),
 *  and
 */

public class MazeInterface{

	private Maze maze;
	public MazeManager mazeManager;

	public final int xsize;
	public final int ysize;
	private final Maze initMaze;
	private Maze playMaze;

	public MazeInterface (int x, int y) {
		xsize = x;
		ysize = y;
		maze = new Maze(x,y);
		initMaze = maze;
		mazeManager = new MazeManager(this);
	}

	/**
	 * @return the player
	 */
	public Element getPlayer() {
		Iterator<Element> i = iterator();
		while (i.hasNext()){
			Element next = i.next();
			if (next instanceof Player) {
				return next;
			}
		}
		return null;
	}

	/**
	 * Adds an element to the maze. It takes the position from the element.
	 * If the position is not valid for the element, it will fail.
	 *
	 * @param e The element you want to add.t
	 * @return Returns if the element was added.
	 */
	public boolean add(Element e){
		Pos pos = e.getPos();
		if (e instanceof Player) {
			if (getPlayer() != null) return false;
		}
		if (canEnterCheck(e, pos, Dir.STILL)){
			maze.getTile(pos).add(e);
			notify(e, pos, Dir.STILL);
			mazeManager.mazeChanged();
			return true;
		}
		return false;
	}

	/**
	 * Remove an element from the maze.
	 * Will fail if the element is not where it is expected to be.
	 *
	 * @param e The element you want to remove.
	 * @return Returns if the element was removed.
	 */
	public boolean remove(Element e){

		Pos pos = e.getPos();
		boolean success = maze.getTile(pos).remove(e);
		if (success) {
			mazeManager.mazeChanged();
		}
		return (success);
	}

	/**
	 * Tests if the maze contains an element of type elementClass at pos.
	 * @param elementClass
	 * @param pos
	 * @return
	 */

	public boolean containsAt(Class<? extends Element> elementClass, Pos pos){
		Iterator<Element> i = maze.getTile(pos).iterator();
		while (i.hasNext()){
			return (i.next().getClass() == elementClass);
		}
		return (false);
	}

	/**
	 * Moves the element e in the maze. Element's position must be maintained separately.
	 * As such, this should really only be called by the Element's move method.
	 * @param e
	 * @param newPos
	 * @param forceLeave
	 * @param dir
	 * @return
	 */
	public boolean move(Element e, Pos newPos, boolean forceLeave, Dir dir){
		Pos pos = e.getPos();
		if (pos == newPos) return true;
		if ((canLeaveCheck(e, pos, dir) || forceLeave) && canEnterCheck(e, newPos, dir)){
			maze.getTile(pos).remove(e);
			maze.getTile(newPos).add(e);
			notify(e, newPos, dir);
			mazeManager.mazeChanged();
			return true;
		}
		return false;
	}

	/**
	 * Tests if element e can enter position pos, if moving in direction dir.
	 * @param e
	 * @param pos
	 * @param dir
	 * @return
	 */
	public boolean canEnterCheck(Element e, Pos pos, Dir dir) {
		boolean canEnter = false;

		if (!posOutsideMaze(pos)) {
			canEnter = true;
			List<Element> ll = (List<Element>) maze.getTile(pos).clone();
			ll.remove(e);
			for (Element v : ll) {
				canEnter &= e.acceptEnter(v, dir);
			}
		}
		return canEnter;

	}

	/**
	 * Tests if element e can leave position pos, if moving in direction dir.
	 * @param e
	 * @param pos
	 * @param dir
	 * @return
	 */

	public boolean canLeaveCheck(Element e, Pos pos, Dir dir) {
		boolean canLeave = true;
		List<Element> ll = (List<Element>) maze.getTile(pos).clone();
		for (Element v : ll) {
			canLeave &= e.acceptLeave(v, dir);
		}
		return canLeave;
	}

	/**
	 * Notifies all elements on tile pos at time of call that element e has entered from direction dir.
	 *
	 * @param e Elements at 'pos' will be notified of this element.
	 * @param pos
	 * @param dir
	 */

	private void notify(Element e, Pos pos, Dir dir) {
		if (!posOutsideMaze(pos)) {
			/*
			 * We want to notify all of the elements on the square that were there during the move.
			 * It's possible that notifying an object may cause changes to the board, and we can't assume
			 * all of the elements in this list will stay in this list before we reach the end.
			 */
			List<Element> ll = (List<Element>) maze.getTile(pos).clone();
			for (Element v : ll) {
				e.acceptNotify(v, dir);
			}
		}
	}


	/**
	 * Returns whether the position is considered inside the maze.
	 * @param pos
	 * @return
	 */
	private boolean posOutsideMaze(Pos pos) {
		return maze.posOutsideMaze(pos);
	}

	/**
 	 * Returns the object considered to be on the top at position 'pos'.
 	 *
	 * @param pos The position that you want to find the top element.
	 * @return The element that is on the top at pos.
	 */
	public Element getTopObj(Pos pos) {
		if (posOutsideMaze(pos)) return null;
		List<Element> ll = maze.getTile(pos);
		if (ll.size() != 0) return ll.get(ll.size()-1);
		return null;
	}

	/**
	 * Check's if all elements in the maze have had their win condition met.
	 *
	 * @return
	 */
	public boolean isWon(){
		boolean won = true;
		MazeIterator i = new MazeIterator(maze);
		while (i.hasNext()){
			won &= i.next().winCondition();
		}
		return won;
	}

	public Iterator<Element> iterator(){
		return this.maze.iterator();
	}

	public void play(){
		playMaze = new Maze(xsize, ysize);
		Iterator<Element> i = initMaze.iterator();
		LinkedList<Element> ll = new LinkedList<Element>();
		while(i.hasNext()) {
			System.out.println(ll.add(i.next()));
		}
		System.out.println(ll.toString());
		for (Element el : ll) {
			playMaze.getTile(el.getPos()).add(el.clone(this));
		}
		maze = playMaze;
		mazeManager.mazeChanged();
	}

	public void edit(){
		maze = initMaze;
		playMaze = null;
	}
}
