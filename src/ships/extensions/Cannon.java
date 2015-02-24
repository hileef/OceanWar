package ships.extensions;

import ships.Extension;
import ships.Ship;
import core.Element;
import core.World;

public class Cannon implements Extension {

	private Integer worldKey;
	private int range;

	public Cannon(Integer worldKey, int range) {
		this.worldKey = worldKey;
		this.range = range;
	}

	@Override
	public void update(World w, Ship s) {
		if (s.neighbors() > 0) {
			Element target = s.neighbor(0);
			int d = s.position().distanceTo(target.position());
			if (d > 0 && d <= range)
				for (int i = 0; i < s.neighbors(); ++i) {
					Element e = s.neighbor(i);
					if (e.position().equals(target.position())) {
						e.hit();
						if (e.isDestroyed())
							w.notifyDeath(worldKey, e);
					}
				}
		}

	}

}
