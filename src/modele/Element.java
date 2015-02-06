package modele;

import affichage.Affichable;

public interface Element extends Affichable {
	
	public void jouerTour();

	public void toucher();
	public void reparer();
	
}
