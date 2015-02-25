package core;

import java.util.Collection;

public interface Factory {

	public void setWorldAccess(InspectableWorld world);
	
	public Collection<Element> build();
	
}
