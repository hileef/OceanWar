package modele;

public class Ocean {

	// CONSTANTES
	private static final int TAILLE_MATRICE = 10;
	private static final int RAYON_ENTOURAGE = 3;

	// ATTRIBUTS
	private Case matrice[][];

	// METHODES
	public Ocean() {
		// initialization matrice
		matrice = new Case[TAILLE_MATRICE][TAILLE_MATRICE];
		for (int i = 0; i < TAILLE_MATRICE; ++i)
			for (int j = 0; j < TAILLE_MATRICE; ++j) {
				if (i == 0 || i == TAILLE_MATRICE || j == 0
						|| j == TAILLE_MATRICE)
					matrice[i][j] = new Case(true);
				else
					matrice[i][j] = new Case(false);
			}

	}

	public void pasDeSimulation() {

	}

	private void deplacerBateau() {

	}

	private Case caseDansLaDirectionDepuis(Case position, DIRECTION direction) {
		return null;
	}

	private Case caseDuBateauLePlusProche(Case position) {
		return null;
	}

}
