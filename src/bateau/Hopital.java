package bateau;

import java.util.Collection;

import modele.Direction;
import modele.Element;
import controle.Ocean;

public class Hopital extends BateauDAction {

	public Hopital(Integer id, Ocean acces) {
		super(id, acces);
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
				b.reparer();
	}

	@Override
	protected Collection<Element> listeBateauxDepuisRadar() {
		return acces().radar(this, 0, 0);
	}

	@Override
	protected Direction calculerDirection(Collection<Element> liste) {
		if(direction() == null) {
			return directionAleatoire();
		} else if(position().estUneBordure(0, Ocean.TAILLE_MATRICE - 1))
			return directionAleatoire();
		else return direction();
	}

}
