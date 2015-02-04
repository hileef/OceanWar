package element;

import java.util.LinkedList;

import modele.Direction;
import modele.Ocean;
/**
 * Contient les attributs et les mï¿½thodes permettant de gï¿½rer l'objet BATEAU.
 */
public abstract class Bateau extends Element {

	private static final int resistanceMax = 8;
	
	private int vies;
	private int rayonRadar = 2;
	private Ocean o;
	
	public Bateau() {
		vies = resistanceMax;
	}
	public int resistance(){
		return resistanceMax;
	}
	public void ocean(Ocean o) {
		if(o != null)
			this.o = o;
	}
	
	public Ocean ocean() {
		if(o == null)
			throw new IllegalStateException("Ocean n'a pas ete donne.");
		return o;
	}
	
	public int rayonRadar() {
		return rayonRadar;
	}
	
	public void position(Direction dir) {
		if(dir != null)
			this.position(this.position().coordoneeDansDirection(dir));
	}
	
	public int vies() {
		return vies;
	}
	public void toucher() { 
		this.vies = (vies() > 0) ? vies() - 1 : 0;
	}
	public void reparer() { 
		vies = resistanceMax;
	}
	
	public boolean estDetruit() {
		return (vies == 0);
	}
	
	public abstract String toString();
	
	public abstract void jouerPas() ;	
	
	protected String imageURLComposante() {
		if(direction() == null)
			return ".png";
		else switch(direction()) {
		case NE:
		case E:
		case SE:
		case S:
			return "d.png";
		default:
			return ".png";
		}

	}
	
	protected Direction directionAleatoire() {
		LinkedList<Direction> options = new LinkedList<Direction>();
		if(!position().estUneBordure(0, Ocean.TAILLE_MATRICE - 1))
			return directionAleatoireSansPenserAuBords();
		switch(position().interpreterDirectionBordure(0, Ocean.TAILLE_MATRICE - 1)) {
		case NO :
			options.add(Direction.SE);
			options.add(Direction.S);
			options.add(Direction.E);
			break;
		case NE :
			options.add(Direction.SO);
			options.add(Direction.S);
			options.add(Direction.O);
			break;
		case SO :
			options.add(Direction.NE);
			options.add(Direction.N);
			options.add(Direction.E);
			break;
		case SE :
			options.add(Direction.NO);
			options.add(Direction.N);
			options.add(Direction.O);
			break;
		case N :
			options.add(Direction.S);
			options.add(Direction.SE);
			options.add(Direction.SO);
			break;
		case S :
			options.add(Direction.N);
			options.add(Direction.NE);
			options.add(Direction.NO);
			break;
		case O :
			options.add(Direction.E);
			options.add(Direction.NE);
			options.add(Direction.SE);
			break;
		case E :
			options.add(Direction.O);
			options.add(Direction.NO);
			options.add(Direction.SO);
			break;
		default:
			return directionAleatoireSansPenserAuBords();
		}
		return directionAleatoireDepuis(options);
	}
	protected Direction directionAleatoireSansPenserAuBords() {
		LinkedList<Direction> liste = new LinkedList<Direction>();
		for(Direction d : Direction.values())
			liste.add(d);
		return directionAleatoireDepuis(liste);
	}
	protected Direction directionAleatoireDepuis(LinkedList<Direction> options) {
		return options.get((int) (Math.random() * (options.size())));
	}
	

		
}
