package test;
import static org.junit.Assert.*;
import modele.Coordonnee;
import modele.Direction;

import org.junit.Test;


public class testCoordonne {
	@Test
	public void testconstructCoordonee() {
		Coordonnee c = new Coordonnee(4,6);
		assertEquals(c.x(),4);
		assertEquals(c.y(),6);
	}
	

	@Test
	public void testAleatoire() {
		Coordonnee c = Coordonnee.aleatoire(10);
		assertTrue(c.x()<= 10);
		assertTrue(c.y()<= 10);
	}


	@Test
	public void testEqualsObject() {
		Coordonnee c = new Coordonnee(4,2);
		Coordonnee d = new Coordonnee(3,3);
		assertFalse(c.equals(d));
		assertTrue(c.equals(c));
		
	}

	@Test
	public void testDirectionVers() {
		Coordonnee c = new Coordonnee(4,6);
		Coordonnee d = new Coordonnee(4,8);
		assertEquals(c.directionVers(d), Direction.S);
		Coordonnee c1 = new Coordonnee(4,4);
		Coordonnee d1 = new Coordonnee(6,2);
		assertEquals(c1.directionVers(d1), Direction.NE);
		Coordonnee e1 = new Coordonnee(0,0);
		assertEquals(c1.directionVers(e1), Direction.NO);
		assertEquals(d1.directionVers(c1), Direction.SO);
		assertEquals(e1.directionVers(d1), Direction.SE);
		Coordonnee e2 = new Coordonnee(2,0);
		assertEquals(e1.directionVers(e2), Direction.E);
		assertEquals(e2.directionVers(e1), Direction.O);
		assertEquals(e2.directionVers(e2), null);
	}

	@Test
	public void testcoordonneeDansDirection() {
		Coordonnee c = new Coordonnee(5,5);
		assertEquals(c.coordonneeDansDirection(Direction.N).x(),5);
		assertEquals(c.coordonneeDansDirection(Direction.N).y(),4);
		
		assertEquals(c.coordonneeDansDirection(Direction.S).x(),5);
		assertEquals(c.coordonneeDansDirection(Direction.S).y(),6);
		
		assertEquals(c.coordonneeDansDirection(Direction.E).x(),6);
		assertEquals(c.coordonneeDansDirection(Direction.E).y(),5);
		
		assertEquals(c.coordonneeDansDirection(Direction.O).x(),4);
		assertEquals(c.coordonneeDansDirection(Direction.O).y(),5);
		
		assertEquals(c.coordonneeDansDirection(Direction.NE).x(),6);
		assertEquals(c.coordonneeDansDirection(Direction.NE).y(),4);
		
		assertEquals(c.coordonneeDansDirection(Direction.NO).x(),4);
		assertEquals(c.coordonneeDansDirection(Direction.NO).y(),4);
		
		assertEquals(c.coordonneeDansDirection(Direction.SE).x(),6);
		assertEquals(c.coordonneeDansDirection(Direction.SE).y(),6);
		
		assertEquals(c.coordonneeDansDirection(Direction.SO).x(),4);
		assertEquals(c.coordonneeDansDirection(Direction.SO).y(),6);
	}

	@Test
	public void testEstUneBordureIntInt() {
		Coordonnee c = new Coordonnee(5,5);
		assertTrue(c.estUneBordure(5, 9));
		assertFalse(c.estUneBordure(10, 10));
		assertTrue(c.estUneBordure(5, 5));
		assertTrue(c.estUneBordure(10, 5));
	}

	@Test
	public void testEstUneBordureIntIntIntInt() {
		Coordonnee c = new Coordonnee(5,5);
		assertTrue(c.estUneBordure(5, 9,5, 9));
		assertFalse(c.estUneBordure(10, 10, 10, 10));
		assertTrue(c.estUneBordure(5, 5, 5, 5));
		assertTrue(c.estUneBordure(10, 5, 10, 5));
	}

