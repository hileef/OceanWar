package deplacement;

import ocean.Coordonnee;
import ocean.Direction;
import ocean.Ocean;

public class Pong extends PeutEtreAleatoire {

	@Override
	public Direction calculerDirection(Coordonnee position, Direction precedente) {
		if(precedente == null) {
			return directionAleatoire(position);
		} else if(position.estUneBordure(0, Ocean.TAILLE_MATRICE - 1))
			return directionAleatoire(position);
		else return precedente;
	}

	
	
}
