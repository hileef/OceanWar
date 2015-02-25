package core;

public interface Element extends Displayable {
	
	public void update() ;
	
	public boolean isDestroyed();
	public void hit();
	public void repair();
	
}
