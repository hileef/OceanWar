package affichage;

import modele.Coordonnee;

public interface Affichable {

	public int id() ;
	public String toString() ;
	public Coordonnee position();
	public String imageURL() ;
	public boolean estDetruit();
	
}
