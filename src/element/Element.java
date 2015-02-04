package element;

import java.util.Observable;

import modele.Coordonee;
import modele.Direction;
import affichage.Affichable;
import controle.Simulation;

public abstract class Element extends Observable implements Affichable {

	private Direction direction;
	private Coordonee position;
	private int id;

	public Element() {
		id = Simulation.idUnique();
	}

	@Override
	public int id() {
		return id;
	}

	@Override
	public Coordonee position() {
		return position;
	}
	
	public void position(Coordonee destination) {
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
