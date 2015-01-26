package modele;

import java.util.LinkedList;

import affichage.Affichable;
import controle.Simulation;
/**
 * Contient les attributs et les mï¿½thodes permettant de gï¿½rer l'objet BATEAU.
 */
public abstract class Bateau implements Affichable {
	/**
	 * Dï¿½finit ï¿½ combien de tirs peuvent rï¿½sister les bateaux.
	 */
	private static final int resistanceMax = 5;
	
	/**
	 * Dï¿½finit le nombre de "vies" qu'a le bateau.
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
	 * Permet de mettre ï¿½ jour la position du bateau
	 * @param destination Case dans laquelle il va ï¿½tre dï¿½placï¿½.
	 */
	public void position(Coordonee destination) {
		this.position = destination;
	}
	
	/**
	 * Permet de connaï¿½tre la case dans laquelle se trouve un bateau.
	 * @return la case dans laquelle est le bateau.
	 */
	public Coordonee position() {
		return position;
	}
	
	
	/**
	 * Attribut de type DIRECTION qui permet au bateau de connaï¿½tre sa direction de dï¿½placement.
	 */
	private DIRECTION direction;
	
	
	
	// TODO DOC A REFAIRE
	public abstract void infosRadar(LinkedList<Bateau> listeMemeEndroit, LinkedList<Bateau> listeTrieeEntourage) ;
	

	
	/**
	 * Getteur de direction
	 * @return la direction dans laquelle se dï¿½place le bateau.
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
	
	
	public abstract DIRECTION determinerDirection() ;
	
	/**
	 * supprime une vie au bateau lorsque la mï¿½thode est appelï¿½e.
	 */
	public void toucher() { 
		vies--;
		System.out.println("DEBUG : " + this.toString() + "#" + this.id() + " Ã  Ã©tÃ© touchÃ©. vies = " + vies);
	}
	
	/**
	 * Remonte le niveau de vie ï¿½ son maximum.
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
