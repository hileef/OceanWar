package modele;

import java.util.LinkedList;

public abstract class Bateau {
	
	private static final int resistanceMax = 5;
	
	private int vies;
	private Case position;
	private DIRECTION direction;
	
	public abstract void agir() ;
	
	public void toucher() { 
		vies--;
		if(vies == 0)
			position.enleverBateau(this); // detruire bateau
	}
	public void reparer() { 
		vies = resistanceMax;
	}
	
	public void position(Case destination) {

	}
	protected Case position() {
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

	
	
}
