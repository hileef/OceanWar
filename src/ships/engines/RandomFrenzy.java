package ships.engines;

import ships.Inspector;
import core.Point;

public class RandomFrenzy extends ARandom {
	
	public RandomFrenzy(Inspector i) {
		super(i);
	}

	@Override
	public Point.Dir calculateDirectionFrom(Point position, Point.Dir dir) {
		return directionAleatoire(position);
	}
	
	
	
}
