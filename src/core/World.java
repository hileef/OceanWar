package core;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

class World implements InspectableWorld, RunnableWorld {
	
	private static final double REFRESH_RATE = 6.0;
	
	private List<Integer> garbage;
	private Map<Integer, Element> elements;
	private Display display;
	private Factory factory;
	
	public World(Factory f, Display d) {
		factory = f;
		display = d;
	}
	
	private void reset() {
		elements = new LinkedHashMap<Integer, Element>();
		garbage = new LinkedList<Integer>();

		factory.setWorldAccess(this);
		for(Element e : factory.build()) {
			elements.put(e.id(), e);
		}
	}
	
	@Override
	public void run(int turns) {
		
		do {
			reset();
			display.reset();
			
			for(int i = 0; i < turns; ++i) {
				for(Element e : elements.values()) {
					e.update();
					display.update(e);
				}
				
				for(Integer id : garbage)
					elements.remove(id);
				display.refresh(1.0 / REFRESH_RATE);
			}
			
		} while(display.askForReplay());
		
	}
	
	@Override
	public void notifyDeath(Element e) {
		if(e.isDestroyed()) {
			garbage.add(e.id());
			display.update(e);
		}
			
	}
	
	@Override
	public Integer[] getElementKeys() {
		Integer[] keys = new Integer[elements.keySet().size()];
		return elements.keySet().toArray(keys);
	}
	
	@Override
	public Element getElementWithKey(Integer key) {
		return elements.get(key);
	}

	@Test
	@Override
	public void runTests(Factory f, Display d) {
		// TODO Auto-generated method stub
		
		World w = new World(f, d) ;
		w.reset();
		assertFalse(w.display == null);
		assertFalse(w.factory == null);
		assertFalse(w.garbage == null);
		assertFalse(w.elements == null);
		w.elements.clear();
		assertEquals(w.elements.size(), 0);
		Element e = w.factory.buildTest();
		w.elements.put(e.id(), e);
		Integer[] list = w.getElementKeys();
		assertEquals(list.length, 1);
		assertEquals(w.getElementWithKey(list[0]), e);
		while(!e.isDestroyed())
			e.hit();
		w.notifyDeath(e);
		assertEquals(w.garbage.size(), 1);
		assertEquals(e, w.elements.get(w.garbage.get(0)));
		
	}

}
