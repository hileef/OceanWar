package modele;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Observer;

import bateau.*;
import controle.Ocean;

public class UsineStandard implements Usine {
	
	private static Integer compteurIdUniques = new Integer(0);
	private static Integer idUnique() {
		compteurIdUniques = new Integer(compteurIdUniques.intValue() + 1);
		return compteurIdUniques;
	}
	
	private static final int nbCibles = 30;
	private static final int nbCombattants = 10;
	private static final int nbHopitaux = 5;
	
	private Collection<Observer> obs;
	private Ocean ocean;
	
	public UsineStandard() {
		this.obs = new LinkedList<Observer>();
		
	}

	@Override
	public Collection<Element> produire() {
		
		Collection<Element> produits = new LinkedList<Element>();
		

		for(int i = 0; i < nbCombattants; ++i) produits.add(new Combattant(idUnique(), ocean));
		for(int i = 0; i < nbHopitaux; ++i) produits.add(new Hopital(idUnique(), ocean));
		for(int i = 0; i < nbCibles; ++i) produits.add(new Cible(idUnique(), ocean));
		
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
