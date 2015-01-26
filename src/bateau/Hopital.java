package bateau;

import java.util.ArrayList;
import java.util.LinkedList;

import modele.DIRECTION;
import modele.Ocean;

public class Hopital extends BateauxDAction {

	private LinkedList<Bateau> bateauxAuMemeEndroit;
	
	@Override
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
	
	@Override
	public void agir() {
		if(!bateauxAuMemeEndroit.isEmpty())
			for(Bateau b : bateauxAuMemeEndroit)
				b.reparer();
	}

	@Override
	public String toString() {
		return "H";
	}

	@Override
	public void infosRadar(ArrayList<LinkedList<Bateau>> radar) {
		bateauxAuMemeEndroit = radar.get(0);
	}


}
