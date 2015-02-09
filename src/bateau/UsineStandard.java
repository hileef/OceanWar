package bateau;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Observer;

import ocean.Ocean;
import ocean.Usine;
import deplacement.*;
import element.CiblageRadar;
import element.Element;

public class UsineStandard implements Usine {
	
	private static Integer compteurIdUniques = new Integer(0);
	private static Integer idUnique() {
		compteurIdUniques = new Integer(compteurIdUniques.intValue() + 1);
		return compteurIdUniques;
	}
	
	private static final int nbCibles = 30;
	private static final int nbCombattants = 12;
	private static final int nbHopitaux = 6;
	
	private Collection<Observer> obs;
	private Ocean ocean;
	
	public UsineStandard() {
		this.obs = new LinkedList<Observer>();
		
	}

	@Override
	public Collection<Element> produire() {
		
		Collection<Element> produits = new LinkedList<Element>();
		
		
		for(int i = 0; i < nbCombattants / 2; ++i) 
			produits.add(new Combattant(idUnique(), ocean, new Pong(), CiblageRadar.BLESSE));
		for(int i = 0; i < nbCombattants / 2; ++i) 
			produits.add(new Combattant(idUnique(), ocean, new Aleatoire(), CiblageRadar.PROCHE));
		for(int i = 0; i < nbHopitaux / 2; ++i) 
			produits.add(new Hopital(idUnique(), ocean, new Boustrophedon()));
		for(int i = 0; i < nbHopitaux / 2; ++i) 
			produits.add(new Hopital(idUnique(), ocean, new Pong(), CiblageRadar.BLESSE));
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
