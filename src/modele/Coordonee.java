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
	public DIRECTION interpreterDirectionBordure(int min, int max) {
		return interpreterDirectionBordure(min, max, min, max);
	}
	public DIRECTION interpreterDirectionBordure(int xmin, int xmax, int ymin, int ymax) {
		if(x == xmin) {
			if(y == ymin) return DIRECTION.NO;
			else if(y == ymax) return DIRECTION.SO;
			else return DIRECTION.O;
		} else if(x == xmax) {
			if(y == ymin) return DIRECTION.NE;
			else if(y == ymax) return DIRECTION.SE;
			else return DIRECTION.E;
		} else if(y == ymin) return DIRECTION.N;
		else if(y == ymax) return DIRECTION.S;
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
	
}
