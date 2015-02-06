package controle;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import affichage.Affichage;
import affichage.Console;
import modele.Element;
import modele.EtatElement;
import modele.Usine;
import modele.UsineStandard;

public class Ocean implements Observer {
	
	private static final int NOMBRE_TOURS = 200;
	public static final int TAILLE_MATRICE = 15;
	
	private static final double frequenceTours = 6.0;
	
	private static boolean clignotant = false;
	
	private Affichage affichage;
	private LinkedHashMap<Integer, Element> elements;
	private Queue<Integer> aRetirer;
	
	public Ocean(){
		this(new Console());
	}
	public Ocean(Affichage a) {
		this(new UsineStandard(), a);
	}
	public Ocean(Usine u, Affichage a) {
		affichage = a;
		elements = new LinkedHashMap<Integer, Element>();
		aRetirer = new LinkedList<Integer>();
		
		u.ajouterOcean(this);
		u.ajouterObserver(affichage);
		Collection<Element> produits = u.produire();
		for(Element e : produits)
			elements.put(e.id(), e);
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
		boolean intrusion = true;
		int d, rmin = rayonMax + 1;

		for (Element e : elements.values()) {
			if(e.id() != a.id()) {
				d = a.position().distance(e.position());
				if(d > rayonMax || d < rayonMin) { ; }
				else if(d < rmin) {
					liste.clear();
					liste.add(e);
					rmin = d;
				} else if(d == rmin) 
					liste.add(e);
			} else
				intrusion = false;
		}

		if(intrusion)
			throw new IllegalAccessError("Acces non autorise."
					+ " La requete doit provenir d'un element appartenant a cet Ocean.");
		
		return liste;
	}
	

}
