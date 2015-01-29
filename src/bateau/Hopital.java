package bateau;

import java.util.LinkedList;

import modele.DIRECTION;
import modele.Ocean;

public class Hopital extends BateauxDAction {
	
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

	public void agir(LinkedList<Bateau> liste) {
		if(!liste.isEmpty())
			for(Bateau b : liste)
				b.reparer();
	}

	private LinkedList<Bateau> listeBateauxDepuisRadar() {
		return ocean().radar(this, 0, 0).get(0);
	}
	
	@Override
	public String toString() {
		return "H";
	}

	@Override
	public void jouerPas() {
		LinkedList<Bateau> liste = listeBateauxDepuisRadar();
		DIRECTION dir = determinerDirection();
		if(dir != null)
			this.position(this.position().coordoneeDansDirection(dir));
		agir(liste);
	}

	@Override
	public String imageURL() {
		return "Hopital.gif";
	}


}
