package bateau;

import modele.Coordonee;
import modele.DIRECTION;
import modele.Ocean;
import affichage.Affichable;
import controle.Simulation;
/**
 * Contient les attributs et les mï¿½thodes permettant de gï¿½rer l'objet BATEAU.
 */
public abstract class Bateau implements Affichable {

	private static final int resistanceMax = 5;
	
	private int id;
	private int vies;
	private int rayonRadar = 3;
	private Coordonee position;
	private DIRECTION direction;
	private Ocean o;
	
	public Bateau() {
		this.id = Simulation.idUnique();
		vies = resistanceMax;
	}
	
	public void ocean(Ocean o) {
		if(o != null)
			this.o = o;
	}
	
	public Ocean ocean() {
		if(o == null)
			throw new IllegalStateException("Ocean n'a pas été donné.");
		return o;
	}
	
	public int id() {
		return id;
	}
	
	public int rayonRadar() {
		return rayonRadar;
	}
	
	public Coordonee position() {
		return position;
	}
	public void position(Coordonee destination) {
		this.position = destination;
	}
	public void position(DIRECTION dir) {
		if(dir != null)
			this.position(this.position().coordoneeDansDirection(dir));
	}
	
	
	protected DIRECTION direction() {
		return direction;
	}
	protected void direction(DIRECTION d) {
		direction = d;
	}
	
	public int vies() {
		return vies;
	}
	public void toucher() { 
		vies--;
	}
	public void reparer() { 
		vies = resistanceMax;
	}
	
	public abstract String toString();
	
	public abstract void jouerPas() ;
		
}
