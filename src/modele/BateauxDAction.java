package modele;

import java.util.LinkedList;

public abstract class BateauxDAction extends Bateau {

	
	protected DIRECTION directionAleatoire() {
		LinkedList<DIRECTION> options = new LinkedList<DIRECTION>();
		if(!position().estUneBordure(0, Ocean.TAILLE_MATRICE - 1))
			return directionAleatoireSansPenserAuBords();
		switch(position().interpreterDirectionBordure(0, Ocean.TAILLE_MATRICE - 1)) {
		case NO :
			options.add(DIRECTION.SE);
			options.add(DIRECTION.S);
			options.add(DIRECTION.E);
			break;
		case NE :
			options.add(DIRECTION.SO);
			options.add(DIRECTION.S);
			options.add(DIRECTION.O);
			break;
		case SO :
			options.add(DIRECTION.NE);
			options.add(DIRECTION.N);
			options.add(DIRECTION.E);
			break;
		case SE :
			options.add(DIRECTION.NO);
			options.add(DIRECTION.N);
			options.add(DIRECTION.O);
			break;
		case N :
			options.add(DIRECTION.S);
			options.add(DIRECTION.SE);
			options.add(DIRECTION.SO);
			break;
		case S :
			options.add(DIRECTION.N);
			options.add(DIRECTION.NE);
			options.add(DIRECTION.NO);
			break;
		case O :
			options.add(DIRECTION.E);
			options.add(DIRECTION.NE);
			options.add(DIRECTION.SE);
			break;
		case E :
			options.add(DIRECTION.O);
			options.add(DIRECTION.NO);
			options.add(DIRECTION.SO);
			break;
		default:
			return directionAleatoireSansPenserAuBords();
		}
		return directionAleatoireDepuis(options);
	}
	protected DIRECTION directionAleatoireSansPenserAuBords() {
		LinkedList<DIRECTION> liste = new LinkedList<DIRECTION>();
		for(DIRECTION d : DIRECTION.values())
			liste.add(d);
		return directionAleatoireDepuis(liste);
	}
	protected DIRECTION directionAleatoireDepuis(LinkedList<DIRECTION> options) {
		return options.get((int) (Math.random() * (options.size() - 1)));
	}

	/**
	 * Définit l'action du bateau.
	 */
	public abstract void agir() ;


}
