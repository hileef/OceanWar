package controle;

import affichage.Graphique;

public class Appli {

	public static void main(String[] args) {
		(new Ocean(new Graphique())).lancerTours();
//		(new Ocean()).lancerTours();
	}

}
