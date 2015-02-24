package ships.engines;

import core.Point;
import core.Point.Direction;

public class Pong extends ARandom {

	@Override
	public Direction calculateDirectionFrom(Point position, Direction direction) {
		if(direction == null) {
			return directionAleatoire(position);
		} else if(position.isAtBorder())
			return directionAleatoire(position);
		else return direction;
	}

	
	
}
