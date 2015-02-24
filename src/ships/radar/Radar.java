package ships.radar;

import java.util.LinkedList;
import java.util.List;

import core.Element;
import core.World;
import ships.Extension;
import ships.Ship;

public class Radar implements Extension {

	private Integer worldKey;
	private int range;
	
	public Radar(Integer worldKey, int range) {
		this.worldKey = worldKey;
		this.range = range;
	}
	
	@Override
	public void update(World w, Ship s) {
		List<Element> dest = new LinkedList<Element>();
		Integer[] keys = w.getElementKeys(worldKey);
		Element min = w.getElementWithKey(worldKey, keys[0]);
		int d, dmin = s.position().distanceTo(min.position());
		for(Integer i : keys) {
			 Element e = w.getElementWithKey(worldKey, i);
			 d = s.position().distanceTo(e.position());
			 if(d <= range) {
				 if(d < dmin && d > 0)
					 min = e;
				 dest.add(e);
			 }
		}
		dest.remove(min);
		dest.add(0, min);
		dest.remove(s);
		s.setNeighbors(this, dest);
	}

}
