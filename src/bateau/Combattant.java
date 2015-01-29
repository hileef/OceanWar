package bateau;

import java.util.ArrayList;
import java.util.LinkedList;

import modele.DIRECTION;

public class Combattant extends BateauxDAction {
	
	private static final int porteeDeTir = 2;
	
	public DIRECTION determinerDirection(LinkedList<Bateau> liste) {
		if(liste.isEmpty()) return directionAleatoire();
		
		int distanceCibles = this.position().distance(liste.peekFirst().position());
		DIRECTION directionCibles = this.position().directionVers(liste.peekFirst().position());
		
		if(distanceCibles > porteeDeTir)
			return directionCibles;
		else if(distanceCibles == 1)
			return null;
		else
			return directionCibles;
	}
	
	public void agir(LinkedList<Bateau> liste) {
		
		for(Bateau b : liste)
			if(this.position().distance(b.position()) <= porteeDeTir) b.toucher();
	}

	@Override
	public String toString() {
		return "M";
	}

	private LinkedList<Bateau> listeBateauxDepuisRadar() {
		
		ArrayList<LinkedList<Bateau>> radar = ocean().radar(this, 1, rayonRadar());
		
		LinkedList<Bateau> cibles = new LinkedList<Bateau>();
		Bateau lePlusProche = null;

		for(int i = 1; i < radar.size() && lePlusProche == null; ++i) {
			LinkedList<Bateau> liste = radar.get(i);
			if(!liste.isEmpty()) {
				lePlusProche = liste.peekFirst();
				for(Bateau b : liste)
					if(b.position().equals(lePlusProche.position()))
						cibles.add(b);
			}
		}
		
		return cibles;
	}
	
	@Override
	public void jouerPas() {
		LinkedList<Bateau> liste = listeBateauxDepuisRadar();
		DIRECTION dir = determinerDirection(liste);
		if(dir != null)
			this.position(this.position().coordoneeDansDirection(dir));
		agir(liste);
	}

	@Override
	public String imageURL() {
		return "Militaire.gif";
	}



}
