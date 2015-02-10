package bateau;

import java.util.Collection;

import ocean.Ciblage;
import ocean.Element;
import ocean.Ocean;

public class Hopital extends BateauDAction {
	

	public Hopital(Integer id, Ocean acces, StrategieDeplacement deplacement, Ciblage ciblage) {
		super(id, acces, deplacement, ciblage);
	}
	public Hopital(Integer id, Ocean acces, StrategieDeplacement deplacement) {
		super(id, acces, deplacement);
	}
	
	@Override
	public String toString() {
		return "~H";
	}
	
	@Override
	public String imageURL() {
		return "hopital" + this.imageURLComposante();
	}

	@Override
	protected void agir(Collection<Element> liste) {
		if(!liste.isEmpty())
			for(Element b : liste) 
				if(b.position().equals(this.position())) b.reparer();
	}

}
