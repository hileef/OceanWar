package test;
import static org.junit.Assert.*;

import modele.Coordonee;

import org.junit.Test;

import bateau.Bateau;


public class testBateau {
	Bateau b = new Bateau() {

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void jouerPas() {
			// TODO Auto-generated method stub
			
		}

	};

	@Test
	public void testconstructeurBateau() {
		assertEquals(b.vies(), 5);
	}

	@Test
	public void testPositionCoordonee() {
		Coordonee c = new Coordonee(5,6);
		b.position(c);
		assertEquals(b.position(),c);
	}

	
	@Test
	public void testToucher() {
		b.toucher();
		assertEquals(b.vies(),4);
	}

	@Test
	public void testReparer() {
		b.reparer();
		assertEquals(b.vies(),5);
	}

}
