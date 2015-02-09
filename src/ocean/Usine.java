package ocean;

import java.util.Collection;
import java.util.Observer;

import element.Element;

public interface Usine {

	/**
	 * Cette fonction produit des elements comme elle l'entend, tant qu'ils sont conforme
	 * a l'interface Element.
	 * @return Une collection d'objet implementant l'interface Element.
	 */
	public Collection<Element> produire();

	/**
	 * Cette fonction permet de donner aux elements produits une reference a l'Ocean
	 * afin qu'il puisse utiliser le radar.
	 * @param ocean l'ocean qui contiendra les elements profuits par l'usine.
	 */
	public void ajouterOcean(Ocean ocean);

	/**
	 * Cette fonciton permet d'ajouter aux elements produits les Observer necessaires
	 * pour la mise a jour de l'Ocean et de l'affichage.
	 * (Voir interface Element)
	 * @param o Observer a ajouter aux elements produits.
	 */
	public void ajouterObserver(Observer o);

}
