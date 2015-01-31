package test;

import static org.junit.Assert.*;
import modele.Coordonee;
import modele.Direction;
import modele.Ocean;

import org.junit.Test;

import element.Cible;

public class testCible {

	Ocean o = new Ocean();
	Cible b = new Cible();
	Coordonee c = new Coordonee(5,5);
	
	
	@Test
	public void testDeterminerDirection() {
		o.ajouterBateau(b, c);
		assertTrue(b.determinerDirection() == Direction.E);
		
	}

}
