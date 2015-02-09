package deplacement;

import element.Coordonnee;
import element.Direction;
import ocean.Ocean;

public class Boustrophedon implements Deplaceur {
	
	private Direction suivante = null;
	private boolean retour = false;
	
	@Override
	public Direction calculerDirection(Coordonnee position, Direction precedente) {
		if(precedente == null) {
			if(position.estUneBordure(0, Ocean.TAILLE_MATRICE - 1)) {
				switch(position.interpreterDirectionBordure(0, Ocean.TAILLE_MATRICE - 1)) {
				case NE :
					suivante = Direction.O;
					return Direction.S;
				case SE :
				case E :
					return Direction.O;
				default :
					return Direction.E;
				}
			} else return Direction.E;
		} else if(suivante != null) {
			Direction tmp = suivante;
			suivante = null;
			return tmp;
		} else {
			if(position.estUneBordure(0, Ocean.TAILLE_MATRICE - 1)) {
				switch(position.interpreterDirectionBordure(0, Ocean.TAILLE_MATRICE - 1)) {
				case NO:
					if(retour){ 
						retour = false;
						return Direction.E;
					}
					suivante = Direction.E;
					return Direction.S;
				case NE:
					if(retour) {
						retour = false;
						return Direction.O;
					}
					suivante = Direction.O;
					return Direction.S;
				case SE:
					if(retour) {
						suivante = Direction.O;
						return Direction.N;
					}
					retour = true;
					return Direction.O;
				case SO:
					if(retour) {
						suivante = Direction.E;
						return Direction.N;
					}
					retour = true;
					return Direction.E;
				case E:
					suivante = Direction.O;
					return (retour) ? Direction.N : Direction.S;
				case O:
					suivante = Direction.E;
					return (retour) ? Direction.N : Direction.S;
				default:
					return precedente;	
				}
			} else {
				return precedente;
			}

		}
	}

	
}
