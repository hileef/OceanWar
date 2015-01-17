package modele;

public class Coordonee {

	private int x;
	private int y;
	
	public static Coordonee aleatoire(int maximum) {
		return new Coordonee((int) Math.random() * maximum, (int) Math.random() * maximum);
	}
	
	public Coordonee(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int x() {
		return x;
	}
	public int y() {
		return y;
	}
	
	public DIRECTION directionVers(Coordonee b) {
		int dy = this.differenceY(b);
		int dx = this.differenceX(b);
		int d = (dy < dx) ? dy: dx;
		if(d == 0) return null;
		if(dy == 0) return (b.y() > this.y()) ? DIRECTION.S: DIRECTION.N; 
		if(dx == 0) return (b.x() > this.x()) ? DIRECTION.E: DIRECTION.O;
		if(dy > 0) { 
			if(dx > 0) return DIRECTION.SE;
			else return DIRECTION.SO;
		} else {
			if(dx > 0) return DIRECTION.NE;
			else return DIRECTION.NO;
		}
	}
	
	public Coordonee coordoneeDansDirection(DIRECTION dir) {
		switch(dir) {
		case NO : return new Coordonee(this.x() - 1, this.y() - 1);
		case NE : return new Coordonee(this.x() + 1, this.y() - 1);
		case SO : return new Coordonee(this.x() - 1, this.y() + 1);
		case SE : return new Coordonee(this.x() + 1, this.y() + 1);
		case N : return new Coordonee(this.x(), this.y() - 1);
		case S : return new Coordonee(this.x(), this.y() + 1);
		case E : return new Coordonee(this.x() + 1, this.y());
		case O : return new Coordonee(this.x() - 1, this.y());
		default : return null;
		}
	}
	
	public int distance(Coordonee b) {
		int i = this.distanceY(b);
		int j = this.distanceX(b);
		return (i < j) ? i: j;
	}
	public int distanceX(Coordonee b) {
		return Math.abs(differenceX(b));
	}
	public int distanceY(Coordonee b) {
		return Math.abs(this.differenceY(b));
	}
	public int differenceX(Coordonee b) {
		return (b.x() - this.x());
	}
	public int differenceY(Coordonee b) {
		return (b.y() - this.y());
	}
	
}
