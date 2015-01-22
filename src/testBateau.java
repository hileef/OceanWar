import static org.junit.Assert.*;

import java.util.LinkedList;

import modele.Bateau;
import modele.Coordonee;
import modele.DIRECTION;

import org.junit.Test;


public class testBateau {
	Bateau b = new Bateau() {
		
		@Override
		public String toString() {
			// TODO Stub de la méthode généré automatiquement
			return null;
		}
		
		@Override
		public void infosRadar(LinkedList<Bateau> listeMemeEndroit,
				LinkedList<Bateau> listeTrieeEntourage) {
			// TODO Stub de la méthode généré automatiquement
			
		}
		
		@Override
		public DIRECTION determinerDirection() {
			// TODO Stub de la méthode généré automatiquement
			return null;
		}
		
		@Override
		public void agir() {
			// TODO Stub de la méthode généré automatiquement
			
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
