package ships;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ships.engines.Boustrophedon;
import ships.engines.Pong;
import ships.engines.RandomFrenzy;
import ships.extensions.Cannon;
import ships.extensions.RepairDock;
import ships.radar.Radar;
import core.Element;
import core.Factory;
import core.InspectableWorld;
import core.Point;

public class Shipyard implements Factory {

	private static final int DEFAULT_SHIP_RESISTANCE = 5;
	private static final int DEFAULT_HOS_RADAR_RANGE = 0;
	private static final int DEFAULT_MIL_RADAR_RANGE = 4;
	private static final int DEFAULT_CANNON_RANGE = 2;
	
	private static final int DEFAULT_CIV_ORDER = 5;
	private static final int DEFAULT_MIL_ORDER = 1;
	private static final int DEFAULT_HOS_ORDER = 2;
	private static final int DEFAULT_ORDER_MULTIPLIER = 4;
	
	private static final Point.Direction DEFAULT_DIRECTION = Point.Direction.E;
	
	private InspectableWorld world;
	private static Integer uniqueIDcounter = 0;
	
	private static Integer uniqueId() {
		uniqueIDcounter = new Integer(uniqueIDcounter.intValue() + 1);
		return uniqueIDcounter;
	}
	
	private InspectableWorld world() {
		if(world == null)
			throw new IllegalStateException("World reference has not been passed.");
		else return world;
	}
	
	@Override
	public void setWorldAccess(InspectableWorld world) {
		this.world = world;
	}

	@Override
	public Collection<Element> build() {
		return order(DEFAULT_CIV_ORDER * DEFAULT_ORDER_MULTIPLIER,
				DEFAULT_MIL_ORDER * DEFAULT_ORDER_MULTIPLIER,
				DEFAULT_HOS_ORDER * DEFAULT_ORDER_MULTIPLIER);
	}
	
	private List<Element> order(int civilian, int military, int hospital) {
		List<Element> liste = new LinkedList<Element>();
		for(int i = 0; i < civilian; ++i) liste.add(buildCivilian());
		for(int i = 0; i < hospital; ++i) liste.add(buildHospital());
		for(int i = 0; i < military; ++i) liste.add(buildMilitary());
		return liste;
	}
	
	private Element buildSpecificShip(String name, String imgURL, int maxLife, Extension...parts) {
		return new Ship(uniqueId(), Point.random(), DEFAULT_DIRECTION, name, imgURL, maxLife, parts);
	}
	
	private Element buildCivilian() {
		return buildSpecificShip("P", "civil", DEFAULT_SHIP_RESISTANCE, new Boustrophedon(null));
	}
	
	private Element buildMilitary() {
		Inspector radar = new Radar(world(), DEFAULT_MIL_RADAR_RANGE);
		return buildSpecificShip("M", "militaire", DEFAULT_SHIP_RESISTANCE, radar, new RandomFrenzy(radar), new Cannon(radar, DEFAULT_CANNON_RANGE));
	}
	
	private Element buildHospital() {
		Inspector radar = new Radar(world(), DEFAULT_HOS_RADAR_RANGE);
		return buildSpecificShip("H", "hopital", DEFAULT_SHIP_RESISTANCE, new Pong(null), new RepairDock(radar));
	}

}
