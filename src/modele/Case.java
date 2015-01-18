package modele;

import java.util.ArrayList;

/**
 * D�finit les m�thodes et les attributs permettant de g�rer l'objet Case.
 */
public class Case {

	private Coordonee pos;
	
	/**
	 * Permet de savoir si la case se trouve au bord de l'oc�an.
	 */
	private boolean estUnBord = false;
	
	/**
	 * Contient la direction qui depuis la case m�ne vers l'�xt�rieur de l'oc�an.
	 */
	private DIRECTION versExterieur;
	
	/**
	 * Contient les bateaux pr�sents dans la case.
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
	 * @return vrai si la case est un bord de l'oc�an et faux sinon.
	 */
	public boolean estUnBord() {
		return estUnBord;
	}
	
	/**
	 * Getteur de versExt�rieur
	 * @return retourne la direction qui depuis la case m�ne vers l'�xt�rieur de l'oc�an.
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
	 * Permet de conna�tre combien de bateaux sont dans la case.
	 * @return le nombre de bateaux pr�sents dans la case.
	 */
	public int nombreDeBateaux() {
		return this.listeBateaux.size();
	}
	
	/**
	 * Permet de r�cup�rer un bateau en connaissant son indice.
	 * @param i un indice
	 * @return le i �me bateaux pr�sent dans la case.
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
	 * Permet de r�parer tout les bateaux de la case.
	 */
	public void reparerBateaux() {
		for (Bateau b : listeBateaux)
			b.reparer();
	}

	
}
