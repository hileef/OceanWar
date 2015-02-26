package ships.engines;

import ships.Inspector;
import core.Point;
import core.Point.Dir;

public class Pong extends ARandom {

	public Pong(Inspector i) {
		super(i);
	}

	@Override
	public Dir calculateDirectionFrom(Point position, Dir dir) {
		if(dir == null) {
			return directionAleatoire(position);
		} else if(position.isAtBorder())
			return directionAleatoire(position);
		else return dir;
	}

	
	
}
