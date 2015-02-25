package core;

public interface Displayable {

	public Integer id();	
	public String imgURL();
	
	public Point.Direction direction();
	public Point position();
	
	public boolean isDisplayable();
	
}