	@Test
	public void testInterpreterDirectionBordureIntInt() {
		Coordonnee c = new Coordonnee(1,1);
		assertEquals(c.interpreterDirectionBordure(1, 5), Direction.NO);
		Coordonnee d = new Coordonnee(5,5);
		assertEquals(d.interpreterDirectionBordure(1, 5), Direction.SE);
		Coordonnee e = new Coordonnee(5,1);
		assertEquals(e.interpreterDirectionBordure(1, 5), Direction.NE);
		Coordonnee f = new Coordonnee(1,5);
		assertEquals(f.interpreterDirectionBordure(1, 5), Direction.SO);
		Coordonnee g = new Coordonnee(3,1);
		assertEquals(g.interpreterDirectionBordure(1, 5), Direction.N);
		Coordonnee h = new Coordonnee(1,3);
		assertEquals(h.interpreterDirectionBordure(1, 5), Direction.O);
		Coordonnee i = new Coordonnee(3,5);
		assertEquals(i.interpreterDirectionBordure(1, 5), Direction.S);
		Coordonnee j = new Coordonnee(5,3);
		assertEquals(j.interpreterDirectionBordure(1, 5), Direction.E);
	}

	@Test
	public void testInterpreterDirectionBordureIntIntIntInt() {
		Coordonnee c = new Coordonnee(1,1);
		assertEquals(c.interpreterDirectionBordure(1, 5,1, 5), Direction.NO);
		Coordonnee d = new Coordonnee(5,5);
		assertEquals(d.interpreterDirectionBordure(1, 5,1, 5), Direction.SE);
		Coordonnee e = new Coordonnee(5,1);
		assertEquals(e.interpreterDirectionBordure(1, 5,1, 5), Direction.NE);
		Coordonnee f = new Coordonnee(1,5);
		assertEquals(f.interpreterDirectionBordure(1, 5,1, 5), Direction.SO);
		Coordonnee g = new Coordonnee(3,1);
		assertEquals(g.interpreterDirectionBordure(1, 5,1, 5), Direction.N);
		Coordonnee h = new Coordonnee(1,3);
		assertEquals(h.interpreterDirectionBordure(1, 5,1, 5), Direction.O);
		Coordonnee i = new Coordonnee(3,5);
		assertEquals(i.interpreterDirectionBordure(1, 5,1, 5), Direction.S);
		Coordonnee j = new Coordonnee(5,3);
		assertEquals(j.interpreterDirectionBordure(1, 5,1, 5), Direction.E);
	}

	@Test
	public void testDistance() {
		Coordonnee c = new Coordonnee(4,2);
		Coordonnee d = new Coordonnee(3,3);
		assertEquals(c.distance(d),1);
		assertEquals(d.distance(c),1);
		assertEquals(d.distance(d),0);
		Coordonnee c1 = new Coordonnee(2,5);
		Coordonnee d1 = new Coordonnee(5,2);
		assertEquals(d1.distance(c1),3);
		
		
		
	}

	@Test
	public void testDistanceX() {
		Coordonnee c = new Coordonnee(5,5);
		Coordonnee d = new Coordonnee(9,5);
		assertEquals(c.distanceX(d),4);
		assertEquals(d.distanceX(c),4);
	}

	@Test
	public void testDistanceY() {
		Coordonnee c = new Coordonnee(5,5);
		Coordonnee d = new Coordonnee(5,9);
		assertEquals(c.distanceY(d),4);
		assertEquals(d.distanceY(c),4);
	}

	@Test
	public void testDifferenceX() {
		Coordonnee c = new Coordonnee(5,5);
		Coordonnee d = new Coordonnee(9,5);
		assertEquals(c.differenceX(d),4);
		assertEquals(d.differenceX(c),-4);
	}

	@Test
	public void testDifferenceY() {
		Coordonnee c = new Coordonnee(5,5);
		Coordonnee d = new Coordonnee(5,9);
		assertEquals(c.differenceY(d),4);
		assertEquals(d.differenceY(c),-4);
	}

}
