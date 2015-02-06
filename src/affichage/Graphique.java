package affichage;

import java.util.HashMap;

import modele.Coordonnee;
import controle.Ocean;

public class Graphique extends AffichageGen {
	
	private HashMap<Integer, Coordonnee> destinations;

	public Graphique() {
		destinations = new HashMap<Integer, Coordonnee>();
		ProfGraphique.ouvrir("Ocean War", Ocean.TAILLE_MATRICE * 50, Ocean.TAILLE_MATRICE * 50);
		ProfGraphique.ajouterImage(0, "/images/ocean1.jpg", 0, 0);
	}
	
	@Override
	public void actualiser(double secondes, boolean clignotant) {
		int steps = (clignotant) ? 1 : (secondes == 0.0) ? 5: 10;
		int facteur = 50 / steps;
		for(int i = 0; i < steps; ++i) {
			for(Integer id : destinations.keySet()) {
				Coordonnee origine = new Coordonnee(ProfGraphique.getX(id), ProfGraphique.getY(id));
				Coordonnee differentiel = Coordonnee.differentielVers(origine.directionVers(destinations.get(id))).facteur(facteur);
				ProfGraphique.deplacerElement(id, differentiel.x(), differentiel.y());
			}
			ProfGraphique.actualiser();
			pause(secondes / steps);
		}
	}

	@Override
	protected void actualiser(Affichable a) {
		int x, y;
		if(destinations.containsKey(Integer.parseInt("" + a.id()))) {
			x = ProfGraphique.getX(a.id());
			y = ProfGraphique.getY(a.id());
		} else {
			x = a.position().facteur(50).x();
			y = a.position().facteur(50).y();
		}
		ProfGraphique.retirerElement(a.id());
		ProfGraphique.ajouterImage(a.id(), "/images/" + a.imageURL(), x, y);
		destinations.put(Integer.parseInt("" + a.id()), a.position().facteur(50));
	}

	@Override
	protected void detruire(Affichable a) {
		ProfGraphique.retirerElement(a.id());
		destinations.remove(a.id());
		
	}

}
