package bateau;

import java.util.Collection;

import ocean.Ocean;
import deplacement.Deplaceur;
import element.CiblageRadar;
import element.Element;

public abstract class BateauDAction extends Bateau {

	public BateauDAction(Integer id, Ocean acces, Deplaceur deplacement, CiblageRadar ciblage) {
		super(id, acces, deplacement, ciblage);
	}
	public BateauDAction(Integer id, Ocean acces, Deplaceur deplacement) {
		super(id, acces, deplacement);
	}
	
	@Override
	public void jouerTour() {
		agir(super.tour());
	}
	
	protected abstract void agir(Collection<Element> liste);

	
}
