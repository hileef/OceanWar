package bateau;

import java.util.Observable;

import controle.Ocean;
import modele.Coordonnee;
import modele.Direction;
import modele.Element;
import modele.EtatElement;

public abstract class Bateau extends Observable implements Element {
	private static final int NB_VIES_DEFAUT = 8;
	
	private Ocean acces;
	
	private Integer id;
	private Coordonnee position;
	private Direction direction;
	private int vies;
	
	
	public Bateau(Integer id, Ocean acces) {
		this.id = id;
		this.acces = acces;
		this.vies = NB_VIES_DEFAUT;
		this.position(Coordonnee.aleatoire(Ocean.TAILLE_MATRICE));
	}
	
	protected Ocean acces() { return acces; }
	protected void direction(Direction dir) { this.direction = dir; }
	protected void position(Coordonnee pos) {
		if(vies > 0) {
			this.position = pos;
			this.setChanged();
			this.notifyObservers(EtatElement.POSITION_CHANGEE);
		} 
	}

	@Override
	public Integer id() { return this.id; }

	@Override
	public Coordonnee position() { return this.position; }
	
	@Override
	public Direction direction() { return this.direction; }

	@Override
	public void toucher() {
		if(this.vies > 0) {
			this.vies = this.vies - 1;
			if(this.vies == 0) {
				this.setChanged();
				this.notifyObservers(EtatElement.DETRUIT);
			}
		}

	}

	@Override
	public void reparer() {
		if(this.vies > 0)
			this.vies = NB_VIES_DEFAUT;
	}
	
	protected String imageURLComposante() {
		if(direction() == null)
			return ".png";
		else switch(direction()) {
		case NE:
		case E:
		case SE:
		case S:
			return "d.png";
		default:
			return ".png";
		}

	}
	
	public abstract void tour();

	@Override
	public void jouerTour() {
		Coordonnee x = this.position();
		this.tour();
		if(x.equals(position()))
			this.position(position());
	}


	
}
