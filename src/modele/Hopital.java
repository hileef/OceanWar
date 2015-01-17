package modele;

import java.util.LinkedList;

public class Hopital extends Bateau {

	public DIRECTION determinerDirection() {
		if(position().estUnBord()) {
			LinkedList<DIRECTION> options = new LinkedList<DIRECTION>();
			switch(position().directionBordVersExterieur()) {
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
			}
			return directionAleatoireDepuis(options);
		} else if(direction() == null) {
			return directionAleatoire();
		} else return direction();
	}
	
	public void agir() {
		position().reparerBateaux();
	}

	public void infoEntourage(Case bateauLePlusProche, DIRECTION dir, int distance) { ;	}
	


}
