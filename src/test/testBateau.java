package test;
import static org.junit.Assert.*;
import modele.Coordonnee;

import org.junit.Test;

import element.Bateau;


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

		@Override
		public String imageURL() {
			// TODO Auto-generated method stub
			return null;
		}

	};

	@Test
	public void testconstructeurBateau() {
		assertEquals(b.vies(), 8);
	}

	@Test
	public void testPositionCoordonee() {
		Coordonnee c = new Coordonnee(5,6);
		b.position(c);
		assertEquals(b.position(),c);
	}

	
	@Test
	public void testToucher() {
		b.toucher();
		assertEquals(b.vies(),7);
	}

	@Test
	public void testReparer() {
		b.reparer();
		assertEquals(b.vies(),8);
	}

}
