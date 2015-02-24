package ships;

import core.Point;
import core.World;
import core.Element;

public abstract class Engine implements Extension {
	
	@Override
	public void update(World w, Ship s) {
		Point.Direction heading;
		if(s.neighbors() > 0) { 
			Element e = s.neighbor(0);
			if(s.position().distanceTo(e.position()) > 1)
				heading = s.position().directionTo(e.position());
			else
				heading = calculateDirectionFrom(s.position(), s.direction());
		} else
			heading = calculateDirectionFrom(s.position(), s.direction());
		s.setDisplacement(this, s.position().nextPointTowards(heading), heading);
	}
	
	public abstract Point.Direction calculateDirectionFrom(Point position, Point.Direction direction) ;

}
