package modele;

import java.util.LinkedList;

import affichage.Affichable;
import controle.Simulation;
/**
 * Contient les attributs et les m�thodes permettant de g�rer l'objet BATEAU.
 */
public abstract class Bateau implements Affichable {
	/**
	 * D�finit � combien de tirs peuvent r�sister les bateaux.
	 */
	private static final int resistanceMax = 5;
	
	/**
	 * D�finit le nombre de "vies" qu'a le bateau.
	 */
	private int vies;
	
	/**
	 * Attribut de typeCase contenant la case ou le bateau se trouve.
	 */
	// private Case position;
	
	// TODO DOC A REFAIRE
	public abstract String toString();
	private int rayonRadar = 3;
	private Coordonee position;
	public Bateau() {
		this.id = Simulation.idUnique();
		vies = resistanceMax;
	}
	private int id;
	public int id() {
		return id;
	}
	public int rayonRadar() {
		return rayonRadar;
	}
	
	/**
	 * Permet de mettre � jour la position du bateau
	 * @param destination Case dans laquelle il va �tre d�plac�.
	 */
	public void position(Coordonee destination) {
		this.position = destination;
	}
	
	/**
	 * Permet de conna�tre la case dans laquelle se trouve un bateau.
	 * @return la case dans laquelle est le bateau.
	 */
	public Coordonee position() {
		return position;
	}
	
	
	/**
	 * Attribut de type DIRECTION qui permet au bateau de conna�tre sa direction de d�placement.
	 */
	private DIRECTION direction;
	
	/**
	 * D�finit l'action du bateau.
	 */
	public abstract void agir() ;
	
	// TODO DOC A REFAIRE
	public abstract void infosRadar(LinkedList<Bateau> listeMemeEndroit, LinkedList<Bateau> listeTrieeEntourage) ;
	

	
	/**
	 * Getteur de direction
	 * @return la direction dans laquelle se d�place le bateau.
	 */
	protected DIRECTION direction() {
		return direction;
	}
	
	/**
	 * Setter de direction
	 * @param d une direction
	 */
	protected void direction(DIRECTION d) {
		direction = d;
	}
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
	
	public abstract DIRECTION determinerDirection() ;
	
	/**
	 * supprime une vie au bateau lorsque la m�thode est appel�e.
	 */
	public void toucher() { 
		vies--;
		System.out.println("DEBUG : " + this.toString() + "#" + this.id() + " à été touché. vies = " + vies);
	}
	
	/**
	 * Remonte le niveau de vie � son maximum.
	 */
	public void reparer() { 
		vies = resistanceMax;
	}
	
	/**
	 * Getteur de vies.
	 * @return le nombre de vies qu'a le bateau.
	 */
	public int vies() {
		return vies;
	}
	
}
