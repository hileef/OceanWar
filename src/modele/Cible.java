package modele;

public class Cible extends Bateau {
	
	private DIRECTION suivante = null;
	private boolean retour = false;

	public DIRECTION determinerDirection() {
		if(direction() == null) {
			return DIRECTION.E;
		} else if(suivante != null) {
			DIRECTION tmp = suivante;
			suivante = null;
			return tmp;
		} else if(position().estUnBord()) {
			switch(position().directionBordVersExterieur()) {
			case NE:
				retour = false;
				return DIRECTION.O;
			case NO:
				suivante = DIRECTION.S;
				return DIRECTION.E;
			case SE:
				retour = true;
				return DIRECTION.O;
			case SO:
				retour = true;
				return DIRECTION.E;
			case E:
				suivante = DIRECTION.O;
				return (retour) ? DIRECTION.N : DIRECTION.S;
			case O:
				suivante = DIRECTION.E;
				return (retour) ? DIRECTION.N : DIRECTION.S;
			default:
				return direction();	
			}
		} else {
			return direction();
		}
	}
	
	public void agir() { ; }

}
