package modele;

import java.util.Collection;
import java.util.Observer;

import controle.Ocean;

public interface Usine {

	public Collection<Element> produire();

	public void ajouterOcean(Ocean ocean);

	public void ajouterObserver(Observer o);

}
