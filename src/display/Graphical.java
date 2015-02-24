package display;

import core.Element;
import core.World;

public class Graphical extends ADisplay {

	public Graphical() {
		ProfGraphique.ouvrir("Ocean War", World.SIZE * IMAGE_SIZE, World.SIZE * IMAGE_SIZE);
		reinitaliser();
	}
	
	@Override
	public void update(Element e) {
		ProfGraphique.retirerElement(e.id());
		if(!e.isDestroyed()) {
			int x, y;
			x = e.position().multiply(IMAGE_SIZE).x();
			y = e.position().multiply(IMAGE_SIZE).y();
			ProfGraphique.ajouterImage(e.id(), "/images/" + e.imgURL(), x, y);
		}
	}
	
	@Override
	public void refresh(double seconds) {
		ProfGraphique.actualiser();
		pause(seconds);
	}

	public void reinitaliser() {
		ProfGraphique.retirerTout();
		ProfGraphique.ajouterImage(0, "/images/ocean.png", 0, 0);
		ProfGraphique.actualiser();
	}
	
	@Override
	public boolean askForReplay() {
		char c;
		ProfGraphique.retirerTout();
		ProfGraphique.ajouterLibelle(0, "Desirez-vous relancer (oui / non) ? _ _ _", 50, 50);
		ProfGraphique.actualiser();
		while((c = ProfGraphique.lireCaractereNonBloquant()) != 'o' && c !='n');
		return (c == 'o');
	}

}
