package Sokoban.model;

import java.awt.Color;

import Sokoban.model.Pos.Dir;


public class Target extends Element implements ElementVisitor{

	public Color color;

	public Target(MazeInterface maze, Pos pos) {
		super(maze, pos);
	}

	public Target clone(MazeInterface newMaze){
		return new Target(newMaze, pos);
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
		return true;
	}

	@Override
	public boolean visitEnter(Box visitor, Dir dir) {
		return true;
	}

	@Override
	public boolean visitEnter(Target visitor, Dir dir) {
		return false;
	}

	@Override
	public boolean visitEnter(Arrow visitor, Dir dir) {
		return false;
	}


}
