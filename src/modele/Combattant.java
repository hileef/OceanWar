package modele;

public class Combattant extends Bateau {

	private static final int porteeDeTir = 2;
	
	private Case cible;
	private DIRECTION directionCible;
	private int distanceCible;
	
	public DIRECTION determinerDirection() {
		if(cible == null) return directionAleatoire();
		if(distanceCible > porteeDeTir)
			return directionCible;
		return null;
	}
	
	public void agir() {
		cible.toucherBateaux();
	}

	public void infoEntourage(Case bateauLePlusProche, DIRECTION dir, int distance) { 
		cible = bateauLePlusProche;
		directionCible = dir;
		distanceCible = distance;
	}

}
