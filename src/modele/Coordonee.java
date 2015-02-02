package modele;

public class Coordonee {

	private int x;
	private int y;
	
	public static Coordonee aleatoire(int maximum) {
		return new Coordonee((int) (Math.random() * maximum), (int) (Math.random() * maximum));
	}
	
	public Coordonee(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() { 
		return "(" + x + ";" + y + ")";
	}
	public boolean equals(Object o) {
		Coordonee a = (Coordonee) o;
		return (this.x() == a.x() && this.y() == a.y());
	}
	public int hashCode() {
		return x * Ocean.TAILLE_MATRICE + y;
	}
	
	public int x() {
		return x;
	}
	public int y() {
		return y;
	}
	
	public Direction directionVers(Coordonee b) {
		int dy = this.differenceY(b);
		int dx = this.differenceX(b);
		boolean d = this.x() == b.x() && this.y() == b.y();
		if(d) return null;
		if(dx == 0) return (b.y() > this.y()) ? Direction.S: Direction.N; 
		if(dy == 0) return (b.x() > this.x()) ? Direction.E: Direction.O;
		if(dy > 0) { 
			if(dx > 0) return Direction.SE;
			else return Direction.SO;
		} else {
			if(dx > 0) return Direction.NE;
			else return Direction.NO;
		}
	}
	
	public Coordonee coordoneeDansDirection(Direction dir) {
		if(dir == null)
			return this;
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
	
	public boolean estUneBordure(int min, int max) {
		return estUneBordure(min, max, min, max);
	}
	public boolean estUneBordure(int xmin, int xmax, int ymin, int ymax) {
		return (x == xmin || x == xmax || y == ymin || y == ymax);
	}
	public Direction interpreterDirectionBordure(int min, int max) {
		return interpreterDirectionBordure(min, max, min, max);
	}
	public Direction interpreterDirectionBordure(int xmin, int xmax, int ymin, int ymax) {
		if(x == xmin) {
			if(y == ymin) return Direction.NO;
			else if(y == ymax) return Direction.SO;
			else return Direction.O;
		} else if(x == xmax) {
			if(y == ymin) return Direction.NE;
			else if(y == ymax) return Direction.SE;
			else return Direction.E;
		} else if(y == ymin) return Direction.N;
		else if(y == ymax) return Direction.S;
		else return null;
	}
	
	public int distance(Coordonee b) {
		int i = this.distanceY(b);
		int j = this.distanceX(b);
		int k = Math.abs(i - j);
		return (i < j) ? i + k: j + k;
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
	
	public Coordonee facteur(int f) {
		return new Coordonee(this.x() * f, this.y() * f);
	}
	
	public static Coordonee differentielVers(Direction d) {
		return (new Coordonee(0, 0)).coordoneeDansDirection(d);
	}
	
}
