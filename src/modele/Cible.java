package modele;

import java.util.LinkedList;

public class Cible extends Bateau {
	
	private DIRECTION suivante = null;
	private boolean retour = false;
	
	public DIRECTION determinerDirection() {
		direction(this.calculerDirection());
		return direction();
	}
	
	private DIRECTION calculerDirection() {
		if(direction() == null) {
			if(position().estUneBordure(0, Ocean.TAILLE_MATRICE - 1)) {
				switch(position().interpreterDirectionBordure(0, Ocean.TAILLE_MATRICE - 1)) {
				case NE :
					suivante = DIRECTION.O;
					return DIRECTION.S;
				case SE :
				case E :
					return DIRECTION.O;
				default :
					return DIRECTION.E;
				}
			} else return DIRECTION.E;
		} else if(suivante != null) {
			DIRECTION tmp = suivante;
			suivante = null;
			return tmp;
		} else {
			if(position().estUneBordure(0, Ocean.TAILLE_MATRICE - 1)) {
				switch(position().interpreterDirectionBordure(0, Ocean.TAILLE_MATRICE - 1)) {
				case NO:
					retour = false;
					return DIRECTION.E;
				case NE:
					suivante = DIRECTION.O;
					return DIRECTION.S;
				case SE:
					if(retour) {
						suivante = DIRECTION.O;
						return DIRECTION.N;
					}
					retour = true;
					return DIRECTION.O;
				case SO:
					if(retour) {
						suivante = DIRECTION.E;
						return DIRECTION.N;
					}
					retour = true;
					return DIRECTION.E;
				case E:
					suivante = DIRECTION.O;
					return (retour) ? DIRECTION.N : DIRECTION.S;
				case O:
					suivante = DIRECTION.E;
					return (retour) ? DIRECTION.N : DIRECTION.S;
				default:
					return direction();	
				}
			} else {
				return direction();
			}

		}
	}
	
	public void agir() { ; }

	public void infosRadar(LinkedList<Bateau> listeMemeEndroit, LinkedList<Bateau> listeTrieeEntourage) { ; }

	public String toString() {
		return "P";
	}
	


}
