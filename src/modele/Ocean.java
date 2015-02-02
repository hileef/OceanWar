package modele;

import java.util.ArrayList;
import java.util.LinkedList;

import affichage.Affichable;
import element.*;

public class Ocean {

	// CONSTANTES
	public static final int TAILLE_MATRICE = 15;

	// ATTRIBUTS
	private ArrayList<Bateau> bateaux;
	private ArrayList<Bateau> bateauxDetruits;

	// METHODES
	// Construction
	public Ocean() {
		bateaux = new ArrayList<Bateau>();
		bateauxDetruits = new ArrayList<Bateau>();
	}
	
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
		// TODO OPTIMISER
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

	
	// Concerne les bateaux detectables par le radar 
	public ArrayList<LinkedList<Bateau>> radar(Bateau a) {
		return radar(a, 0, a.rayonRadar());
	}
	public ArrayList<LinkedList<Bateau>> radar(Bateau a, int rayonMin, int rayonMax) {
		// TODO OPTIMISER
		int r, rayonRange = rayonMax - rayonMin + 1;
		ArrayList<LinkedList<Bateau>> cibles = new ArrayList<LinkedList<Bateau>>(rayonRange);
		for(int i = 0; i < rayonRange; ++i) cibles.add(i, new LinkedList<Bateau>());
		
		for (Bateau b : bateaux) if(b.id() != a.id())
			if((r = b.position().distance(a.position())) <= rayonMax && r >= rayonMin)
				cibles.get(r - rayonMin).add(b);
		return cibles;
	}

	public LinkedList<Affichable> elementsAffichables() {
		LinkedList<Affichable> ret = new LinkedList<Affichable>();
		ret.addAll(bateaux);
		ret.addAll(bateauxDetruits);
		bateauxDetruits.clear();
		return ret;
	}
	
	// POUR LES TESTS
	public boolean estPresent(Bateau b){
		return this.bateaux.contains(b);
	}
	public boolean aEteSupprime(Bateau b){
		supprimerBateauxDetruits();
		return this.bateauxDetruits.contains(b);
	}

}
