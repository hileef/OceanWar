package bateau;

import ocean.Ocean;

public class Cible extends Bateau {

	public Cible(Integer id, Ocean acces, StrategieDeplacement deplacement) {
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
