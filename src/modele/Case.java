package modele;

import java.util.ArrayList;

/**
 * Définit les méthodes et les attributs permettant de gérer l'objet Case.
 */
public class Case {

	private Coordonee pos;
	
	/**
	 * Permet de savoir si la case se trouve au bord de l'océan.
	 */
	private boolean estUnBord = false;
	
	/**
	 * Contient la direction qui depuis la case mène vers l'éxtérieur de l'océan.
	 */
	private DIRECTION versExterieur;
	
	/**
	 * Contient les bateaux présents dans la case.
	 */
	private ArrayList<Bateau> listeBateaux;
	
	public Case(Coordonee coordonees, DIRECTION versExterieur) {
		this(coordonees);
		estUnBord = true;
		this.versExterieur = versExterieur;
	}
	public Case(Coordonee coordonees) {
		this.pos = coordonees;
		listeBateaux = new ArrayList<Bateau>() ;
	}
	
	public Coordonee coordonees() {
		return pos;
	}
	
	/**
	 * Getteur de estUnBord
	 * @return vrai si la case est un bord de l'océan et faux sinon.
	 */
	public boolean estUnBord() {
		return estUnBord;
	}
	
	/**
	 * Getteur de versExtérieur
	 * @return retourne la direction qui depuis la case mène vers l'éxtérieur de l'océan.
	 */
	public DIRECTION directionBordVersExterieur() {
		return versExterieur;
	}
	
	/**
	 * Permet de savoir si la case est vide.
	 * @return vrai si la case est vide et faux sinon.
	 */
	public boolean estVide() {
		return this.listeBateaux.isEmpty();
	}
	
	/**
	 * Permet de connaître combien de bateaux sont dans la case.
	 * @return le nombre de bateaux présents dans la case.
	 */
	public int nombreDeBateaux() {
		return this.listeBateaux.size();
	}
	
	/**
	 * Permet de récupérer un bateau en connaissant son indice.
	 * @param i un indice
	 * @return le i ème bateaux présent dans la case.
	 */
	public Bateau bateau(int i) {
		return this.listeBateaux.get(i);
	}
	
	/**
	 * Permet d'ajouter un bateau dans la case.
	 * @param b un bateau
	 */
	public void ajouterBateau(Bateau b) {
		this.listeBateaux.add(b);
	}
	
	/**
	 * Permet de retirer un bateau de la case.
	 * @param b un bateau
	 */
	public void enleverBateau(Bateau b) {
		this.listeBateaux.remove(b);
	}
	
	/**
	 * Permet de toucher tout les bateaux de la case.
	 */
	public void toucherBateaux() {
		for (Bateau b : listeBateaux)
			b.toucher();
	}
	
	/**
	 * Permet de réparer tout les bateaux de la case.
	 */
	public void reparerBateaux() {
		for (Bateau b : listeBateaux)
			b.reparer();
	}

	
}
