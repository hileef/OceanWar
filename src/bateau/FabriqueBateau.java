package bateau;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Observer;

import bateau.deplacement.Aleatoire;
import bateau.deplacement.Boustrophedon;
import bateau.deplacement.Pong;
import ocean.Ciblage;
import ocean.Element;
import ocean.Ocean;
import ocean.Fabrique;

public class FabriqueBateau implements Fabrique {
	
	private static Integer compteurIdUniques = new Integer(0);
	private static Integer idUnique() {
		compteurIdUniques = new Integer(compteurIdUniques.intValue() + 1);
		return compteurIdUniques;
	}
	
	private static final int nbCibles = 20;
	private static final int nbCombattants = 10;
	private static final int nbHopitaux = 6;
	
	private Collection<Observer> obs;
	private Ocean ocean;
	
	public FabriqueBateau() {
		this.obs = new LinkedList<Observer>();
		
	}

	@Override
	public Collection<Element> produire() {
		
		Collection<Element> produits = new LinkedList<Element>();
		
		
		for(int i = 0; i < nbCombattants / 2; ++i) 
			produits.add(new Combattant(idUnique(), ocean, new Pong(), Ciblage.BLESSE));
		for(int i = 0; i < nbCombattants / 2; ++i) 
			produits.add(new Combattant(idUnique(), ocean, new Aleatoire(), Ciblage.PROCHE));
		for(int i = 0; i < nbHopitaux / 2; ++i) 
			produits.add(new Hopital(idUnique(), ocean, new Boustrophedon()));
		for(int i = 0; i < nbHopitaux / 2; ++i) 
			produits.add(new Hopital(idUnique(), ocean, new Pong(), Ciblage.BLESSE));
		for(int i = 0; i < nbCibles / 2; ++i) 
			produits.add(new Cible(idUnique(), ocean, new Boustrophedon()));
		for(int i = 0; i < nbCibles / 2; ++i) 
			produits.add(new Cible(idUnique(), ocean, new Pong()));
		
		for(Element e : produits) {
			e.addObserver(ocean);
			for(Observer o : obs)
				e.addObserver(o);
		}
		
		return produits;
	}

	@Override
	public void ajouterOcean(Ocean ocean) {
		this.ocean = ocean;
	}

	@Override
	public void ajouterObserver(Observer o) {
		obs.add(o);
	}

}
