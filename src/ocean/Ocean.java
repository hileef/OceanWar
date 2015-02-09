package ocean;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import element.CiblageRadar;
import element.Element;
import element.EtatElement;
import bateau.UsineStandard;
import affichage.Affichage;
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
	
	// TODO ADD DOCUMENTATION
	private Usine usine;
	
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
		this(new UsineStandard(), a);
	}
	/**
	 * Constructeur principal
	 * Se charge d'initialiser les attributs necessaires
	 * Lancera la production de l'usine en parametre, et stockera ses produits. (Voir doc Usine)
	 * @param u L'usine qui sera utilisee pour populer l'ocean des elements produits.
	 * @param a L'affichage qui sera utilise afin de representer a l'utilisateur l etat du jeu apres chaque tour.
	 */
	public Ocean(Usine u, Affichage a) {
		usine = u;
		affichage = a;
		
		usine.ajouterOcean(this);
		usine.ajouterObserver(affichage);
		
		reinitialiser();
	}
	
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

	private void reinitialiser() {
		affichage.reinitaliser();
		elements = new LinkedHashMap<Integer, Element>();
		aRetirer = new LinkedList<Integer>();
		Collection<Element> produits = usine.produire();
		for(Element e : produits)
			elements.put(e.id(), e);
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		assert(arg0 != null && arg1 != null);
		assert(arg0.getClass().isAssignableFrom(Element.class));
		assert(arg1.getClass().isAssignableFrom(EtatElement.class));
		
		if(((EtatElement)arg1) == EtatElement.DETRUIT)
			aRetirer.add(((Element)arg0).id());		
	}
	
	public Collection<Element> radar(Element a, int rayonMin, int rayonMax) {
		LinkedList<Element> liste = new LinkedList<Element>();
		
		if(a.ciblage() == CiblageRadar.AUCUN)
			return liste;
		
		boolean intrusion = true;
		int d, min = Integer.MAX_VALUE;
		Element minElement = elements.get(0);
			
		for (Element e : elements.values()) {
			if(e.id() != a.id()) {
				d = a.position().distance(e.position());
				if(d > rayonMax || d < rayonMin) { ; }
				else if(((a.ciblage() == CiblageRadar.BLESSE) ? 
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
		
		if(!liste.isEmpty() && Math.random() <= 0.2) 
			liste.removeFirst();

		if(intrusion)
			throw new IllegalAccessError("Acces non autorise."
					+ " La requete doit provenir d'un element appartenant a cet Ocean.");
		
		return liste;
	}
	

}
