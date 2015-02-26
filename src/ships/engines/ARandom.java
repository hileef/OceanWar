package ships.engines;

import java.util.LinkedList;

import core.Point;
import ships.Engine;
import ships.Inspector;

public abstract class ARandom extends Engine {

	public ARandom(Inspector i) {
		super(i);
	}
	protected Point.Dir directionAleatoire(Point position) {
		LinkedList<Point.Dir> options = new LinkedList<Point.Dir>();
		if(!position.isAtBorder())
			return directionAleatoireSansPenserAuBords();
		switch(position.borderHeading()) {
		case NW :
			options.add(Point.Dir.SE);
			options.add(Point.Dir.S);
			options.add(Point.Dir.E);
			break;
		case NE :
			options.add(Point.Dir.SW);
			options.add(Point.Dir.S);
			options.add(Point.Dir.W);
			break;
		case SW :
			options.add(Point.Dir.NE);
			options.add(Point.Dir.N);
			options.add(Point.Dir.E);
			break;
		case SE :
			options.add(Point.Dir.NW);
			options.add(Point.Dir.N);
			options.add(Point.Dir.W);
			break;
		case N :
			options.add(Point.Dir.S);
			options.add(Point.Dir.SE);
			options.add(Point.Dir.SW);
			break;
		case S :
			options.add(Point.Dir.N);
			options.add(Point.Dir.NE);
			options.add(Point.Dir.NW);
			break;
		case W :
			options.add(Point.Dir.E);
			options.add(Point.Dir.NE);
			options.add(Point.Dir.SE);
			break;
		case E :
			options.add(Point.Dir.W);
			options.add(Point.Dir.NW);
			options.add(Point.Dir.SW);
			break;
		default:
			return directionAleatoireSansPenserAuBords();
		}
		return directionAleatoireDepuis(options);
	}
	protected Point.Dir directionAleatoireSansPenserAuBords() {
		LinkedList<Point.Dir> liste = new LinkedList<Point.Dir>();
		for(Point.Dir d : Point.Dir.values())
			liste.add(d);
		return directionAleatoireDepuis(liste);
	}
	protected Point.Dir directionAleatoireDepuis(LinkedList<Point.Dir> options) {
		return options.get((int) (Math.random() * (options.size())));
	}

}
