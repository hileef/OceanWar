package ships.engines;

import ships.Inspector;
import core.Point;

public class RandomFrenzy extends ARandom {
	
	public RandomFrenzy(Inspector i) {
		super(i);
	}

	@Override
	public Point.Direction calculateDirectionFrom(Point position, Point.Direction direction) {
		return directionAleatoire(position);
	}
	
	
	
}
