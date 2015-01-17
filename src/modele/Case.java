package modele;

import java.util.ArrayList;

public class Case {

	private Coordonee pos;
	
	private boolean estUnBord = false;
	private DIRECTION versExterieur;
	
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
	

	public boolean estUnBord() {
		return estUnBord;
	}
	public DIRECTION directionBordVersExterieur() {
		return versExterieur;
	}
	
	public boolean estVide() {
		return this.listeBateaux.isEmpty();
	}
	public int nombreDeBateaux() {
		return this.listeBateaux.size();
	}
	public Bateau bateau(int i) {
		return this.listeBateaux.get(i);
	}
	public void ajouterBateau(Bateau b) {
		this.listeBateaux.add(b);
	}
	public void enleverBateau(Bateau b) {
		this.listeBateaux.remove(b);
	}
	
	public void toucherBateaux() {
		for (Bateau b : listeBateaux)
			b.toucher();
	}
	public void reparerBateaux() {
		for (Bateau b : listeBateaux)
			b.reparer();
	}

	
}
