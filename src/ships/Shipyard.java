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
import core.Point;

public class Shipyard implements Factory {

	private static final int DEFAULT_SHIP_RESISTANCE = 5;
	private static final int DEFAULT_RANGE_RADAR = 4;
	private static final int DEFAULT_RANGE_CANNON = 1;
	
	private static final int DEFAULT_CIV_ORDER = 10;
	private static final int DEFAULT_MIL_ORDER = 4;
	private static final int DEFAULT_HOS_ORDER = 6;
	private static final int DEFAULT_ORDER_MULTIPLIER = 2;
	
	private static final Point.Direction DEFAULT_DIRECTION = Point.Direction.E;
	
	private Integer worldKey;
	private static Integer uniqueIDcounter = 0;
	
	private static Integer uniqueId() {
		uniqueIDcounter = new Integer(uniqueIDcounter.intValue() + 1);
		return uniqueIDcounter;
	}
	
	@Override
	public void setWorldKey(Integer key) {
		this.worldKey = key;
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
		for(int i = 0; i < military; ++i) liste.add(buildMilitary());
		for(int i = 0; i < hospital; ++i) liste.add(buildHospital());
		return liste;
	}
	
	private Ship buildShipBones(String name, String imgURL, int maxLife, Extension...parts) {
		return new Ship(uniqueId(), Point.random(), DEFAULT_DIRECTION, name, imgURL, maxLife, parts);
	}
	
	private Element buildCivilian() {
		return buildShipBones("P", "civil", DEFAULT_SHIP_RESISTANCE, new Boustrophedon());
	}
	
	private Element buildMilitary() {
		return buildShipBones("M", "militaire", DEFAULT_SHIP_RESISTANCE, new Radar(worldKey, DEFAULT_RANGE_RADAR), new RandomFrenzy(), new Cannon(worldKey, DEFAULT_RANGE_CANNON));
	}
	
	private Element buildHospital() {
		return buildShipBones("H", "hopital", DEFAULT_SHIP_RESISTANCE, new Pong(), new RepairDock(worldKey));
	}

}
