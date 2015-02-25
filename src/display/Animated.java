package display;

import java.util.HashMap;

import core.Displayable;
import core.Point;

public class Animated extends Graphical {
	
	private HashMap<Integer, Point> destinations;

	public Animated(int size) {
		super(size);
	}
	
	@Override
	public void update(Displayable e) {
		if(e.isDisplayable()) {
			ProfGraphique.retirerElement(e.id());
			destinations.remove(e.id());
		} else {
			int x, y;
			if(destinations.containsKey(Integer.parseInt("" + e.id()))) {
				x = ProfGraphique.getX(e.id());
				y = ProfGraphique.getY(e.id());
			} else {
				x = e.position().multiply(imageSize()).x();
				y = e.position().multiply(imageSize()).y();
			}
			ProfGraphique.retirerElement(e.id());
			ProfGraphique.ajouterImage(e.id(), "/images/" + e.imgURL(), x, y);
			destinations.put(Integer.parseInt("" + e.id()), e.position().multiply(imageSize()));
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
	public void reset() {
		destinations = new HashMap<Integer, Point>();
		super.reset();
	}

}
