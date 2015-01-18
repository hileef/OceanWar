package modele;

import java.util.LinkedList;
/**
 * Contient les attributs et les méthodes permettant de gérer l'objet BATEAU.
 */
public abstract class Bateau {
	/**
	 * Définit à combien de tirs peuvent résister les bateaux.
	 */
	private static final int resistanceMax = 5;
	
	/**
	 * Définit le nombre de "vies" qu'a le bateau.
	 */
	private int vies;
	
	/**
	 * Attribut de typeCase contenant la case ou le bateau se trouve.
	 */
	private Case position;
	
	/**
	 * Attribut de type DIRECTION qui permet au bateau de connaître sa direction de déplacement.
	 */
	private DIRECTION direction;
	
	/**
	 * Définit l'action du bateau.
	 */
	public abstract void agir() ;
	
	/**
	 * Permet de récupérer des informations sur l'entourage du bateau et ainsi changer certains attributs de BATEAU.
	 * @param bateauLePlusProche
	 * @param dir
	 * @param distance
	 */
	public abstract void infoEntourage(Case bateauLePlusProche, DIRECTION dir, int distance) ;
	
	/**
	 * Permet de mettre à jour la position du bateau
	 * @param destination Case dans laquelle il va être déplacé.
	 */
	public void position(Case destination) {
		this.position = destination;
	}
	
	/**
	 * Permet de connaître la case dans laquelle se trouve un bateau.
	 * @return la case dans laquelle est le bateau.
	 */
	public Case position() {
		return position;
	}
	
	/**
	 * Getteur de direction
	 * @return la direction dans laquelle se déplace le bateau.
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
	 * supprime une vie au bateau lorsque la méthode est appelée.
	 */
	public void toucher() { 
		vies--;
	}
	
	/**
	 * Remonte le niveau de vie à son maximum.
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
