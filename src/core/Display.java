package core;

public interface Display {

	public void update(Displayable e) ;
	
	public void refresh(double seconds);
	
	public boolean askForReplay();
	
	public void reset();
	
}
