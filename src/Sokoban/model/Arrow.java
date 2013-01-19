package Sokoban.model;

import Sokoban.model.Pos.Dir;

public class Arrow extends Element implements ElementVisitor{

	private Pos.Dir dir;

	public Arrow(MazeInterface maze, Pos pos) {
		super(maze,pos);
		this.setDir(Pos.Dir.SOUTH);
	}

	public Arrow(MazeInterface maze, Pos pos, Dir dir) {
		super(maze, pos);
		// Arrows can not have no direction. Give a sane default.
		if (dir == Pos.Dir.STILL) dir = Pos.Dir.SOUTH;
		this.setDir(dir);
	}

	public Arrow clone(MazeInterface newMaze){
		return new Arrow(newMaze, pos, dir);
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
		if (dir == Dir.STILL) dir = Dir.SOUTH;
		this.dir = dir;
		maze.mazeManager.mazeChanged();
	}

	public boolean acceptEnter(ElementVisitor e, Dir dir) {
		return e.visitEnter(this, dir);
	}
	public boolean acceptLeave(ElementVisitor e, Dir dir) {
		return e.visitLeave(this, dir);
	}
	public void acceptNotify(ElementVisitor e, Dir dir) {
		e.visitLeave(this, dir);
	}

	@Override
	public boolean visitEnter(Player visitor, Dir dir) {
		return (dir == this.getDir() || dir == Dir.STILL);
	}

	@Override
	public boolean visitEnter(Box visitor, Dir dir) {
		return false;
	}

	@Override
	public boolean visitEnter(Target visitor, Dir dir) {
		return false;
	}

	@Override
	public boolean visitEnter(Arrow visitor, Dir dir) {
		return false;
	}

	public boolean visitLeave(Player visitor, Dir dir) {
		return dir == this.getDir();
	}

}
