package deplacement;

import element.Coordonnee;
import element.Direction;

public class Aleatoire extends PeutEtreAleatoire {

	@Override
	public Direction calculerDirection(Coordonnee position, Direction precedente) {
		return directionAleatoire(position);
	}
	
	
	
}
