package bateau;

import java.util.Collection;

import ocean.Ciblage;
import ocean.Direction;
import ocean.Element;
import ocean.Ocean;

public class Combattant extends BateauDAction {

	public Combattant(Integer id, Ocean acces, StrategieDeplacement deplacement, Ciblage ciblage) {
		super(id, acces, deplacement, ciblage);
	}
	public Combattant(Integer id, Ocean acces, StrategieDeplacement deplacement) {
		this(id, acces, deplacement, Ciblage.PROCHE);
	}

	private static final int porteeDeTir = 2;
	
	@Override
	protected Direction determinerDirection(Collection<Element> liste) {
		if(!liste.isEmpty() && this.position().distance(liste.iterator().next().position()) == 1)
			return null;
		else return super.determinerDirection(liste);
	}
	
	@Override
	protected void agir(Collection<Element> liste) {
		for(Element b : liste) {
			int d = this.position().distance(b.position());
			if(d <= porteeDeTir && d > 0)
				b.toucher();
		}
			
	}

	@Override
	public String toString() {
		return "~M";
	}
	
	@Override
	public String imageURL() {
		return "militaire" + this.imageURLComposante();
	}


}
