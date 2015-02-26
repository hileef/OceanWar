package ships.engines;

import ships.Engine;
import ships.Inspector;
import core.Point;
import core.Point.Dir;

public class Boustrophedon extends Engine {
	
	public Boustrophedon(Inspector i) {
		super(i);
	}

	private Point.Dir previous = null;
	private Point.Dir next = null;
	private boolean up = true;

	@Override
	public Dir calculateDirectionFrom(Point position, Dir dir) {
		
		if(previous == null)
			previous = dir;
		
		Dir now = dir;
		
		if(next != null) {
			now = next;
			next = null;
		} else if(position.isAtBorder()) {
			if(position.borderHeading() == Dir.NE || position.borderHeading() == Dir.NW || 
					position.borderHeading() == Dir.SE || position.borderHeading() == Dir.SW) { 
					next = Point.inverseDirection(previous);
					now = ((up) ? Dir.S : Dir.N);
					up = !up;
			} else if(position.borderHeading() == Dir.E || position.borderHeading() == Dir.W) { 
				now = (up) ? Dir.N: Dir.S;
				next = Point.inverseDirection(previous);
			} 
		}

		previous = now;
		return now;
	}

	
}
