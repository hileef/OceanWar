package affichage;

import java.util.Observer;

import element.Coordonnee;
import element.Direction;

public interface Affichable {

	public void addObserver(Observer o);
	
	public String toString();
	public String imageURL();
	
	public Integer id();
	public Coordonnee position();
	public Direction direction();
	
}
