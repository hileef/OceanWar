package core;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class World {
	
	private static final double REFRESH_RATE = 5.0;
	public static final int SIZE = 20;
	
	private List<Integer> garbage;
	private Map<Integer, Element> elements;
	private Integer key;
	private Display display;
	
	private Integer[] keys;
	
	public World(Factory f, Display d) {
		key = this.hashCode() + (int)(Math.random() * Integer.MAX_VALUE);
		elements = new LinkedHashMap<Integer, Element>();
		garbage = new LinkedList<Integer>();
		display = d;
		f.setWorldKey(key);
		for(Element e : f.build()) {
			elements.put(e.id(), e);
		}
	}
	
	public void run(int turns) {
		for(int i = 0; i < turns; ++i) {
			prepareKeysForRadarRequestPerformance();
			for(Element e : elements.values()) {
				e.update(this);
				display.update(e);
			}
			
			for(Integer id : garbage)
				elements.remove(id);
			display.refresh(1.0 / REFRESH_RATE);
		}
	}
	
	private void prepareKeysForRadarRequestPerformance() {
		keys = new Integer[elements.keySet().size()];
		keys = elements.keySet().toArray(keys);
	}

	private void authorize(Integer authorizationKey) {
		if(!authorizationKey.equals(this.key))
			throw new IllegalAccessError("Unauthorized Access.");
	}
	
	public void notifyDeath(Integer authorizationKey, Element e) {
		authorize(authorizationKey);
		if(e.isDestroyed()) {
			garbage.add(e.id());
			display.update(e);
		}
			
	}
	
	public Integer[] getElementKeys(Integer authorizationKey) {
		return Arrays.copyOf(keys, keys.length);
	}
	
	public Element getElementWithKey(Integer authorizationKey, Integer key) {
		authorize(authorizationKey);
		return elements.get(key);
	}

}
