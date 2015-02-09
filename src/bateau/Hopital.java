package bateau;

import java.util.Collection;

import ocean.Ocean;
import deplacement.Deplaceur;
import deplacement.Pong;
import element.CiblageRadar;
import element.Element;

public class Hopital extends BateauDAction {
	

	public Hopital(Integer id, Ocean acces, Deplaceur deplacement, CiblageRadar ciblage) {
		super(id, acces, deplacement, ciblage);
	}
	public Hopital(Integer id, Ocean acces, Deplaceur deplacement) {
		super(id, acces, deplacement);
	}
	public Hopital(Integer id, Ocean acces) {
		this(id, acces, new Pong());
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
