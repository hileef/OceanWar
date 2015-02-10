package bateau;

import java.util.Collection;

import ocean.Ciblage;
import ocean.Element;
import ocean.Ocean;

public abstract class BateauDAction extends Bateau {

	public BateauDAction(Integer id, Ocean acces, StrategieDeplacement deplacement, Ciblage ciblage) {
		super(id, acces, deplacement, ciblage);
	}
	public BateauDAction(Integer id, Ocean acces, StrategieDeplacement deplacement) {
		super(id, acces, deplacement);
	}
	
	@Override
	public void jouerTour() {
		agir(super.tour());
	}
	
	protected abstract void agir(Collection<Element> liste);

	
}
