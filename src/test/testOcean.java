package test;
import static org.junit.Assert.*;
import modele.*;

import org.junit.Test;

import element.*;


public class testOcean {
	
	Ocean o = new Ocean();
	Bateau b = new Cible();
	Bateau d = new Cible();

	@Test
	public void testAjouterBateau() {
		Coordonee c = new Coordonee(5,5);
		o.ajouterBateau(b, c);
		System.out.println(b.hashCode());
		System.out.println(d.hashCode());

	}

	@Test
	public void testAjouterBateauSurPositionAleatoire() {
		fail("Pas encore implémenté");
	}

	@Test
	public void testPasDeSimulation() {
		fail("Pas encore implémenté");
	}

}
