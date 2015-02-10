package ocean;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import bateau.FabriqueBateau;
import affichage.Console;

/**
 * Classe de controle principale, se charge d'orchestrer le jeu.
 *
 */
public class Ocean implements Observer {
	
	/**
	 * Le nombre de pas de simulation a effectuer.
	 */
	private static final int NOMBRE_TOURS = 250;
	
	/**
	 * Le nombre de cases de chaque colonne et rangee de la matrice representee.
	 */
	public static final int TAILLE_MATRICE = 15;
	
	/**
	 * La frequence a laquelle chaque pas de simulation est effectuee (representee en Hz).
	 */
	private static final double frequenceTours = 4.5;
	
	/**
	 * Permet de choisir entre la representation graphique fluide ou par étapes.
	 * Sur des machines peu performantes, choisir true, sinon false pour une experience plus agreable.
	 */
	private static final boolean clignotant = false;
	
	/**
	 * La reference vers l'affichage qui sera utilise, est initalisee a la contruction de la classe.
	 */
	private Affichage affichage;
	
	/**
	 * La reference vers usine qui sera utilise a la construction de l'ocean et a la reinitaliser() pour poupler l'ocean.
	 */
	private Fabrique usine;
	
	/**
	 * La collection qui contiendra les elements de l'ocean. (Voir doc de l'interface Element)
	 * LinkedHasMap a ete choisie pour ses hautes performances pour les algorithmes utilises dans cette classe.
	 */
	private LinkedHashMap<Integer, Element> elements;
	
	/**
	 * Collection qui contiendra, a chaque tour, les id des elements a retirer de la collection qui contient tout les elements de l'ocean.
	 */
	private Queue<Integer> aRetirer;
	
	/**
	 * Constructeur secondaire
	 * Ce constructeur lancera le contructeur principal avec un affichage Console en parametre.
	 */
	public Ocean(){
		this(new Console());
	}
	/**
	 * Constructeur secondaire
	 * Ce constructeur lancera le contructeur principal avec l'affichage donne et une UsineStandard en parametre.
	 * @param a L'affichage qui sera utilise afin de representer a l'utilisateur l etat du jeu apres chaque tour.
	 */
	public Ocean(Affichage a) {
		this(new FabriqueBateau(), a);
	}
	/**
	 * Constructeur principal
	 * Se charge d'initialiser les attributs necessaires
	 * Lancera la production de l'usine en parametre, et stockera ses produits. (Voir doc Usine)
	 * @param u L'usine qui sera utilisee pour populer l'ocean des elements produits.
	 * @param a L'affichage qui sera utilise afin de representer a l'utilisateur l etat du jeu apres chaque tour.
	 */
	public Ocean(Fabrique u, Affichage a) {
		assert(u != null && a != null);
		
		usine = u;
		affichage = a;
		
		usine.ajouterOcean(this);
		usine.ajouterObserver(affichage);
		
		reinitialiser();
	}
	
	private void reinitialiser() {
		affichage.reinitaliser();
		elements = new LinkedHashMap<Integer, Element>();
		aRetirer = new LinkedList<Integer>();
		Collection<Element> produits = usine.produire();
		for(Element e : produits)
			elements.put(e.id(), e);
	}
	
	/**
	 * Cette fonction contient la boucle de jeu, dans laquelle, dans l'ordre :
	 * = On appelle la fonction jouerTour() de tous les elements contenus dans l'ocean
	 * = On retire les elements detruits de l'Ocean
	 * = On actualise l'affichage
	 * = On recommence, NOMBRE_TOURS fois.
	 * Finalement, on demande grace a l'affichage si l'utilisateur veut rejouer.
	 */
	public void lancerTours() {
		for(int i = 0; i < NOMBRE_TOURS; ++i) {
			long lag = System.currentTimeMillis();
			for(Element e : elements.values())
				e.jouerTour();
			while(!aRetirer.isEmpty()) 
				elements.remove(aRetirer.poll());
			double tempsRestant = (1 / frequenceTours) - ((double)(System.currentTimeMillis() - lag));
			tempsRestant = (tempsRestant > 0.0) ? tempsRestant : 0.0;
			affichage.actualiser((1 / frequenceTours), clignotant);
		}
		if(affichage.demanderSiRejouer()) {
			reinitialiser();
			lancerTours();
		}
	}

	/**
	 * Cette fonction permet a l'Ocean d'ajouter a la queue des elements a detruires
	 * les element qui envoient un message avec comme etat DETRUIT
	 * @param arg0 Un Observable qui DOIT etre assignable en objet implementant l'interface Element
	 * @Param arg1 Un Objet qui DOIT etre assignable en enum EtatElement
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		assert(arg0 != null && arg1 != null);
		assert(Element.class.isAssignableFrom(arg0.getClass()));
		assert(Etat.class.isAssignableFrom(arg1.getClass()));
		
		if(((Etat)arg1) == Etat.DETRUIT)
			aRetirer.add(((Element)arg0).id());		
	}
	
	/**
	 * L'inspection renvoie une collection d'Elements repondant au CiblageInspectionElement specifie par l'Element.
	 * La collection contient seulement les elements qui possede la meme position que l'element
	 *      correspondant au ciblage.
	 * = Pour le ciblage BLESSES, l'inspecteur renverra les elements dans la case de l'element
	 *      le plus blesse dans le rayon demande.
	 * = Pour le ciblage PROCHE, l'inspecteur renverra les elements dans la case de l'element
	 *      le plus proche dans le rayon demande.
	 * = Pour le ciblage AUCUN, l'inspecteur renverra une liste vide.
	 * @param a l'Element qui soumet la requete, il DOIT faire partie de cet Ocean et le radar fonctionnera a partir du ciblage et de la position de cet Element
	 * @param rayonMin le rayon minimum a partir duquel les elements doivent etre pris en compte
	 * @param rayonMax le rayon maximum a pertir duquel les elements doivent etre pris en compte
	 * @return Collection d'Elements situes a la meme position repondant au CiblageInspectionElement specifie.
	 */
	public Collection<Element> inspection(Element a, int rayonMin, int rayonMax) {
		assert(a != null && rayonMin >= 0);
		
		LinkedList<Element> liste = new LinkedList<Element>();
		
		if(a.ciblage() == Ciblage.AUCUN)
			return liste;
		
		boolean intrusion = true;
		int d, min = Integer.MAX_VALUE;
		Element minElement = elements.get(0);
			
		for (Element e : elements.values()) {
			if(e.id() != a.id()) {
				d = a.position().distance(e.position());
				if(d > rayonMax || d < rayonMin) { ; }
				else if(((a.ciblage() == Ciblage.BLESSE) ? 
						((e.viesMax() - e.vies() == 0) ? 
								Integer.MAX_VALUE: e.vies()): d) < min) {
					liste.clear();
					liste.add(e);
					min = d;
					minElement = e;
				} else if(d == min && e.position().equals(minElement.position())) 
					liste.add(e);
			} else
				intrusion = false;
		}
		
		if(!liste.isEmpty() && Math.random() <= 0.3) 
			liste.removeFirst();

		if(intrusion)
			throw new IllegalAccessError("Acces non autorise."
					+ " La requete doit provenir d'un element appartenant a cet Ocean.");
		
		return liste;
	}
	

}
