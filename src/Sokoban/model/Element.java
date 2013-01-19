package Sokoban.model;

import Sokoban.model.Pos.Dir;

/**
 * Element is an element in the maze. Elements are immutable.
 * @author edward
 *
 */
public abstract class Element implements ElementVisitor, Cloneable{

	protected Pos pos;
	public final MazeInterface maze;

	public Element(MazeInterface maze, Pos pos) {
		this.pos = pos.clone();
		this.maze = maze;
	}

	public void delete(){
		maze.remove(this);
	}

	/**
	 * Return's if this element's win conditions are met.
	 * All elements in the maze must return true in order for the maze to be considered "won".
	 * @return Win condition.
	 */
	public boolean winCondition(){
		return true;
	}

	/**
	 * Returns the pos of the Element.
	 * The returned pos is a new instance of the clone,
	 * to prevent leaking the reference to the internal pos.
	 * @return
	 */
	public Pos getPos() {return pos.clone();}

		/**
	 *  Move, if possible according to the maze, in the given direction and return whether successful.
	 * @param dir The direction, eg Pos.NORTH
	 * @return Whether the move was successful according to domain model.
	 */
	public boolean move(Dir dir){
		return moveTo(pos.move(dir), false, dir);
	}

	/**
	 *  Moves the object to the given point, and the movement has the given direction.
	 * @param pos Position in maze.
	 * @param force Leave Forces the move, even if the object's old position won't let it move.
	 * @param dir The direction the object moving, for purposes of collision checks.
	 * @return Whether it was successful according to domain model.
	 */
	public boolean moveTo(Pos newPos, boolean forceLeave, Dir dir) {
		boolean success = maze.move(this, newPos, forceLeave, dir);
		if (success) pos = newPos;
		return success;
	}


	/**
	 *  Moves the object to the given point. The object movement has no direction.
	 * @param pos Position in maze.
	 * @param force Leave Forces the move, even if the object's old position won't let it move.
	 * @return Whether it was successful according to domain model.
	 */
	public boolean moveTo(Pos newPos, boolean forceLeave) {
		return moveTo(newPos, forceLeave, Pos.Dir.STILL);
	}

	public abstract boolean acceptEnter(ElementVisitor e, Dir dir);
	public abstract boolean acceptLeave(ElementVisitor e, Dir dir);
	public abstract void acceptNotify(ElementVisitor e, Dir dir);


	public boolean visitLeave(Wall visitor, Dir dir){
		return true;
	}
	public boolean visitLeave(Player visitor, Dir dir){
		return true;
	}
	public boolean visitLeave(Box visitor, Dir dir){
		return true;
	}
	public boolean visitLeave(Target visitor, Dir dir){
		return true;
	}
	public boolean visitLeave(Arrow visitor, Dir dir){
		return true;
	}

	public boolean visitEnter(Wall visitor, Dir dir) {
		return false;
	}

	public void visitNotify(Wall visitor, Dir dir){}
	public void visitNotify(Player visitor, Dir dir){}
	public void visitNotify(Box visitor, Dir dir){}
	public void visitNotify(Target visitor, Dir dir){}
	public void visitNotify(Arrow visitor, Dir dir){}

	public abstract Element clone(MazeInterface newMaze);

}
