package ocean;

import java.util.Observer;

public interface Affichage extends Observer {

	public void actualiser(double secondes);
	
	public boolean demanderSiRejouer();
	
	public void reinitaliser();

}