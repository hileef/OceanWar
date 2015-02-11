package affichage;

import java.util.HashMap;

import ocean.Coordonnee;
import ocean.Element;

public class Graphique extends AffichageGen {
	
	private HashMap<Integer, Coordonnee> destinations;

	public Graphique() { 
		ProfGraphique.ouvrir("Ocean War", super.tailleOcean() * 50, super.tailleOcean() * 50);
		reinitaliser();
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
	protected void actualiser(Element a) {
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
	protected void detruire(Element a) {
		ProfGraphique.retirerElement(a.id());
		destinations.remove(a.id());
		
	}

	@Override
	public boolean demanderSiRejouer() {
		char c;
		ProfGraphique.retirerTout();
		ProfGraphique.ajouterLibelle(0, "Desirez-vous relancer (oui / non) ? _ _ _", 50, 50);
		ProfGraphique.actualiser();
		while((c = ProfGraphique.lireCaractereNonBloquant()) != 'o' && c !='n');
		return (c == 'o');
	}

	@Override
	public void reinitaliser() {
		destinations = new HashMap<Integer, Coordonnee>();
		ProfGraphique.retirerTout();
		ProfGraphique.ajouterImage(0, "/images/ocean1.jpg", 0, 0);
		ProfGraphique.actualiser();
	}

}
