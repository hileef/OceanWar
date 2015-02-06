package bateau;

import java.util.Collection;
import java.util.LinkedList;

import controle.Ocean;
import modele.Direction;
import modele.Element;

public abstract class BateauDAction extends Bateau {

	private static final int rayonRadar = 4;
	
	protected int rayonRadar() { return rayonRadar; }
	
	public BateauDAction(Integer id, Ocean acces) {
		super(id, acces);
	}
	
	protected abstract void agir(Collection<Element> liste);
	protected abstract Collection<Element> listeBateauxDepuisRadar() ;
	protected abstract Direction calculerDirection(Collection<Element> liste) ;
	
	@Override
	public void tour() {
		Collection<Element> liste = listeBateauxDepuisRadar();
		Direction dir = determinerDirection(liste);
		if(dir != null)
			this.position(this.position().coordonneeDansDirection(dir));
		agir(liste);
	}
	
	protected Direction determinerDirection(Collection<Element> liste) {
		direction(this.calculerDirection(liste));
		return direction();
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
