package deplacement;

import element.Coordonnee;
import element.Direction;

public interface Deplaceur {

	public Direction calculerDirection(Coordonnee position, Direction precedente);
	
}
