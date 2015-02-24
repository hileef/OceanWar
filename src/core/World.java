package core;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class World {
	
	private static final double REFRESH_RATE = 6.0;
	public static final int SIZE = 15;
	
	private List<Integer> garbage;
	private Map<Integer, Element> elements;
	private Integer key;
	private Display display;
	private Factory factory;
	
	public World(Factory f, Display d) {
		factory = f;
		display = d;
	}
	
	private void reset() {
		key = this.hashCode() + (int)(Math.random() * Integer.MAX_VALUE);
		elements = new LinkedHashMap<Integer, Element>();
		garbage = new LinkedList<Integer>();

		factory.setWorldKey(key);
		for(Element e : factory.build()) {
			elements.put(e.id(), e);
		}
	}
	
	public void run(int turns) {
		
		do {
			
			reset();
			for(int i = 0; i < turns; ++i) {
				for(Element e : elements.values()) {
					e.update(this);
					display.update(e);
				}
				
				for(Integer id : garbage)
					elements.remove(id);
				display.refresh(1.0 / REFRESH_RATE);
			}
			
		} while(display.askForReplay());
		
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
		Integer[] keys = new Integer[elements.keySet().size()];
		return elements.keySet().toArray(keys);
	}
	
	public Element getElementWithKey(Integer authorizationKey, Integer key) {
		authorize(authorizationKey);
		return elements.get(key);
	}

}
