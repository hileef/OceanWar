package display;

import java.util.HashMap;

import core.Element;
import core.Point;
import core.World;

public class Animated extends Graphical {
	
	private HashMap<Integer, Point> destinations;

	public Animated() {
		ProfGraphique.ouvrir("Ocean War", World.SIZE * IMAGE_SIZE, World.SIZE * IMAGE_SIZE);
		reinitaliser();
	}
	
	@Override
	public void update(Element e) {
		if(e.isDestroyed()) {
			ProfGraphique.retirerElement(e.id());
			destinations.remove(e.id());
		} else {
			int x, y;
			if(destinations.containsKey(Integer.parseInt("" + e.id()))) {
				x = ProfGraphique.getX(e.id());
				y = ProfGraphique.getY(e.id());
			} else {
				x = e.position().multiply(IMAGE_SIZE).x();
				y = e.position().multiply(IMAGE_SIZE).y();
			}
			ProfGraphique.retirerElement(e.id());
			ProfGraphique.ajouterImage(e.id(), "/images/" + e.imgURL(), x, y);
			destinations.put(Integer.parseInt("" + e.id()), e.position().multiply(IMAGE_SIZE));
		}
	}
	
	@Override
	public void refresh(double seconds) {
		int steps = (seconds == 0.0) ? 5: 10;
		int facteur = 50 / steps;
		for(int i = 0; i < steps; ++i) {
			for(Integer id : destinations.keySet()) {
				Point origine = new Point(ProfGraphique.getX(id), ProfGraphique.getY(id));
				Point differentiel = Point.differentialTowards(origine.directionTo(destinations.get(id))).multiply(facteur);
				ProfGraphique.deplacerElement(id, differentiel.x(), differentiel.y());
			}
			super.refresh(seconds / steps);
		}
	}

	@Override
	public void reinitaliser() {
		destinations = new HashMap<Integer, Point>();
		super.reinitaliser();
	}

}
