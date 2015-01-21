package modele;

import java.util.LinkedList;

public class Combattant extends Bateau {

	private static final int porteeDeTir = 2;
	
	private LinkedList<Bateau> cibles = new LinkedList<Bateau>();
	private DIRECTION directionCibles;
	private int distanceCibles;
	
	public DIRECTION determinerDirection() {
		if(cibles.isEmpty()) return directionAleatoire();
		else if(distanceCibles > porteeDeTir)
			return directionCibles;
		else if(distanceCibles == 1)
			return null;
		else
			return directionCibles;
	}
	
	public void agir() {
		for(Bateau b : cibles)
			if(this.position().distance(b.position()) <= porteeDeTir) b.toucher();
	}

	public void infosRadar(LinkedList<Bateau> listeMemeEndroit, LinkedList<Bateau> listeTrieeEntourage) { 
		cibles.clear();

		if(!listeTrieeEntourage.isEmpty()) {
			Bateau lePlusProche = listeTrieeEntourage.peekFirst();
			distanceCibles = lePlusProche.position().distance(this.position()); 
				
			for(Bateau b : listeTrieeEntourage) if(b.position().equals(lePlusProche.position()))
				cibles.add(b);
				
			directionCibles = this.position().directionVers(cibles.peekFirst().position());
		}
		




	}

	public String toString() {
		return "M";
	}

}
