package bateau;

import ocean.Coordonnee;
import ocean.Direction;

public interface StrategieDeplacement {

	public Direction calculerDirection(Coordonnee position, Direction precedente);
	
}
