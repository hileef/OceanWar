package ships.engines;

import java.util.LinkedList;

import core.Point;
import ships.Engine;
import ships.Inspector;

public abstract class ARandom extends Engine {

	public ARandom(Inspector i) {
		super(i);
	}
	protected Point.Direction directionAleatoire(Point position) {
		LinkedList<Point.Direction> options = new LinkedList<Point.Direction>();
		if(!position.isAtBorder())
			return directionAleatoireSansPenserAuBords();
		switch(position.borderHeading()) {
		case NW :
			options.add(Point.Direction.SE);
			options.add(Point.Direction.S);
			options.add(Point.Direction.E);
			break;
		case NE :
			options.add(Point.Direction.SW);
			options.add(Point.Direction.S);
			options.add(Point.Direction.W);
			break;
		case SW :
			options.add(Point.Direction.NE);
			options.add(Point.Direction.N);
			options.add(Point.Direction.E);
			break;
		case SE :
			options.add(Point.Direction.NW);
			options.add(Point.Direction.N);
			options.add(Point.Direction.W);
			break;
		case N :
			options.add(Point.Direction.S);
			options.add(Point.Direction.SE);
			options.add(Point.Direction.SW);
			break;
		case S :
			options.add(Point.Direction.N);
			options.add(Point.Direction.NE);
			options.add(Point.Direction.NW);
			break;
		case W :
			options.add(Point.Direction.E);
			options.add(Point.Direction.NE);
			options.add(Point.Direction.SE);
			break;
		case E :
			options.add(Point.Direction.W);
			options.add(Point.Direction.NW);
			options.add(Point.Direction.SW);
			break;
		default:
			return directionAleatoireSansPenserAuBords();
		}
		return directionAleatoireDepuis(options);
	}
	protected Point.Direction directionAleatoireSansPenserAuBords() {
		LinkedList<Point.Direction> liste = new LinkedList<Point.Direction>();
		for(Point.Direction d : Point.Direction.values())
			liste.add(d);
		return directionAleatoireDepuis(liste);
	}
	protected Point.Direction directionAleatoireDepuis(LinkedList<Point.Direction> options) {
		return options.get((int) (Math.random() * (options.size())));
	}

}
