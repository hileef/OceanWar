package core;

import java.util.Collection;

public interface Factory {

	public void setWorldKey(Integer i);
	
	public Collection<Element> build();
	
}
