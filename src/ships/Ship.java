package ships;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import core.Element;
import core.Point;
import core.Point.Direction;
import core.World;

public class Ship implements Element {

	private List<Element> neighbors;
	private List<Extension> parts;
	private Integer id;
	private Point position;
	private Point.Direction direction;
	private String imgURL;
	private String name;
	private int maxLife;
	private int life;

	public Ship(Integer id, Point position, Point.Direction direction, String name, String imgURL, int maxLife, Extension...parts) {
		this.id = id;
		this.parts = new LinkedList<Extension>();
		this.neighbors = new ArrayList<Element>();
		this.position = position;
		this.direction = direction;
		this.imgURL = imgURL;
		this.maxLife = maxLife;
		this.life = maxLife;
		this.name = name;
		for(Extension e : parts) 
			this.parts.add(e);
			
	}
	
	private void authorize(Extension e) {
		if(!this.parts.contains(e))
			throw new IllegalAccessError("Unathorized Access.");
	}

	public void setNeighbors(Extension e, Collection<Element> env) {
		authorize(e);
		this.neighbors.clear();
		this.neighbors.addAll(env);
	}

	public void setDisplacement(Extension e, Point position, Point.Direction direction) {
		authorize(e);
		this.position = position;
		this.direction = direction;
	}

	public int neighbors() {
		return neighbors.size();
	}

	public Element neighbor(int i) {
		return neighbors.get(i);
	}
	
	@Override
	public void update(World w) {
		for (Extension sp : parts)
			sp.update(w, this);
	}

	@Override
	public String imgURL() {
		String ddir;
		switch (direction) {
		case E:
		case NE:
		case SE:
			ddir = "d";
			break;
		default:
			ddir = "";
		}
		return imgURL + ddir + ".png";
	}

	@Override
	public Integer id() {
		return id;
	}

	@Override
	public Point position() {
		return position;
	}

	@Override
	public boolean isDestroyed() {
		return (life == 0);
	}

	@Override
	public void hit() {
		if (life > 0)
			life--;
	}

	@Override
	public void repair() {
		if (life > 0)
			life = maxLife;
	}

	@Override
	public Direction direction() {
		return direction;
	}

	@Override
	public String toString() {
		return name;
	}

}
