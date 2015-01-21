package modele;

import java.util.HashMap;
import java.util.LinkedList;

import controle.Simulation;
import affichage.Affichable;

public class Ocean implements Affichable {

	// CONSTANTES
	public static final int TAILLE_MATRICE = 15;

	// ATTRIBUTS
	private HashMap<Integer, Bateau> bateaux;
	private HashMap<Coordonee, LinkedList<Integer>> cases;
	private int id;

	// METHODES
	// Construction
	public Ocean() {
		id = Simulation.idUnique();
		bateaux = new HashMap<Integer, Bateau>();
		cases = new HashMap<Coordonee, LinkedList<Integer>>();
		for(int i = 0; i < TAILLE_MATRICE; ++i)
			for(int j = 0; j < TAILLE_MATRICE; ++j)
				cases.put(new Coordonee(j, i), new LinkedList<Integer>());
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
		bateaux.put(b.id(), b);
		cases.get(position).add(b.id());
	}
	public void ajouterBateauSurPositionAleatoire(Bateau b) {
		ajouterBateau(b, Coordonee.aleatoire(TAILLE_MATRICE));
	}
	private void supprimerBateau(Bateau b) {
		Integer id = b.id();
		cases.get(b.position()).remove(id);
		bateaux.remove(id);
	}

	// Gestion d'un pas de simulations
	public void pasDeSimulation() {
		for (Bateau b : bateaux.values()) {
			
			// Envoi informations "radar" bateau
			LinkedList<Bateau> liste = listeBateauxDansPosition(b.position());
			liste.remove(b);
			b.infosRadar(liste, listeBateauxRadar(b));
			
			// Déplacement Bateau dans direction désirée
			deplacerBateau(b);
			
			// Faire agir le bateau
			b.agir();
			
		}
		// Suppression des bateaux detruits.
		LinkedList<Bateau> aDetruire = new LinkedList<Bateau>();
		for (Bateau b : bateaux.values())
			if(b.vies() == 0)
				aDetruire.add(b);
		System.out.println(aDetruire);
		for(Bateau b : aDetruire)
			supprimerBateau(b);
	}
	
	private void deplacerBateau(Bateau b) {
		cases.get(b.position()).remove(new Integer(b.id()));
		DIRECTION dir = b.determinerDirection();
		if(dir != null) 
			b.position(b.position().coordoneeDansDirection(dir));
		if(cases.get(b.position()) == null) {
			 System.out.println("DEBUG : Bateau " + b + "#" + b.id());
			 System.out.println("DEBUG : Position" + b.position());
			 System.out.println("DEBUG : Case a position : " + cases.get(b.position()));
			
		}

		cases.get(b.position()).add(b.id());
	}

	// Concerne les bateaux dans une seule case
	private LinkedList<Bateau> listeBateauxDansPosition(Coordonee position) { 
		LinkedList<Bateau> liste = new LinkedList<Bateau>();
		for(Integer id : cases.get(position)) 
			liste.add(bateaux.get(id));
		return liste;
	}
	
	// Concerne les bateaux detectables par le radar 
	private LinkedList<Bateau> listeBateauxRadar(Bateau a) {
		return listeBateauxRadar(a, 1, a.rayonRadar());
	}
	private LinkedList<Bateau> listeBateauxRadar(Bateau a, int rayonMin, int rayonMax) {
		int r, rayonRange = rayonMax - rayonMin + 1;
		LinkedList<Bateau> aOrdonner[] = new LinkedList[rayonRange + 1];
		for(int i = 0; i < rayonRange; ++i) aOrdonner[i] = new LinkedList<Bateau>(); 
		for (Bateau b : bateaux.values()) if(b != a)
			if((r = b.position().distance(a.position())) <= rayonMax && r >= rayonMin)
				aOrdonner[r - rayonMin].add(b);
		for(int i = 1; i < rayonRange; ++i) aOrdonner[0].addAll(aOrdonner[i]);
		return aOrdonner[0];
	}



}
