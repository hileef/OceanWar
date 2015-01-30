package test;
import static org.junit.Assert.*;
import modele.Coordonee;
import modele.Direction;

import org.junit.Test;


public class testCoordonne {
	@Test
	public void testconstructCoordonee() {
		Coordonee c = new Coordonee(4,6);
		assertEquals(c.x(),4);
		assertEquals(c.y(),6);
	}
	

	@Test
	public void testAleatoire() {
		Coordonee c = Coordonee.aleatoire(10);
		assertTrue(c.x()<= 10);
		assertTrue(c.y()<= 10);
	}


	@Test
	public void testEqualsObject() {
		Coordonee c = new Coordonee(4,2);
		Coordonee d = new Coordonee(3,3);
		assertFalse(c.equals(d));
		assertTrue(c.equals(c));
		
	}

	@Test
	public void testDirectionVers() {
		Coordonee c = new Coordonee(4,6);
		Coordonee d = new Coordonee(4,8);
		assertEquals(c.directionVers(d), Direction.S);
		Coordonee c1 = new Coordonee(4,4);
		Coordonee d1 = new Coordonee(6,2);
		assertEquals(c1.directionVers(d1), Direction.NE);
		Coordonee e1 = new Coordonee(0,0);
		assertEquals(c1.directionVers(e1), Direction.NO);
		assertEquals(d1.directionVers(c1), Direction.SO);
		assertEquals(e1.directionVers(d1), Direction.SE);
		Coordonee e2 = new Coordonee(2,0);
		assertEquals(e1.directionVers(e2), Direction.E);
		assertEquals(e2.directionVers(e1), Direction.O);
		assertEquals(e2.directionVers(e2), null);
	}

	@Test
	public void testCoordoneeDansDirection() {
		Coordonee c = new Coordonee(5,5);
		assertEquals(c.coordoneeDansDirection(Direction.N).x(),5);
		assertEquals(c.coordoneeDansDirection(Direction.N).y(),4);
		
		assertEquals(c.coordoneeDansDirection(Direction.S).x(),5);
		assertEquals(c.coordoneeDansDirection(Direction.S).y(),6);
		
		assertEquals(c.coordoneeDansDirection(Direction.E).x(),6);
		assertEquals(c.coordoneeDansDirection(Direction.E).y(),5);
		
		assertEquals(c.coordoneeDansDirection(Direction.O).x(),4);
		assertEquals(c.coordoneeDansDirection(Direction.O).y(),5);
		
		assertEquals(c.coordoneeDansDirection(Direction.NE).x(),6);
		assertEquals(c.coordoneeDansDirection(Direction.NE).y(),4);
		
		assertEquals(c.coordoneeDansDirection(Direction.NO).x(),4);
		assertEquals(c.coordoneeDansDirection(Direction.NO).y(),4);
		
		assertEquals(c.coordoneeDansDirection(Direction.SE).x(),6);
		assertEquals(c.coordoneeDansDirection(Direction.SE).y(),6);
		
		assertEquals(c.coordoneeDansDirection(Direction.SO).x(),4);
		assertEquals(c.coordoneeDansDirection(Direction.SO).y(),6);
	}

	@Test
	public void testEstUneBordureIntInt() {
		Coordonee c = new Coordonee(5,5);
		assertTrue(c.estUneBordure(5, 9));
		assertFalse(c.estUneBordure(10, 10));
		assertTrue(c.estUneBordure(5, 5));
		assertTrue(c.estUneBordure(10, 5));
	}

	@Test
	public void testEstUneBordureIntIntIntInt() {
		Coordonee c = new Coordonee(5,5);
		assertTrue(c.estUneBordure(5, 9,5, 9));
		assertFalse(c.estUneBordure(10, 10, 10, 10));
		assertTrue(c.estUneBordure(5, 5, 5, 5));
		assertTrue(c.estUneBordure(10, 5, 10, 5));
	}

