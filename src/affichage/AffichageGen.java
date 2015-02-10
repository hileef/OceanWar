package affichage;

import java.util.Observable;

import ocean.Affichage;
import ocean.Element;
import ocean.Etat;

public abstract class AffichageGen implements Affichage {

	protected abstract void detruire(Element a) ;
	protected abstract void actualiser(Element a) ;
	
	public static void pause(double secondes) {
		try {
			Thread.sleep((int) (1000 * secondes));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		assert(arg0 != null && arg1 != null);
		assert(Element.class.isAssignableFrom(arg0.getClass()));
		assert(Etat.class.isAssignableFrom(arg1.getClass()));
		
		switch(((Etat)arg1)) {
		case POSITION :
			actualiser(((Element)arg0));
			break;
		case DETRUIT:
			detruire(((Element)arg0));
			break;

		}
			
		
	}
}
