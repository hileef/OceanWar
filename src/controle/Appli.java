package controle;

import affichage.ProfGraphique;

public class Appli {

	public static void main(String[] args) {
		
		Simulation s = new Simulation(500);
		s.lancer();
		
		testerAffichageGraphique();
		
	}
	
	private static void testerAffichageGraphique() {
		ProfGraphique.ouvrir("test", 750, 750);
		ProfGraphique.ajouterImage(1, "./src/images/Vague.gif", 50, 50);
		ProfGraphique.actualiser();
	}

}
