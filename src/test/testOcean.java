package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import modele.Coordonnee;
import modele.Ocean;

import org.junit.Test;

import element.Bateau;
import element.Cible;

public class testOcean {
	
	Ocean o = new Ocean();
	Cible b = new Cible();
	Coordonnee c = new Coordonnee(5,5);
	Cible b2 = new Cible();
	Coordonnee c2 = new Coordonnee(6,6);
	

	@Test
	public void testAjouterBateau() {
		o.ajouterBateau(b, c);
		assertTrue(o.estPresent(b));
		assertTrue(b.ocean() ==o);
		
	}

	@Test
	public void testAjouterBateauSurPositionAleatoire() {
		o.ajouterBateauSurPositionAleatoire(b);
		assertTrue(o.estPresent(b));
		assertTrue(b.ocean() ==o);
	}

	@Test
	public void testsupprimerBateauxDetruits() {
		o.ajouterBateau(b, c);
		for (int i =0; i < b.resistance(); ++i){
			b.toucher();
		}
		assertTrue(b.estDetruit());
		assertTrue(o.aEteSupprime(b));
		assertFalse(o.estPresent(b));
	}
	
	
	@Test
	public void testRadarBateau() {
		boolean bool = false;
		o.ajouterBateau(b, c);
		o.ajouterBateau(b2, c2);
		
		LinkedList<Bateau> cibles = o.radar(b);
			if(cibles.contains(b2)){
				bool = true;
			}
		assertTrue(bool);		
		
	}
		

}
