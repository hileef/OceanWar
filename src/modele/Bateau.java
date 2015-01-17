package modele;

import java.util.LinkedList;

public abstract class Bateau {
	
	private static final int resistanceMax = 5;
	
	private int vies;
	private Case position;
	private DIRECTION direction;
	
	public abstract void agir() ;
	
	public abstract void infoEntourage(Case bateauLePlusProche, DIRECTION dir, int distance) ;
	
	public void position(Case destination) {
		this.position = destination;
	}
	public Case position() {
		return position;
	}
	
	protected DIRECTION direction() {
		return direction;
	}
	protected void direction(DIRECTION d) {
		direction = d;
	}

	protected DIRECTION directionAleatoire() {
		LinkedList<DIRECTION> liste = new LinkedList<DIRECTION>();
		for(DIRECTION d : DIRECTION.values())
			liste.add(d);
		return directionAleatoireDepuis(liste);
	}
	protected DIRECTION directionAleatoireDepuis(LinkedList<DIRECTION> options) {
		return options.get((int) (Math.random() * (options.size() - 1)));
	}
	
	public abstract DIRECTION determinerDirection() ;
	
	public void toucher() { 
		vies--;
	}
	public void reparer() { 
		vies = resistanceMax;
	}
	public int vies() {
		return vies;
	}
	
}
