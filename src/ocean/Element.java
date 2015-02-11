package ocean;

import java.util.Observer;

// TODO DOC

/**
 * Cette interface represente toute chose que l'ocean peut contenir.
 * ATTENTION, il est necessaire que les classes concretes qui implementeront
 * cette interface heritent de l'Objet Standard Observable,
 * afin que l'Ocean puisse fonctionner correctement.
 * 
 */
public interface Element {
	
	/**
	 * Cette fonction sera appelle regulierement par l'Ocean.
	 * L'element pourrait par exemple se deplacer et tirer sur d'autres elements.
	 */
	public void jouerTour();
	
	/**
	 * Cette fonction requiert le ciblage radar representant la cible principale de l'element.
	 * (Voir doc CiblageRadar)
	 * @return Enum CiblageRadar representant la cible principale de l'element. 
	 */
	public Ciblage ciblage();

	/**
	 * Cette fonction, quad elle sera appelle,
	 * signifie que cet element vient d'etre touche par le tir d'un autre.
	 * Logiquement l'element devrait perdre une ou plusierus vies.
	 */
	public void toucher();
	
	/**
	 * Cette fonction, quad elle sera appelle,
	 * signifie que cet element vient d'etre repare par un autre.
	 * Logiquement l'element devrait regagner toutes ses vies.
	 */
	public void reparer();
	
	/**
	 * Cette fonction sert a rendre des comptes sur les vies restantes de l'Element.
	 * @return un entier representant le nombre de vies restantes de l'element.
	 */
	public int vies();
	
	/**
	 * Cette fonction sert a informer sur les vies maximum qu'un Element possede.
	 * @return un entier representant le nombres de vies maximum de l'Element.
	 */
	public int viesMax();
	
	
	public void addObserver(Observer o);
	
	public String toString();
	public String imageURL();
	
	public Integer id();
	public Coordonnee position();
	public Direction direction();
	
	
}
