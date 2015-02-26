package tests.core;

import static org.junit.Assert.*;

import org.junit.Test;

import core.CoreFactory;
import core.Point;
import core.Point.Dir;

public class PointTest {
	
	private Point p = new Point(1, 2);
	
	@Test
	public void testStandardFunctionality() {
		assertEquals(p.x(), 1);
		assertEquals(p.y(), 2);
		assertTrue(p.toString().equals("(1;2)"));
		assertTrue(p.equals(new Point(1, 2)));
	}
	
	@Test
	public void testVectorFunctionality() {
		assertEquals(p.multiply(3), new Point(3, 6));
		assertEquals(p.multiply(0), new Point(0, 0));
		assertEquals(p.distanceTo(new Point(1, 2)), 0);
		assertEquals(p.distanceTo(new Point(1, 0)), 2);
		assertEquals(p.distanceTo(new Point(-1, -1)), 3);
		assertEquals(p.distanceTo(new Point(1, 1)), 1);
	}
	
	@Test
	public void testDirectionFunctionality() {
		assertEquals(p.directionTo(new Point(1, 3)), Dir.S);
		assertEquals(p.directionTo(new Point(1, 1)), Dir.N);
		assertEquals(p.directionTo(new Point(2, 2)), Dir.E);
		assertEquals(p.directionTo(new Point(0, 2)), Dir.W);
		assertEquals(p.directionTo(new Point(2, 3)), Dir.SE);
		assertEquals(p.directionTo(new Point(0, 3)), Dir.SW);
		assertEquals(p.directionTo(new Point(2, 1)), Dir.NE);
		assertEquals(p.directionTo(new Point(0, 1)), Dir.NW);
		assertEquals(Point.differentialTowards(Dir.N), (new Point(0, -1)));
		assertEquals(Point.differentialTowards(Dir.S), (new Point(0, 1)));
		assertEquals(Point.differentialTowards(Dir.E), (new Point(1, 0)));
		assertEquals(Point.differentialTowards(Dir.W), (new Point(-1, 0)));
		assertEquals(Point.differentialTowards(Dir.NE), (new Point(1, -1)));
		assertEquals(Point.differentialTowards(Dir.NW), (new Point(-1, -1)));
		assertEquals(Point.differentialTowards(Dir.SE), (new Point(1, 1)));
		assertEquals(Point.differentialTowards(Dir.SW), (new Point(-1, 1)));
		assertEquals(Point.inverseDirection(Dir.N), Dir.S);
		assertEquals(Point.inverseDirection(Dir.S), Dir.N);
		assertEquals(Point.inverseDirection(Dir.E), Dir.W);
		assertEquals(Point.inverseDirection(Dir.W), Dir.E);
		assertEquals(Point.inverseDirection(Dir.SE), Dir.NW);
		assertEquals(Point.inverseDirection(Dir.SW), Dir.NE);
		assertEquals(Point.inverseDirection(Dir.NE), Dir.SW);
		assertEquals(Point.inverseDirection(Dir.NW), Dir.SE);
	}
	
	@Test
	public void testBorderFunctionality() {
		int m = CoreFactory.SIZE - 1;
		Point[] bords = {
				new Point(0, 0), new Point(0, m),
				new Point(m, 0), new Point(m, m),
				new Point(0, 1), new Point(1, 0),
				new Point(1, m), new Point(m, 1)
		};
		
		assertFalse(p.isAtBorder());
		for(Point p : bords)
			assertTrue(p.isAtBorder());
		
		assertEquals(bords[0].borderHeading(), Dir.NW);
		assertEquals(bords[1].borderHeading(), Dir.SW);
		assertEquals(bords[2].borderHeading(), Dir.NE);
		assertEquals(bords[3].borderHeading(), Dir.SE);
		assertEquals(bords[4].borderHeading(), Dir.W);
		assertEquals(bords[5].borderHeading(), Dir.N);
		assertEquals(bords[6].borderHeading(), Dir.S);
		assertEquals(bords[7].borderHeading(), Dir.E);
		
	}

}
