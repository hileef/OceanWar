package appli;

import bateau.FabriqueBateau;
import ocean.Ocean;
import affichage.Console;
import affichage.Graphique;

public class Appli {

	/**
	 * Fonction d'amorce.
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		(new Ocean(new FabriqueBateau(), new Graphique(false))).lancerTours();	// Affichage graphique fluide
//		(new Ocean(new FabriqueBateau(), new Graphique())).lancerTours();		// Affichage graphique clignotant
//		(new Ocean(new FabriqueBateau(), new Console())).lancerTours();			// Affichage console
		System.exit(0);
	}

}
