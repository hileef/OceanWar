package element;

import java.util.LinkedList;

import modele.Coordonee;
import modele.Direction;
import modele.Ocean;
import affichage.Affichable;
import controle.Simulation;

public abstract class Element implements Affichable {

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
	}
	
	protected Direction direction() {
		return direction;
	}
	protected void direction(Direction d) {
		direction = d;
	}
	
	protected Direction directionAleatoire() {
		LinkedList<Direction> options = new LinkedList<Direction>();
		if(!position().estUneBordure(0, Ocean.TAILLE_MATRICE - 1))
			return directionAleatoireSansPenserAuBords();
		switch(position().interpreterDirectionBordure(0, Ocean.TAILLE_MATRICE - 1)) {
		case NO :
			options.add(Direction.SE);
			options.add(Direction.S);
			options.add(Direction.E);
			break;
		case NE :
			options.add(Direction.SO);
			options.add(Direction.S);
			options.add(Direction.O);
			break;
		case SO :
			options.add(Direction.NE);
			options.add(Direction.N);
			options.add(Direction.E);
			break;
		case SE :
			options.add(Direction.NO);
			options.add(Direction.N);
			options.add(Direction.O);
			break;
		case N :
			options.add(Direction.S);
			options.add(Direction.SE);
			options.add(Direction.SO);
			break;
		case S :
			options.add(Direction.N);
			options.add(Direction.NE);
			options.add(Direction.NO);
			break;
		case O :
			options.add(Direction.E);
			options.add(Direction.NE);
			options.add(Direction.SE);
			break;
		case E :
			options.add(Direction.O);
			options.add(Direction.NO);
			options.add(Direction.SO);
			break;
		default:
			return directionAleatoireSansPenserAuBords();
		}
		return directionAleatoireDepuis(options);
	}
	protected Direction directionAleatoireSansPenserAuBords() {
		LinkedList<Direction> liste = new LinkedList<Direction>();
		for(Direction d : Direction.values())
			liste.add(d);
		return directionAleatoireDepuis(liste);
	}
	protected Direction directionAleatoireDepuis(LinkedList<Direction> options) {
		return options.get((int) (Math.random() * (options.size())));
	}
}
