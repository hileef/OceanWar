package ships.extensions;

import ships.CuriousExtension;
import ships.Inspector;
import ships.IShip;
import core.Element;

public class Cannon extends CuriousExtension {

	private int range;

	public Cannon(Inspector i, int range) {
		super(i);
		this.range = range;
	}

	@Override
	public void update(IShip s) {
		if(radar() == null)
			throw new IllegalStateException("An Inspector must be given to this extension at construction.");
		else if(radar().nbOfElementsAtClosestPosition() > 0) {
			Element target = radar().elementsAtClosestPosition(0);
			int d = s.position().distanceTo(target.position());
			if(d > 0 && d <= range)
				for (int i = 0; i < radar().nbOfElementsAtClosestPosition(); ++i) {
					Element e = radar().elementsAtClosestPosition(i);
					if (e.position().equals(target.position())) {
						e.hit();
						if (e.isDestroyed())
							radar().notifyDeath(e);
					}
				}	
			
		}

	}

}
