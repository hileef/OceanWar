package display;

import core.Displayable;

public class Graphical extends ADisplay {

	/**
	 * Constructeur d'affichage Graphique
	 * @param size La taille de l'Ocean
	 */
	public Graphical(int size) {
		super(size);
		ProfGraphique.ouvrir("Ocean War",  worldSize() * imageSize(),  worldSize() * imageSize());
		reset();
	}
	
	/**
	 * Mets a jour un affichable sur l'affichage :
	 * le supprime, change sa position et/ou son image selon son etat
	 */
	@Override
	public void update(Displayable e) {
		ProfGraphique.retirerElement(e.id());
		if(e.isDisplayable()) {
			int x, y;
			x = e.position().multiply(imageSize()).x();
			y = e.position().multiply(imageSize()).y();
			ProfGraphique.ajouterImage(e.id(), "/images/" + e.imgURL(), x, y);
		}
	}
	
	/**
	 * Actualiser l'affichage : rendre les changements demandés visibles
	 * @param seconds Le temps a attendre en secondes apres l'actualisation
	 */
	@Override
	public void refresh(double seconds) {
		ProfGraphique.actualiser();
		pause(seconds);
	}

	/**
	 * Reinitialisation : supprime tout les affichables et
	 * remet l'affichage graphique a son etat inital.
	 */
	@Override
	public void reset() {
		ProfGraphique.retirerTout();
		ProfGraphique.actualiser();
		ProfGraphique.ajouterImage(0, "/images/ocean.png", 0, 0);
		ProfGraphique.actualiser();
	}
	
	/**
	 * Demande à l'utilisateur s'il souhaite rejouer.
	 * @return Renvoie la decision de l'utilisateur
	 */
	@Override
	public boolean askForReplay() {
		char c;
		ProfGraphique.retirerTout();
		ProfGraphique.ajouterLibelle(0, "Desirez-vous relancer (oui / non) ? _ _ _", 50, 50);
		ProfGraphique.actualiser();
		while((c = ProfGraphique.lireCaractereNonBloquant()) != 'o' && c !='n');
		return (c == 'o');
	}

}
