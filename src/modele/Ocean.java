package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import bateau.Bateau;
import controle.Simulation;
import affichage.Affichable;

public class Ocean implements Affichable {

	// CONSTANTES
	public static final int TAILLE_MATRICE = 15;

	// ATTRIBUTS
	private ArrayList<Bateau> bateaux;
	private int id;

	// METHODES
	// Construction
	public Ocean() {
		id = Simulation.idUnique();
		bateaux = new ArrayList<Bateau>();
	}
	// Concerne affichage console
	public String toString() {
		String out = "      ";
		for(int i = 0; i < TAILLE_MATRICE; ++i) {
			out = out.concat(" ");
			out = out.concat((i > 9) ? "" : "0");
			out = out.concat(i + " ");
		}
		out = out.concat("\n");
		for(int i = 0; i < TAILLE_MATRICE; ++i) {		
			out = out.concat(ligneToString());
			out = out.concat((i > 9) ? "" : "0");
			out = out.concat(i + " >  ");
			for(int j = 0; j < TAILLE_MATRICE; ++j) {
				out = out.concat(" " + positionToString(new Coordonee(j, i)) + " ");
				if(j == TAILLE_MATRICE - 1) out = out.concat(" ");
			}
				
			out = out.concat("\n");
			if(i == TAILLE_MATRICE - 1)
				out = out.concat(ligneToString());
		}
		return out;
				
	}
	private String ligneToString() {
		String out = "";
		out = out.concat("      ");
		for(int j = 0; j < TAILLE_MATRICE; ++j) out = out.concat("    ");
		out = out.concat(" \n");
		return out;
	}
	private String positionToString(Coordonee position) {
		if(listeBateauxDansPosition(position) == null) System.out.println("pos to string : " + position);
		LinkedList<Bateau> liste = listeBateauxDansPosition(position);
		if(liste.isEmpty()) return "~~";
		else if(liste.size() == 1) return liste.peek().toString() + "" + (liste.peek().id());
		else return liste.size() + "x";
	}
	
	public int id() {
		return id;
	}

	
	// Concerne gestion de bateaux
	public void ajouterBateau(Bateau b, Coordonee position) {
		b.position(position);
		bateaux.add(b);
	}
	public void ajouterBateauSurPositionAleatoire(Bateau b) {
		ajouterBateau(b, Coordonee.aleatoire(TAILLE_MATRICE));
	}
	private void supprimerBateau(Bateau b) {
		bateaux.remove(b);
	}

	// Gestion d'un pas de simulations
	public void pasDeSimulation() {
		for (Bateau b : bateaux) {
			
			// Envoi informations "radar" bateau
			b.infosRadar(radar(b));
			
			// DÃ©placement Bateau dans direction dÃ©sirÃ©e
			deplacerBateau(b);
			
			// Faire agir le bateau
			b.agir();
			
		}
		// Suppression des bateaux detruits.
		LinkedList<Bateau> aDetruire = new LinkedList<Bateau>();
		for (Bateau b : bateaux)
			if(b.vies() == 0)
				aDetruire.add(b);
		for(Bateau b : aDetruire)
			supprimerBateau(b);
	}
	
	private void deplacerBateau(Bateau b) {
		DIRECTION dir = b.determinerDirection();
		if(dir != null) 
			b.position(b.position().coordoneeDansDirection(dir));
	}

	// Concerne les bateaux dans une seule case
	private LinkedList<Bateau> listeBateauxDansPosition(Coordonee position) { 
		LinkedList<Bateau> liste = new LinkedList<Bateau>();
		for(Bateau b : bateaux) if(b.position().equals(position))
			liste.add(b);
		return liste;
	}
	
	// Concerne les bateaux detectables par le radar 
	private ArrayList<LinkedList<Bateau>> radar(Bateau a) {
		return radar(a, 0, a.rayonRadar());
	}
	private ArrayList<LinkedList<Bateau>> radar(Bateau a, int rayonMin, int rayonMax) {
		int r, rayonRange = rayonMax - rayonMin + 1;
		ArrayList<LinkedList<Bateau>> cibles = new ArrayList<LinkedList<Bateau>>(rayonRange);
		for(int i = 0; i < rayonRange; ++i) cibles.set(i, new LinkedList<Bateau>());
		
		for (Bateau b : bateaux) if(b.id() != a.id())
			if((r = b.position().distance(a.position())) <= rayonMax && r >= rayonMin)
				cibles.get(r - rayonMin).add(b);
		return cibles;
	}



}
