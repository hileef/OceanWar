package affichage;

import java.util.HashMap;

import modele.Coordonnee;
import controle.Simulation;

public class Graphique extends Affichage {
	
	private HashMap<Integer, Coordonnee> destinations;

	public Graphique(int tailleMatrice) {
		destinations = new HashMap<Integer, Coordonnee>();
		ProfGraphique.ouvrir("test", tailleMatrice * 50, tailleMatrice * 50);
		int idOcean = Simulation.idUnique();
		ProfGraphique.ajouterImage(idOcean,"./src/images/ocean1.jpg", 0, 0);
	}

	@Override
	public void actualiserPosition(Affichable a) {
		if(a.estDetruit()) {
			ProfGraphique.retirerElement(a.id());
			destinations.remove(Integer.parseInt("" + a.id()));
		} else {
			int x, y;
			if(destinations.containsKey(Integer.parseInt("" + a.id()))) {
				x = ProfGraphique.getX(a.id());
				y = ProfGraphique.getY(a.id());
			} else {
				x = a.position().facteur(50).x();
				y = a.position().facteur(50).y();
			}
			ProfGraphique.retirerElement(a.id());
			ProfGraphique.ajouterImage(a.id(), "./src/images/" + a.imageURL(), x, y);
			destinations.put(Integer.parseInt("" + a.id()), a.position().facteur(50));
		} 
	}

	@Override
	public void actualiserAffichage(double secondes) {
		for(int i = 0; i < 50; ++i) {
			for(Integer id : destinations.keySet()) {
				Coordonnee origine = new Coordonnee(ProfGraphique.getX(id), ProfGraphique.getY(id));
				Coordonnee differentiel = Coordonnee.differentielVers(origine.directionVers(destinations.get(id))).facteur(1);
				ProfGraphique.deplacerElement(id, differentiel.x(), differentiel.y());
			}
			ProfGraphique.actualiser();
			pause(secondes / 50);
		}
		
	}
	

	
}
