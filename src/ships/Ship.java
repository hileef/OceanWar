package ships;

import java.util.LinkedList;
import java.util.List;

import core.Point;
import core.Point.Direction;

class Ship implements IShip {

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
		this.position = position;
		this.direction = direction;
		this.imgURL = imgURL;
		this.maxLife = maxLife;
		this.life = maxLife;
		this.name = name;
		for(Extension e : parts) 
			this.parts.add(e);
			
	}

	@Override
	public void setDisplacement(Point position, Point.Direction direction) {
		this.position = position;
		this.direction = direction;
	}
	
	@Override
	public void update() {
		for (Extension sp : parts)
			sp.update(this);
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

	@Override
	public boolean isDisplayable() {
		return isDestroyed();
	}

}
