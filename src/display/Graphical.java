package display;

import core.Displayable;

public class Graphical extends ADisplay {

	public Graphical(int size) {
		super(size);
		ProfGraphique.ouvrir("Ocean War",  worldSize() * imageSize(),  worldSize() * imageSize());
		reset();
	}
	
	@Override
	public void update(Displayable e) {
		ProfGraphique.retirerElement(e.id());
		if(!e.isDisplayable()) {
			int x, y;
			x = e.position().multiply(imageSize()).x();
			y = e.position().multiply(imageSize()).y();
			ProfGraphique.ajouterImage(e.id(), "/images/" + e.imgURL(), x, y);
		}
	}
	
	@Override
	public void refresh(double seconds) {
		ProfGraphique.actualiser();
		pause(seconds);
	}

	@Override
	public void reset() {
		ProfGraphique.retirerTout();
		ProfGraphique.actualiser();
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
