package modele;

import java.util.ArrayList;
import java.util.LinkedList;

import controle.Statistics;
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
		Statistics.checkPoint("Pas De Simulation (init)");
		for (Bateau b : bateaux) {
			b.jouerPas();
			Statistics.checkPoint("Pas De Simulation : " + b.toString());
		}
			
		Statistics.checkPoint("Supprimer les bateaux detruits");
		supprimerBateauxDetruits();
		Statistics.checkPoint("- bateaux supprimes");
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
		int r, rayonRange = rayonMax - rayonMin + 1;
		ArrayList<LinkedList<Bateau>> cibles = new ArrayList<LinkedList<Bateau>>(rayonRange);
		for(int i = 0; i < rayonRange; ++i) cibles.add(i, new LinkedList<Bateau>());
		
		for (Bateau b : bateaux) if(b.id() != a.id())
			if((r = b.position().distance(a.position())) <= rayonMax && r >= rayonMin)
				cibles.get(r - rayonMin).add(b);
		return cibles;
	}
	public LinkedList<Bateau> radarPlusProches(Bateau a, int rayonMin, int rayonMax) {
		Statistics.checkPoint("Radar [2] [" + rayonMin + "," + rayonMax + "]");
		LinkedList<Bateau> liste = new LinkedList<Bateau>();
		
		int d, rmin = rayonMax + 1;

		for (Bateau b : bateaux) if(b.id() != a.id()) {
			d = a.position().distance(b.position());
			if(d > rayonMax || d < rayonMin) {
				;
			} else if(d < rmin) {
				liste.clear();
				liste.add(b);
				rmin = d;
			} else if(d == rmin) {
				liste.add(b);
			}
		}
		Statistics.checkPoint("- radar termine");
		return liste;
	}

	public LinkedList<Affichable> elementsAffichables() {
		Statistics.checkPoint(" Elements Affichables");
		LinkedList<Affichable> ret = new LinkedList<Affichable>();
		ret.addAll(bateaux);
		ret.addAll(bateauxDetruits);
		bateauxDetruits.clear();
		Statistics.checkPoint("- elements affichables donnes");
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
