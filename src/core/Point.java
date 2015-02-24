package core;

public class Point {

	public static enum Direction { N, S, E, W, NE, NW, SE, SW };
	
	private int x;
	private int y;
	
	public static Point random() {
		int x = (int) Math.round(Math.random() * (World.SIZE - 1));
		int y = (int) Math.round(Math.random() * (World.SIZE - 1));
		return new Point(x, y);
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
	
	public String toString() { 
		return "(" + x + ";" + y + ")";
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!this.getClass().isAssignableFrom(o.getClass())) return false;
		Point a = (Point) o;
		return (this.x() == a.x() && this.y() == a.y());
	}
	
	public Point multiply(int f) {
		return new Point(this.x() * f, this.y() * f);
	}
	
	public static Point differentialTowards(Direction d) {
		return (new Point(0, 0)).nextPointTowards(d);
	}
	
	public Direction directionTo(Point b) {
		assert(b != null);
		int dy = this.differenceY(b);
		int dx = this.differenceX(b);
		boolean d = this.x() == b.x() && this.y() == b.y();
		if(d) return null;
		if(dx == 0) return (b.y() > this.y()) ? Direction.S: Direction.N; 
		if(dy == 0) return (b.x() > this.x()) ? Direction.E: Direction.W;
		if(dy > 0) { 
			if(dx > 0) return Direction.SE;
			else return Direction.SW;
		} else {
			if(dx > 0) return Direction.NE;
			else return Direction.NW;
		}
	}
	
	public Point nextPointTowards(Direction dir) {
		if(dir == null)
			return this;
		switch(dir) {
		case NW : return new Point(this.x() - 1, this.y() - 1);
		case NE : return new Point(this.x() + 1, this.y() - 1);
		case SW : return new Point(this.x() - 1, this.y() + 1);
		case SE : return new Point(this.x() + 1, this.y() + 1);
		case N : return new Point(this.x(), this.y() - 1);
		case S : return new Point(this.x(), this.y() + 1);
		case E : return new Point(this.x() + 1, this.y());
		case W : return new Point(this.x() - 1, this.y());
		default : return null;
		}
	}
	
	public boolean isAtBorder() {
		return (x() == 0 || y() == 0 || x() == World.SIZE - 1 || y() == World.SIZE - 1);
	}
	
	public Point.Direction borderHeading() {
		if(!isAtBorder()) return null;
		if(x == 0) {
			if(y == 0) return Direction.NW;
			else if(y == World.SIZE - 1) return Direction.SW;
			else return Direction.W;
		} else if(x == World.SIZE - 1) {
			if(y == 0) return Direction.NE;
			else if(y == World.SIZE - 1) return Direction.SE;
			else return Direction.E;
		} else if(y == 0) return Direction.N;
		else if(y == World.SIZE - 1) return Direction.S;
		else return null;
	}
	
	public int distanceTo(Point b) {
		assert(b != null);
		int i = this.distanceY(b);
		int j = this.distanceX(b);
		int k = Math.abs(i - j);
		return (i < j) ? i + k: j + k;
	}
	
	private int distanceX(Point b) {
		assert(b != null);
		return Math.abs(differenceX(b));
	}
	
	private int distanceY(Point b) {
		assert(b != null);
		return Math.abs(this.differenceY(b));
	}
	
	private int differenceX(Point b) {
		assert(b != null);
		return (b.x() - this.x());
	}
	
	private int differenceY(Point b) {
		assert(b != null);
		return (b.y() - this.y());
	}
	
}
