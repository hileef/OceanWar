package modele;

import java.util.LinkedList;

public class Case {

	private boolean estUnBord;
	
	private LinkedList<Bateau> listeBateaux;
	
	public Case() {
		
	}
	public Case(boolean estUnBord) {
		this.estUnBord = estUnBord;
	}
	
	public boolean estUnBord() {
		return estUnBord;
	}
	public void ajouterBateau(Bateau b) {
		this.listeBateaux.add(b);
	}
	public void enleverBateau(Bateau b) {
		this.listeBateaux.remove(b);
	}
	public void toucherBateaux() {
		
		for (Bateau b : listeBateaux){
			b.toucher();
		}
		
	}
	public void reparerBateaux() {
		
		for (Bateau b : listeBateaux){
			b.reparer();
		}
	}

	
}
