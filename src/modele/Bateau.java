package modele;

public abstract class Bateau {
	
	private static final int resistanceMax = 5;
	
	private int vies;
	private Case position;
	private DIRECTION direction;
	
	public void toucher() { 
		vies--;
		if(vies == 0)
			position.enleverBateau(this); // detruire bateau
	}
	public void reparer() { 
		vies = resistanceMax;
	}
	
	public void positionner(Case destination) {
		position.enleverBateau(this);
		position = destination;
		destination.ajouterBateau(this);
		
	}
	
	
	public abstract DIRECTION determinerDirection() ;
	public abstract void agir() ;
	
	
}
