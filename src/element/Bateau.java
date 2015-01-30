package element;

import modele.DIRECTION;
import modele.Ocean;
/**
 * Contient les attributs et les mï¿½thodes permettant de gï¿½rer l'objet BATEAU.
 */
public abstract class Bateau extends Element {

	private static final int resistanceMax = 5;
	
	private int vies;
	private int rayonRadar = 3;
	private Ocean o;
	
	public Bateau() {
		vies = resistanceMax;
	}
	
	public void ocean(Ocean o) {
		if(o != null)
			this.o = o;
	}
	
	protected Ocean ocean() {
		if(o == null)
			throw new IllegalStateException("Ocean n'a pas ete donne.");
		return o;
	}
	
	public int rayonRadar() {
		return rayonRadar;
	}
	
	public void position(DIRECTION dir) {
		if(dir != null)
			this.position(this.position().coordoneeDansDirection(dir));
	}
	
	public int vies() {
		if(vies < 0)
			System.out.println("VIES NEGATIVES DETECTEES.");
		return vies;
	}
	public void toucher() { 
		--vies;
	}
	public void reparer() { 
		vies = resistanceMax;
	}
	
	public boolean estDetruit() {
		return (vies == 0);
	}
	
	public abstract String toString();
	
	public abstract void jouerPas() ;
	
	@Override
	public boolean estAffichable() {
		return !estDetruit();
	}
	
	protected String imageURLComposante() {
		if(direction() == null)
			return ".png";
		switch(direction()) {
		case NE:
		case E:
		case SE:
		case S:
			return "d.png";
		default:
			return ".png";
		}
	}
		
}
