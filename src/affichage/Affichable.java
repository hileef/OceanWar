package affichage;

import modele.Coordonee;

public interface Affichable {

	public int id() ;
	public String toString() ;
	public Coordonee position();
	public String imageURL() ;
	public boolean estDetruit();
	
}
