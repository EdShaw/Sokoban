package Sokoban.model;

import Sokoban.model.Pos.Dir;

public interface ElementVisitor {

	/*
	 * Collision ultimately depends on the type and state of the collider, the collidee.
	 * Double dispatch is great for this.
	 * Methods to check whether an element can leave a square and enter a square are needed
	 * for elements like the arrow (can only leave arrows from the arrow head).
	*/

	boolean visitEnter(Wall visitor, Dir dir);
	boolean visitEnter(Player visitor, Dir dir);
	boolean visitEnter(Box visitor, Dir dir);
	boolean visitEnter(Target visitor, Dir dir);
	boolean visitEnter(Arrow visitor, Dir dir);

	boolean visitLeave(Wall visitor, Dir dir);
	boolean visitLeave(Player visitor, Dir dir);
	boolean visitLeave(Box visitor, Dir dir);
	boolean visitLeave(Target visitor, Dir dir);
	boolean visitLeave(Arrow visitor, Dir dir);

	void visitNotify(Wall visitor, Dir dir);
	void visitNotify(Player visitor, Dir dir);
	void visitNotify(Box visitor, Dir dir);
	void visitNotify(Target visitor, Dir dir);
	void visitNotify(Arrow visitor, Dir dir);
}
