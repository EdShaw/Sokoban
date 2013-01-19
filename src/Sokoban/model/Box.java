package Sokoban.model;

import java.awt.Color;

import Sokoban.model.Pos.Dir;


public class Box extends Element implements ElementVisitor{

	private Color color;

	public Box(MazeInterface maze, Pos pos) {
		super(maze, pos);
		this.setColor(Color.RED);
	}

	public Box(MazeInterface maze, Pos pos, Color color) {
		super(maze, pos);
		this.setColor(color);
	}

	public Box clone(MazeInterface newMaze){
		return new Box(newMaze, pos, color);
	}

	@Override
	public boolean winCondition(){
		return maze.containsAt(Target.class, getPos());
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
		if (dir == Dir.STILL) return false;
		boolean canEnter = maze.canEnterCheck(this, this.getPos().move(dir), dir);
		boolean canLeave = maze.canLeaveCheck(this, this.getPos(), dir);
		boolean canBePushed = canEnter && canLeave;
		return canBePushed;
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
		return false;
	}

	public void visitNotify(Player visitor, Dir dir){
		this.move(dir);
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
		maze.mazeManager.mazeChanged();
	}



}
