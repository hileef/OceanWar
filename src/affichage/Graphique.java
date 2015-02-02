package affichage;

import java.util.HashMap;

import modele.Coordonee;
import controle.Simulation;

public class Graphique implements Affichage {
	
	private HashMap<Integer, Coordonee> origines;

	public Graphique(int tailleMatrice) {
		origines = new HashMap<Integer, Coordonee>();
		ProfGraphique.ouvrir("test", tailleMatrice * 50, tailleMatrice * 50);
		int idOcean = Simulation.idUnique();
		ProfGraphique.ajouterImage(idOcean,"./src/images/ocean1.jpg", 0, 0);
	}

	@Override
	public void actualiserPosition(Affichable a) {
		if(!a.estAffichable()) {
			ProfGraphique.retirerElement(a.id());
			origines.remove(Integer.parseInt("" + a.id()));
		} else {
			origines.put(key, value)
			ProfGraphique.retirerElement(a.id());
			ProfGraphique.ajouterImage(a.id(), "./src/images/" + a.imageURL(), a.position().x() * 50, a.position().y() * 50);
		} 
			
	}

	@Override
	public void actualiserAffichage() {
		ProfGraphique.actualiser();
		
	}
	

	
}
