package ocean;

import java.util.Collection;
import java.util.Observer;

import element.Element;

public interface Usine {

	public Collection<Element> produire();

	public void ajouterOcean(Ocean ocean);

	public void ajouterObserver(Observer o);

}
