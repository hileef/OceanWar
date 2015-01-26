package bateau;

import java.util.ArrayList;
import java.util.LinkedList;

import modele.DIRECTION;

public class Combattant extends BateauxDAction {

	private static final int porteeDeTir = 2;
	
	private LinkedList<Bateau> cibles = new LinkedList<Bateau>();
	private DIRECTION directionCibles;
	private int distanceCibles;
	
	@Override
	public DIRECTION determinerDirection() {
		if(cibles.isEmpty()) return directionAleatoire();
		else if(distanceCibles > porteeDeTir)
			return directionCibles;
		else if(distanceCibles == 1)
			return null;
		else
			return directionCibles;
	}
	
	@Override
	public void agir() {
		for(Bateau b : cibles)
			if(this.position().distance(b.position()) <= porteeDeTir) b.toucher();
	}

	@Override
	public String toString() {
		return "M";
	}

	@Override
	public void infosRadar(ArrayList<LinkedList<Bateau>> radar) {
		cibles.clear();
		Bateau lePlusProche = null;

		for(int i = 1; i < radar.size() && lePlusProche == null; ++i) {
			LinkedList<Bateau> liste = radar.get(i);
			if(!liste.isEmpty()) {
				lePlusProche = liste.peekFirst();
				distanceCibles = i;
				directionCibles = this.position().directionVers(lePlusProche.position());
				for(Bateau b : liste)
					if(b.position().equals(lePlusProche.position()))
						cibles.add(b);
			}
		}
		
	}



}
