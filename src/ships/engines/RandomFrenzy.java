package ships.engines;

import core.Point;

public class RandomFrenzy extends ARandom {
	
	@Override
	public Point.Direction calculateDirectionFrom(Point position, Point.Direction direction) {
		return directionAleatoire(position);
	}
	
	
	
}
