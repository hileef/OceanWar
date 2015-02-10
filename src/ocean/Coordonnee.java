package ocean;


/**
 * Cette classe permet d'encapluser des coordonees x et y dans un objet Immuable avec pleins de fonctions pratiques.
 * ATTENTION : Le concept de coordonee graphique sera utilisee, c'est a dire que :
 *  => les abscisses augmentent (..5.6.7..) vers la droite (EST)
 *  => les abscisses baissent (..4.3.2..) vers la gauche (OUEST)
 *  => les ordonees augmentent (..5.6.7..) vers LE BAS (SUD)
 *  => les ordonees baissent (..4.3.2..) vers LE HAUT (NORD)
 *  L'origine (0, 0) est donc au NORD-OUEST (en haut a gauche).
 *
 */
public class Coordonnee {

	/**
	 * L'abscisse
	 */
	private int x;
	
	/**
	 * L'ordonee
	 */
	private int y;
	
	/**
	 * Permet d'obtenir une coordonee aleatoire sur un plan limite de maniere inclusive
	 *   par les coordonees de l'origine et donne en parametre.
	 * @param maximum la cordonee maximum dans laquelle la coordonee aleatoire sera choisie.
	 * @return une coordonee generee aleatoirement entre l'orgiine et le maximum.
	 */
	public static Coordonnee aleatoire(int maximum) {
		return new Coordonnee((int) (Math.random() * maximum), (int) (Math.random() * maximum));
	}
	
