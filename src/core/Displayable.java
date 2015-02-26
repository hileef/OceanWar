package core;

public interface Displayable {

	public Integer id();	
	public String imgURL();
	
	public Point.Dir dir();
	public Point position();
	
	public boolean isDisplayable();
	
}
