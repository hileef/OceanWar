package element;

import java.util.Observable;

import modele.Coordonnee;
import modele.Direction;
import affichage.Affichable;
import controle.Simulation;

public abstract class Element extends Observable implements Affichable {

	private Direction direction;
	private Coordonnee position;
	private int id;

	public Element() {
		id = Simulation.idUnique();
	}

	@Override
	public int id() {
		return id;
	}

	@Override
	public Coordonnee position() {
		return position;
	}
	
	public void position(Coordonnee destination) {
		this.position = destination;
		this.setChanged();
		this.notifyObservers(EtatElement.POSITION_CHANGEE);
	}
	
	protected Direction direction() {
		return direction;
	}
	protected void direction(Direction d) {
		direction = d;
	}

	public void detruire() {
		this.setChanged();
		this.notifyObservers(EtatElement.DETRUIT);
	}
	
	public abstract void jouerPas() ;	

}
