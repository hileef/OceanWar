package element;

import affichage.Affichable;

public interface Element extends Affichable {
	
	public void jouerTour();
	
	public CiblageRadar ciblage();

	public void toucher();
	public void reparer();
	public int vies();
	public int viesMax();
	
}
