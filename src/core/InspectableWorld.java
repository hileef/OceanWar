package core;

public interface InspectableWorld {

	public void notifyDeath(Element e) ;
	
	public Integer[] getElementKeys() ;
	
	public Element getElementWithKey(Integer key) ;
}
