package deplacement;

import ocean.Coordonnee;
import ocean.Direction;

public class Aleatoire extends PeutEtreAleatoire {

	@Override
	public Direction calculerDirection(Coordonnee position, Direction precedente) {
		return directionAleatoire(position);
	}
	
	
	
}
