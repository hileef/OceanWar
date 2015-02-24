package ships.engines;

import ships.Engine;
import core.Point;
import core.Point.Direction;

public class Boustrophedon extends Engine {
	
	private Point.Direction suivante = null;
	private boolean retour = false;

	@Override
	public Direction calculateDirectionFrom(Point position, Direction direction) {
		if(direction == null) {
			if(position.isAtBorder()) {
				switch(position.borderHeading()) {
				case NE :
					suivante = Direction.W;
					return Direction.S;
				case SE :
				case E :
					return Direction.W;
				default :
					return Direction.E;
				}
			} else return Direction.E;
		} else if(suivante != null) {
			Direction tmp = suivante;
			suivante = null;
			return tmp;
		} else {
			if(position.isAtBorder()) {
				switch(position.borderHeading()) {
				case NW:
					if(retour){ 
						retour = false;
						return Direction.E;
					}
					suivante = Direction.E;
					return Direction.S;
				case NE:
					if(retour) {
						retour = false;
						return Direction.W;
					}
					suivante = Direction.W;
					return Direction.S;
				case SE:
					if(retour) {
						suivante = Direction.W;
						return Direction.N;
					}
					retour = true;
					return Direction.W;
				case SW:
					if(retour) {
						suivante = Direction.E;
						return Direction.N;
					}
					retour = true;
					return Direction.E;
				case E:
					suivante = Direction.W;
					return (retour) ? Direction.N : Direction.S;
				case W:
					suivante = Direction.E;
					return (retour) ? Direction.N : Direction.S;
				default:
					return direction;	
				}
			} else {
				return direction;
			}

		}
	}

	
}