	@Test
	public void testInterpreterDirectionBordureIntInt() {
		Coordonee c = new Coordonee(1,1);
		assertEquals(c.interpreterDirectionBordure(1, 5), Direction.NO);
		Coordonee d = new Coordonee(5,5);
		assertEquals(d.interpreterDirectionBordure(1, 5), Direction.SE);
		Coordonee e = new Coordonee(5,1);
		assertEquals(e.interpreterDirectionBordure(1, 5), Direction.NE);
		Coordonee f = new Coordonee(1,5);
		assertEquals(f.interpreterDirectionBordure(1, 5), Direction.SO);
		Coordonee g = new Coordonee(3,1);
		assertEquals(g.interpreterDirectionBordure(1, 5), Direction.N);
		Coordonee h = new Coordonee(1,3);
		assertEquals(h.interpreterDirectionBordure(1, 5), Direction.O);
		Coordonee i = new Coordonee(3,5);
		assertEquals(i.interpreterDirectionBordure(1, 5), Direction.S);
		Coordonee j = new Coordonee(5,3);
		assertEquals(j.interpreterDirectionBordure(1, 5), Direction.E);
	}

	@Test
	public void testInterpreterDirectionBordureIntIntIntInt() {
		Coordonee c = new Coordonee(1,1);
		assertEquals(c.interpreterDirectionBordure(1, 5,1, 5), Direction.NO);
		Coordonee d = new Coordonee(5,5);
		assertEquals(d.interpreterDirectionBordure(1, 5,1, 5), Direction.SE);
		Coordonee e = new Coordonee(5,1);
		assertEquals(e.interpreterDirectionBordure(1, 5,1, 5), Direction.NE);
		Coordonee f = new Coordonee(1,5);
		assertEquals(f.interpreterDirectionBordure(1, 5,1, 5), Direction.SO);
		Coordonee g = new Coordonee(3,1);
		assertEquals(g.interpreterDirectionBordure(1, 5,1, 5), Direction.N);
		Coordonee h = new Coordonee(1,3);
		assertEquals(h.interpreterDirectionBordure(1, 5,1, 5), Direction.O);
		Coordonee i = new Coordonee(3,5);
		assertEquals(i.interpreterDirectionBordure(1, 5,1, 5), Direction.S);
		Coordonee j = new Coordonee(5,3);
		assertEquals(j.interpreterDirectionBordure(1, 5,1, 5), Direction.E);
	}

	@Test
	public void testDistance() {
		Coordonee c = new Coordonee(4,2);
		Coordonee d = new Coordonee(3,3);
		assertEquals(c.distance(d),1);
		assertEquals(d.distance(c),1);
		assertEquals(d.distance(d),0);
		Coordonee c1 = new Coordonee(2,5);
		Coordonee d1 = new Coordonee(5,2);
		assertEquals(d1.distance(c1),3);
		
		
		
	}

	@Test
	public void testDistanceX() {
		Coordonee c = new Coordonee(5,5);
		Coordonee d = new Coordonee(9,5);
		assertEquals(c.distanceX(d),4);
		assertEquals(d.distanceX(c),4);
	}

	@Test
	public void testDistanceY() {
		Coordonee c = new Coordonee(5,5);
		Coordonee d = new Coordonee(5,9);
		assertEquals(c.distanceY(d),4);
		assertEquals(d.distanceY(c),4);
	}

	@Test
	public void testDifferenceX() {
		Coordonee c = new Coordonee(5,5);
		Coordonee d = new Coordonee(9,5);
		assertEquals(c.differenceX(d),4);
		assertEquals(d.differenceX(c),-4);
	}

	@Test
	public void testDifferenceY() {
		Coordonee c = new Coordonee(5,5);
		Coordonee d = new Coordonee(5,9);
		assertEquals(c.differenceY(d),4);
		assertEquals(d.differenceY(c),-4);
	}

}
