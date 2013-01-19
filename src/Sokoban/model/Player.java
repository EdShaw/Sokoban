package Sokoban.model;

import Sokoban.model.Pos.Dir;

public class Player extends Element implements ElementVisitor{

	private Dir dir;

	public Player(MazeInterface maze, Pos pos) {
		super(maze,pos);
		this.setDir(Dir.STILL);
	}

	public Player(MazeInterface maze, Pos pos, Dir dir) {
		super(maze, pos);
		this.setDir(dir);
	}

	public Player clone(MazeInterface newMaze){
		return new Player(newMaze, pos, dir);
	}


	public boolean move(Dir dir){
		boolean success = super.move(dir);
		if (success) this.dir = dir;
		return success;
	}

	/**
	 * @return the dir
	 */
	public Dir getDir() {
		return dir;
	}

	/**
	 * @param dir the dir to set
	 */
	public void setDir(Dir dir) {
		this.dir = dir;
	}


	public boolean acceptEnter(ElementVisitor e, Dir dir) {
		return e.visitEnter(this, dir);
	}
	public boolean acceptLeave(ElementVisitor e, Dir dir) {
		return e.visitLeave(this, dir);
	}
	public void acceptNotify(ElementVisitor e, Dir dir) {
		e.visitNotify(this, dir);
	}

	@Override
	public boolean visitEnter(Player visitor, Dir dir) {
		return false;
	}

	@Override
	public boolean visitEnter(Box visitor, Dir dir) {
		return false;
	}

	@Override
	public boolean visitEnter(Target visitor, Dir dir) {
		return true;
	}

	@Override
	public boolean visitEnter(Arrow visitor, Dir dir) {
		return true;
	}
}
