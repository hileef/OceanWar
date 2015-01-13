package modele;

import java.util.LinkedList;

public class Case {

	private boolean estUnBord = false;
	private DIRECTION versExterieur;
	
	private LinkedList<Bateau> listeBateaux;
	
	public Case(DIRECTION versExterieur) {
		estUnBord = true;
		this.versExterieur = versExterieur;
	}
	public Case() {
		listeBateaux = new LinkedList<Bateau>() ;
	}
	
	public boolean estUnBord() {
		return estUnBord;
	}
	public DIRECTION directionBordVersExterieur() {
		return versExterieur;
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
