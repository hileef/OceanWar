package ships;

import core.Point;

public abstract class Engine extends CuriousExtension {
	
	public Engine(Inspector i) {
		super(i);
	}

	@Override
	public void update(IShip s) {
		
		Point.Dir heading = null;
		
		if(radar() != null) {
			if(radar().nbOfElementsAtClosestPosition() > 0) {
				Point dest = radar().elementsAtClosestPosition(0).position();
				if(s.position().distanceTo(dest) > 1)
					heading = s.position().directionTo(dest);
				else
					heading = null;
			} else
				heading = calculateDirectionFrom(s.position(), s.dir());
		} else
			heading = calculateDirectionFrom(s.position(), s.dir());
		
		if(heading != null)
			s.setDisplacement(s.position().nextPointTowards(heading), heading);
		
	}
	
	public abstract Point.Dir calculateDirectionFrom(Point position, Point.Dir dir) ;

}
