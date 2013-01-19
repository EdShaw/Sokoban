package Sokoban.model;

import java.util.Iterator;
import java.util.LinkedList;

final class MazeIterator implements Iterator<Element> {

	LinkedList<Element> list;
	private final Iterator<Element> iterator;

	public MazeIterator (Maze maze){
		list = new LinkedList<Element>();
		for (LinkedList<Element>[] llarray : maze.array){
			for (LinkedList<Element> ll : llarray) {
				for (Element e : ll){
					list.add(e);
				}
			}
		}
		iterator = list.iterator();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Element next() {
		// TODO Auto-generated method stub
		return iterator.next();
	}

	@Override
	public void remove() {
		iterator.remove();
	}



}