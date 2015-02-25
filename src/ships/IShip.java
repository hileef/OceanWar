package ships;

import core.Element;
import core.Point;

public interface IShip extends Element {

	public void setDisplacement(Point position, Point.Direction direction);
	
}
