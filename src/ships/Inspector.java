package ships;

import core.Element;

public interface Inspector extends Extension {

	public int nbOfElementsAtClosestPosition() ;
	
	public Element elementsAtClosestPosition(int i) ;
	
	public int nbOfElementsAtSamePosition() ;
	
	public Element elementAtSamePosition(int i) ;

	void notifyDeath(Element e);
	
}
