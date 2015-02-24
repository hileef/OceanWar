package core;

public interface Element {

	public Integer id();
	
	public void update(World w) ;
	
	public Point.Direction direction();
	public Point position();
	
	public String imgURL();
	
	public boolean isDestroyed();
	public void hit();
	public void repair();
	
}
