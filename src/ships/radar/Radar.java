package ships.radar;

import java.util.ArrayList;
import java.util.List;

import core.Element;
import core.InspectableWorld;
import ships.Inspector;
import ships.IShip;

public class Radar implements Inspector {

	private List<Element> samePosition;
	private List<Element> closestPosition;
	
	private InspectableWorld world;
	private int range;
	
	public Radar(InspectableWorld world, int range) {
		this.world = world;
		this.samePosition = new ArrayList<Element>();
		this.closestPosition = new ArrayList<Element>();
		this.range = range;
	}

	@Override
	public void update(IShip s) {
		samePosition.clear();
		closestPosition.clear();

		Integer[] keys = world.getElementKeys();
		Element min = world.getElementWithKey(keys[0]);
		int d, dmin = s.position().distanceTo(min.position());
		for(Integer i : keys) {
			
			Element e = world.getElementWithKey(i);
			d = s.position().distanceTo(e.position());
			
			if(e.position().equals(s.position()) && !e.equals(s))
				samePosition.add(e);
			else if(d > 0 && d <= range) {
				if(d < dmin) {
					closestPosition.clear();
					closestPosition.add(e);
				} else if(d == dmin) {
					if(closestPosition.size() == 0)
						closestPosition.add(e);
					if(e.position().equals(closestPosition.get(0).position()))
						closestPosition.add(e);
				}
			}
			
		}

	}

	@Override
	public void notifyDeath(Element e) {
		world.notifyDeath(e);
	}
	
	@Override
	public int nbOfElementsAtClosestPosition() {
		return closestPosition.size();
	}

	@Override
	public Element elementsAtClosestPosition(int i) {
		return closestPosition.get(i);
	}

	@Override
	public int nbOfElementsAtSamePosition() {
		return samePosition.size();
	}

	@Override
	public Element elementAtSamePosition(int i) {
		return samePosition.get(i);
	}

}
