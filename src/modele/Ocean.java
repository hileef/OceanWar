package modele;

import java.util.ArrayList;

public class Ocean {

	// CONSTANTES
	private static final int TAILLE_MATRICE = 10;
	private static final int RAYON_ENTOURAGE = 3;

	// ATTRIBUTS
	private Case matrice[][];
	private ArrayList<Bateau> listeBateaux;

	// METHODES
	public Ocean() {
		// initialization matrice new Coordonee(j, i)
		matrice = new Case[TAILLE_MATRICE][TAILLE_MATRICE];
		for (int i = 0; i < TAILLE_MATRICE; ++i)
			for (int j = 0; j < TAILLE_MATRICE; ++j) {
				if(i == 0) {
					if(j == 0) matrice[i][j] = new Case(new Coordonee(j, i), DIRECTION.NO);
					else if(j == TAILLE_MATRICE - 1)
						matrice[i][j] = new Case(new Coordonee(j, i), DIRECTION.NE);
					else matrice[i][j] = new Case(new Coordonee(j, i), DIRECTION.N);
				} else if(i == TAILLE_MATRICE) {
					if(j == 0) matrice[i][j] = new Case(new Coordonee(j, i), DIRECTION.SO);
					else if(j == TAILLE_MATRICE - 1)
						matrice[i][j] = new Case(new Coordonee(j, i), DIRECTION.SE);
					else matrice[i][j] = new Case(new Coordonee(j, i), DIRECTION.S);
				} else if(j == 0) {
					matrice[i][j] = new Case(new Coordonee(j, i), DIRECTION.O);
				} else if(j == TAILLE_MATRICE) {
					matrice[i][j] = new Case(new Coordonee(j, i), DIRECTION.E);
				} else matrice[i][j] = new Case(new Coordonee(j, i));
			}

	}
	
	private Case caseAPosition(Coordonee position) {
		int x = position.x();
		int y = position.y();
		if(x < 0 || x >= TAILLE_MATRICE || y < 0 || y >= TAILLE_MATRICE)
			throw new IllegalArgumentException("Position invalide (hors matrice)");
		return matrice[y][x];
	}
	
	public void ajouterBateauSurPositionAleatoire(Bateau b) {
		ajouterBateau(b, Coordonee.aleatoire(TAILLE_MATRICE));
	}
	public void ajouterBateau(Bateau b, Coordonee position) {
		caseAPosition(position).ajouterBateau(b);
		listeBateaux.add(b);
	}

	public void pasDeSimulation() {
		// pasDeSimulationMatrice(); // PROBLEME
		pasDeSimulationArrayList();
	}
//	private void pasDeSimulationMatrice() {
//		// PROBLEME : RISQUE DE RECONSIDERATION DE BATEAU DEJA RENCONTRE.
//		for (int i = 0; i < TAILLE_MATRICE; ++i)
//			for (int j = 0; j < TAILLE_MATRICE; ++j)
//				if(!matrice[i][j].estVide()) {
//					Coordonee position = new Coordonee(j, i);
//					for(int k = 0; k < matrice[i][j].nombreDeBateaux(); ++k) {
//						Bateau b = matrice[i][j].bateau(k);
//						pasDeSimulationBateau(b, position);
//					}
//		}
//	}
	private void pasDeSimulationArrayList() {
		for (Bateau b : listeBateaux)
			pasDeSimulationBateau(b, b.position().coordonees());
	}
	private void pasDeSimulationBateau(Bateau b, Coordonee position) {
		pasDeSimulationBateauInfos(b, position);
		pasDeSimulationBateauDeplacement(b, position);
		pasDeSimulationBateauAction(b, position);
		retirerBateauDetruit(b, position);
	}
	private void pasDeSimulationBateauInfos(Bateau b, Coordonee position) {
		Coordonee dest = bateauLePlusProcheDepuis(position);
		Case cible = caseAPosition(dest);
		int distance = position.distance(dest);
		b.infoEntourage(cible, position.directionVers(dest), distance);
	}
	private void pasDeSimulationBateauDeplacement(Bateau b, Coordonee position) {
		Coordonee dest = position.coordoneeDansDirection(b.determinerDirection());
		caseAPosition(position).enleverBateau(b);
		Case cible = caseAPosition(dest);
		cible.ajouterBateau(b);
		b.position(cible);
		
	}
	private void pasDeSimulationBateauAction(Bateau b, Coordonee position) { b.agir(); }

	private void retirerBateauDetruit(Bateau b, Coordonee position) {
		if(b.vies() == 0) {
			b.position().enleverBateau(b);
			listeBateaux.remove(b);
		}
	}
	
	private Coordonee bateauLePlusProcheDepuis(Coordonee position) {
		int ip = position.y();
		int jp = position.x();
		int distanceMax = -1;
		int tmp;
		Coordonee plusProche = null;
		for(int i = ip - RAYON_ENTOURAGE; i < (ip + RAYON_ENTOURAGE); ++i)
			for(int j = jp - RAYON_ENTOURAGE; j < (jp + RAYON_ENTOURAGE); ++j)
				if(!matrice[i][j].estVide() && i != ip && j != jp) {
					Coordonee dest = new Coordonee(j, i);
					tmp = position.distance(dest);
					if(tmp > distanceMax) { 
						distanceMax = tmp;
						plusProche = dest;
					}
		}
		return plusProche;
	}



}
