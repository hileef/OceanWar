package bateau;

import ocean.Ocean;
import deplacement.Boustrophedon;
import deplacement.Deplaceur;

public class Cible extends Bateau {

	public Cible(Integer id, Ocean acces) {
		super(id, acces, new Boustrophedon());
	}
	public Cible(Integer id, Ocean acces, Deplaceur deplacement) {
		super(id, acces, deplacement);
	}

	@Override
	public String toString() {
		return "~C";
	}
	
	@Override
	public String imageURL() {
		return "civil" + this.imageURLComposante();
	}

}
