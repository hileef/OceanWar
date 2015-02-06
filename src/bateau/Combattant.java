package bateau;

import java.util.Collection;

import controle.Ocean;
import modele.Direction;
import modele.Element;

public class Combattant extends BateauDAction {

	public Combattant(Integer id, Ocean acces) {
		super(id, acces);
	}

	private static final int porteeDeTir = 2;
	
	@Override
	protected Direction calculerDirection(Collection<Element> liste) {
		if(liste.isEmpty()) return directionAleatoire();
		
		Element e = liste.iterator().next();
		int distanceCibles = this.position().distance(e.position());
		Direction directionCibles = this.position().directionVers(e.position());
		
		if(distanceCibles > porteeDeTir)
			return directionCibles;
		else if(distanceCibles == 1)
			return null;
		else
			return directionCibles;
	}
	
	@Override
	protected void agir(Collection<Element> liste) {
		
		for(Element b : liste)
			if(this.position().distance(b.position()) <= porteeDeTir) b.toucher();
	}

	@Override
	protected Collection<Element> listeBateauxDepuisRadar() {
		return acces().radar(this, 1, rayonRadar());
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
