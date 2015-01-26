package modele;

import java.util.LinkedList;

public class Hopital extends BateauxDAction {

	private LinkedList<Bateau> bateauxAuMemeEndroit;
	
	public DIRECTION determinerDirection() {
		direction(calculerDirection());
		return direction();
	}
	
	private DIRECTION calculerDirection() {
		if(direction() == null) {
			return directionAleatoire();
		} else if(position().estUneBordure(0, Ocean.TAILLE_MATRICE - 1))
			return directionAleatoire();
		else return direction();
	}
	
	public void agir() {
		if(!bateauxAuMemeEndroit.isEmpty())
			for(Bateau b : bateauxAuMemeEndroit)
				b.reparer();
	}

	public void infosRadar(LinkedList<Bateau> listeMemeEndroit, LinkedList<Bateau> listeTrieeEntourage) { 
		this.bateauxAuMemeEndroit = listeMemeEndroit;
	}

	public String toString() {
		return "H";
	}


}
