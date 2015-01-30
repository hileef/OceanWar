package element;

import modele.Direction;
import modele.Ocean;
/**
 * Contient les attributs et les mï¿½thodes permettant de gï¿½rer l'objet BATEAU.
 */
public abstract class Bateau extends Element {

	private static final int resistanceMax = 8;
	
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
