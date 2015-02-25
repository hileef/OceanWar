package ships.extensions;

import core.Element;
import ships.CuriousExtension;
import ships.Inspector;
import ships.IShip;

public class RepairDock extends CuriousExtension  {
	
	public RepairDock(Inspector i) {
		super(i);
	}
	
	@Override
	public void update(IShip s) {
		
		for(int i = 0; i < radar().nbOfElementsAtSamePosition(); ++i) {
			Element e = radar().elementAtSamePosition(i);
			if(!e.isDestroyed())
				e.repair();
		}

	}

}