	/**
	 * Construit une coordonee avec ses proprietes.
	 * @param x L'abscisse
	 * @param y L'ordonee
	 */
	public Coordonnee(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * (Fonction standard)
	 * Renvoie une representation textuelle de la coordonee.
	 * @return Renvoie une chaine de caracteres representant la coordonee.
	 */
	public String toString() { 
		return "(" + x + ";" + y + ")";
	}
	
	/**
	 * (Fonction standard)
	 * Compare si la coordonee est egale a la coordonee donne en parametre.
	 * @param o Objet a copmarer
	 * @return boolean representant l'egalite avec le parametre.
	 */
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!this.getClass().isAssignableFrom(o.getClass())) return false;
		Coordonnee a = (Coordonnee) o;
		return (this.x() == a.x() && this.y() == a.y());
	}
	
	/**
	 * (Fonction standard)
	 * Permet de faire du hachis parmentier avec des Coordonees. :P
	 * Serieusement, permet aux algorithmes de HashMap de fonctionner correctement.
	 * @return un entier qui permet de distribuer uniformement les coordonees.
	 */
	public int hashCode() {
		return x * Ocean.TAILLE_MATRICE + y;
	}
	
	/**
	 * Renvoie l'abscisse
	 * @return L'abscisse
	 */
	public int x() {
		return x;
	}
	
	/**
	 * Renvoie l'ordonee
	 * @return l'ordonee
	 */
	public int y() {
		return y;
	}
	
	/**
	 * Renvoie la direction dans laquelle la coordonee specifie en parametre
	 *   se trouve par rapport a cette coordonee.
	 * @param b La coordonee a cibler afin de calculer la direction.
	 * @return La direction a prendre pour se diriger vers la coordonee donee en parametre.
	 */
	public Direction directionVers(Coordonnee b) {
		assert(b != null);
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
	
	/**
	 * Renvoie la coordonnee adjacente a cette coordonne dans la direction specifiee en parametre.
	 * @param dir La direction ciblee pour calculer la coordonee.
	 * @return La coordonnee adjacente a cette coordonne dans la direction specifiee en parametre.
	 */
	public Coordonnee coordonneeDansDirection(Direction dir) {
		if(dir == null)
			return this;
		switch(dir) {
		case NO : return new Coordonnee(this.x() - 1, this.y() - 1);
		case NE : return new Coordonnee(this.x() + 1, this.y() - 1);
		case SO : return new Coordonnee(this.x() - 1, this.y() + 1);
		case SE : return new Coordonnee(this.x() + 1, this.y() + 1);
		case N : return new Coordonnee(this.x(), this.y() - 1);
		case S : return new Coordonnee(this.x(), this.y() + 1);
		case E : return new Coordonnee(this.x() + 1, this.y());
		case O : return new Coordonnee(this.x() - 1, this.y());
		default : return null;
		}
	}
	
	/**
	 * Renvoie un boolean representant si la coordonee donnee represente un bord dans le plan
	 * limite inclusivement par le minimum et le maximum donne (pour une matrice carree)
	 * @param min Le minimum considere comme bord
	 * @param max Le maximum considere comme bord
	 * @return Boolean representant si la coordonee est un bord ou pas.
	 */
	public boolean estUneBordure(int min, int max) {
		return estUneBordure(min, max, min, max);
	}
	/**
	 * Renvoie un boolean representant si la coordonee donnee represente un bord dans le plan
	 * limite inclusivement par le minimum et le maximum donne (pour une matrice recratngluaire)
	 * @param xmin L'abscisse minimum considere comme bord
	 * @param xmax L'abscisse considere comme bord
	 * @param ymin L'ordonee minimum considere comme bord
	 * @param ymax L'ordonee considere comme bord
	 * @return Boolean representant si la coordonee est un bord ou pas.
	 */
	public boolean estUneBordure(int xmin, int xmax, int ymin, int ymax) {
		return (x == xmin || x == xmax || y == ymin || y == ymax);
	}
	
	/**
	 * Calcule la direction de la bordure (voir definition moins sucree)
	 * @param min Le minimum considere comme bord
	 * @param max Le maximum considere comme bord
	 * @return un enum Direction qui permet de determiner lq direction de la bordure.
	 */
	public Direction interpreterDirectionBordure(int min, int max) {
		return interpreterDirectionBordure(min, max, min, max);
	}
	/**
	 * Calcule la direction de la bordure, definie par rapport aux coordonees graphiques :
	 * => NORD (bort en haut)(y min)
	 * => SUD (bort en bas)(y max)
	 * => SUDOUEST (bort en bas a gauche)(y max et x min)
	 * => ...
	 * @param xmin L'abscisse minimum considere comme bord
	 * @param xmax L'abscisse considere comme bord
	 * @param ymin L'ordonee minimum considere comme bord
	 * @param ymax L'ordonee considere comme bord
	 * @return enum representant la direction de la bordure.
	 */
	public Direction interpreterDirectionBordure(int xmin, int xmax, int ymin, int ymax) {
		if(!estUneBordure(xmin, xmax, ymin, ymax)) return null;
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
	
	/**
	 * Renvoie la distance a parcourir j'usqu'a la coordonee
	 * specifiee en parametre par rapport a cette coordonee.
	 * @param b Coordonnee ciblee pour calculer la distance
	 * @return entier representant la distance a parcourir j'usqu'a la coordonee specifiee en parametre.
	 */
	public int distance(Coordonnee b) {
		assert(b != null);
		int i = this.distanceY(b);
		int j = this.distanceX(b);
		int k = Math.abs(i - j);
		return (i < j) ? i + k: j + k;
	}
	
	
	/**
	 * Renvoie la distance a parcourir sur l'axe des abscisses
	 * j'usqu'a la coordonee specifiee en parametre par rapport a cette coordonee.
	 * @param b Coordonnee ciblee pour calculer la distance
	 * @return entier representant la distance a parcourir sur l'axe des abscisses
	 * j'usqu'a la coordonee specifiee en parametre.
	 */
	public int distanceX(Coordonnee b) {
		assert(b != null);
		return Math.abs(differenceX(b));
	}
	/**
	 * Renvoie la distance a parcourir sur l'axe des ordonees
	 * j'usqu'a la coordonee specifiee en parametre par rapport a cette coordonee.
	 * @param b Coordonnee ciblee pour calculer la distance
	 * @return entier representant la distance a parcourir sur l'axe des ordonees
	 * j'usqu'a la coordonee specifiee en parametre.
	 */
	public int distanceY(Coordonnee b) {
		assert(b != null);
		return Math.abs(this.differenceY(b));
	}
	
	/**
	 * Renvoie la difference entre les valeur X de la coordonee speciffiee et cette coordonnee
	 * @param b La coordonee avec laquelle faire la difference
	 * @return la difference entre les valeur X de la coordonee speciffiee et cette coordonnee
	 */
	public int differenceX(Coordonnee b) {
		assert(b != null);
		return (b.x() - this.x());
	}
	
	/**
	 * Renvoie la difference entre les valeur Y de la coordonee speciffiee et cette coordonnee
	 * @param b La coordonee avec laquelle faire la difference
	 * @return la difference entre les valeur Y de la coordonee speciffiee et cette coordonnee
	 */
	public int differenceY(Coordonnee b) {
		assert(b != null);
		return (b.y() - this.y());
	}
	
	/**
	 * Renvoie l'equivalent de cette coordonee agrandie d'un facteur donne en parametre.
	 * @param f Facteur a utiliser pour l'agrandissement
	 * @return Une nouvelle coordonee a partic de cette coordonee
	 *   avec son abscisse et son ordonnees mulitpliees par f.
	 */
	public Coordonnee facteur(int f) {
		return new Coordonnee(this.x() * f, this.y() * f);
	}
	
	/**
	 * Renvoie une coordonnee representant le differentiel que represente un deplacement dans une certaine direction.
	 * @param d La direction dont on veut connaitre le differentiel en coordonees.
	 * @return Une coordonee representant le differentiel que cause unn deplacement dans la direction donee.
	 */
	public static Coordonnee differentielVers(Direction d) {
		return (new Coordonnee(0, 0)).coordonneeDansDirection(d);
	}
	
}
