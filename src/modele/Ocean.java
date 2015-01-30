package modele;

import java.util.ArrayList;
import java.util.LinkedList;

import affichage.Affichable;
import element.*;

public class Ocean {

	// CONSTANTES
	public static final int TAILLE_MATRICE = 15;

	// ATTRIBUTS
	private LinkedList<Bateau> bateaux;
	private LinkedList<Bateau> bateauxDetruits;

	// METHODES
	// Construction
	public Ocean() {
		bateaux = new LinkedList<Bateau>();
		bateauxDetruits = new LinkedList<Bateau>();
	}
	
//	// Concerne affichage console
//	public String toString() {
//		String out = "      ";
//		for(int i = 0; i < TAILLE_MATRICE; ++i) {
//			out = out.concat(" ");
//			out = out.concat((i > 9) ? "" : "0");
//			out = out.concat(i + " ");
//		}
//		out = out.concat("\n");
//		for(int i = 0; i < TAILLE_MATRICE; ++i) {		
//			out = out.concat(ligneToString());
//			out = out.concat((i > 9) ? "" : "0");
//			out = out.concat(i + " >  ");
//			for(int j = 0; j < TAILLE_MATRICE; ++j) {
//				out = out.concat(" " + positionToString(new Coordonee(j, i)) + " ");
//				if(j == TAILLE_MATRICE - 1) out = out.concat(" ");
//			}
//				
//			out = out.concat("\n");
//			if(i == TAILLE_MATRICE - 1)
//				out = out.concat(ligneToString());
//		}
//		return out;
//				
//	}
//	private String ligneToString() {
//		String out = "";
//		out = out.concat("      ");
//		for(int j = 0; j < TAILLE_MATRICE; ++j) out = out.concat("    ");
//		out = out.concat(" \n");
//		return out;
//	}
//	private String positionToString(Coordonee position) {
//		if(listeBateauxDansPosition(position) == null) System.out.println("pos to string : " + position);
//		LinkedList<Bateau> liste = listeBateauxDansPosition(position);
//		if(liste.isEmpty()) return "~~";
//		else if(liste.size() == 1) return liste.peek().toString() + "" + (liste.peek().id());
//		else return liste.size() + "x";
//	}
	


	
	// Concerne gestion de bateaux
	public void ajouterBateau(Bateau b, Coordonee position) {
		b.position(position);
		bateaux.add(b);
		b.ocean(this);
	}
	public void ajouterBateauSurPositionAleatoire(Bateau b) {
		ajouterBateau(b, Coordonee.aleatoire(TAILLE_MATRICE));
	}

	// Gestion d'un pas de simulations
	public void pasDeSimulation() {
		for (Bateau b : bateaux) 
			b.jouerPas();
		supprimerBateauxDetruits();
	}
	private void supprimerBateauxDetruits() {
		LinkedList<Bateau> aDetruire = new LinkedList<Bateau>();
		for (Bateau b : bateaux)
			if(b.estDetruit()) {
				aDetruire.add(b);
				bateauxDetruits.add(b);
			}	
		for(Bateau b : aDetruire) {
			bateaux.remove(b);
		}
			
	}

//	// Concerne les bateaux dans une seule case
//	private LinkedList<Bateau> listeBateauxDansPosition(Coordonee position) { 
//		LinkedList<Bateau> liste = new LinkedList<Bateau>();
//		for(Bateau b : bateaux) if(b.position().equals(position))
//			liste.add(b);
//		return liste;
//	}
	
	// Concerne les bateaux detectables par le radar 
	public ArrayList<LinkedList<Bateau>> radar(Bateau a) {
		return radar(a, 0, a.rayonRadar());
	}
	public ArrayList<LinkedList<Bateau>> radar(Bateau a, int rayonMin, int rayonMax) {
		int r, rayonRange = rayonMax - rayonMin + 1;
		ArrayList<LinkedList<Bateau>> cibles = new ArrayList<LinkedList<Bateau>>(rayonRange);
		for(int i = 0; i < rayonRange; ++i) cibles.add(i, new LinkedList<Bateau>());
		
		for (Bateau b : bateaux) if(b.id() != a.id())
			if((r = b.position().distance(a.position())) <= rayonMax && r >= rayonMin)
				cibles.get(r - rayonMin).add(b);
		return cibles;
	}

//	private LinkedList<Element> elements() {
//		LinkedList<Element> ret = new LinkedList<Element>();
//		ret.addAll(bateaux);
//		ret.addAll(vagues);
//		return ret;
//	}
	public LinkedList<Affichable> elementsAffichables() {
		LinkedList<Affichable> ret = new LinkedList<Affichable>();
		ret.addAll(bateaux);
		ret.addAll(bateauxDetruits);
		bateauxDetruits.clear();
		return ret;
	}
	

}
