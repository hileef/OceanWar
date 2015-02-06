package bateau;

import modele.Direction;
import controle.Ocean;

public class Cible extends Bateau {

	public Cible(Integer id, Ocean acces) {
		super(id, acces);
	}

	private Direction suivante = null;
	private boolean retour = false;
	
	public Direction determinerDirection() {
		direction(this.calculerDirection());
		return direction();
	}
	
	private Direction calculerDirection() {
		if(direction() == null) {
			if(position().estUneBordure(0, Ocean.TAILLE_MATRICE - 1)) {
				switch(position().interpreterDirectionBordure(0, Ocean.TAILLE_MATRICE - 1)) {
				case NE :
					suivante = Direction.O;
					return Direction.S;
				case SE :
				case E :
					return Direction.O;
				default :
					return Direction.E;
				}
			} else return Direction.E;
		} else if(suivante != null) {
			Direction tmp = suivante;
			suivante = null;
			return tmp;
		} else {
			if(position().estUneBordure(0, Ocean.TAILLE_MATRICE - 1)) {
				switch(position().interpreterDirectionBordure(0, Ocean.TAILLE_MATRICE - 1)) {
				case NO:
					if(retour){ 
						retour = false;
						return Direction.E;
					}
					suivante = Direction.E;
					return Direction.S;
				case NE:
					if(retour) {
						retour = false;
						return Direction.O;
					}
					suivante = Direction.O;
					return Direction.S;
				case SE:
					if(retour) {
						suivante = Direction.O;
						return Direction.N;
					}
					retour = true;
					return Direction.O;
				case SO:
					if(retour) {
						suivante = Direction.E;
						return Direction.N;
					}
					retour = true;
					return Direction.E;
				case E:
					suivante = Direction.O;
					return (retour) ? Direction.N : Direction.S;
				case O:
					suivante = Direction.E;
					return (retour) ? Direction.N : Direction.S;
				default:
					return direction();	
				}
			} else {
				return direction();
			}

		}
	}

	@Override
	public String toString() {
		return "~C";
	}
	
	@Override
	public String imageURL() {
		return "civil" + this.imageURLComposante();
	}

	@Override
	public void tour() {
		Direction dir = this.determinerDirection();
		if(dir != null) this.position(position().coordonneeDansDirection(dir));;
	}
	




}
