package modele;

import java.util.LinkedList;
/**
 * Contient les attributs et les m�thodes permettant de g�rer l'objet BATEAU.
 */
public abstract class Bateau {
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
	private Case position;
	
	/**
	 * Attribut de type DIRECTION qui permet au bateau de conna�tre sa direction de d�placement.
	 */
	private DIRECTION direction;
	
	/**
	 * D�finit l'action du bateau.
	 */
	public abstract void agir() ;
	
	/**
	 * Permet de r�cup�rer des informations sur l'entourage du bateau et ainsi changer certains attributs de BATEAU.
	 * @param bateauLePlusProche
	 * @param dir
	 * @param distance
	 */
	public abstract void infoEntourage(Case bateauLePlusProche, DIRECTION dir, int distance) ;
	
	/**
	 * Permet de mettre � jour la position du bateau
	 * @param destination Case dans laquelle il va �tre d�plac�.
	 */
	public void position(Case destination) {
		this.position = destination;
	}
	
	/**
	 * Permet de conna�tre la case dans laquelle se trouve un bateau.
	 * @return la case dans laquelle est le bateau.
	 */
	public Case position() {
		return position;
	}
	
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
