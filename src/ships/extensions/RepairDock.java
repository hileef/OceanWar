package ships.extensions;

import core.Element;
import core.World;
import ships.Extension;
import ships.Ship;

public class RepairDock implements Extension  {
	
	private Integer worldKey;
	
	public RepairDock(Integer worldKey) {
		this.worldKey = worldKey;
	}
	
	@Override
	public void update(World w, Ship s) {
		
		for(Integer i : w.getElementKeys(worldKey)) {
			Element e = w.getElementWithKey(worldKey, i);
			if(s.position().equals(e.position()) && (e != s)) {
				if(!e.isDestroyed())
					e.repair();
			}
		}
		


	}

}
