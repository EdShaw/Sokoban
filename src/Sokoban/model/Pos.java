package Sokoban.model;

public class Pos {

		public enum Dir {
			NORTH, EAST, SOUTH, WEST, STILL;
		}

		private final int x;
		private final int y;
		public Pos(int x, int y){
			this.x=x;
			this.y=y;
		}

		public int getX(){ return x;}
		public int getY(){ return y;}

		public Pos clone(){
			return new Pos(x,y);
		}

		public Pos move (Dir dir) {
			int newX = x;
			int newY = y;
			switch (dir) {
			case NORTH:
				newY -= 1;
				break;
			case SOUTH:
				newY += 1;
				break;
			case EAST:
				newX += 1;
				break;
			case WEST:
				newX -= 1;
				break;
			default:
				break;
			}
			return new Pos(newX, newY);
		}
}