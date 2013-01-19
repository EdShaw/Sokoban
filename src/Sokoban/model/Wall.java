package Sokoban.model;

import Sokoban.model.Pos.Dir;


public class Wall extends Element implements ElementVisitor{

	protected char ch;

	public Wall(MazeInterface maze, Pos pos) {
		super(maze, pos);
		this.ch = ' ';
	}

	public Wall(MazeInterface maze, Pos pos, char ch) {
		super(maze, pos);
		this.ch = ch;
	}

	public Wall clone(MazeInterface newMaze){
		return new Wall(newMaze, pos, ch);
	}

	public void setChar(char ch){
		maze.mazeManager.mazeChanged();
		this.ch = ch;
	}

	public char getChar(){
		return ch;
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
		return false;
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
}
