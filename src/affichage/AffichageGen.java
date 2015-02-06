package affichage;

import java.util.Observable;

import modele.Element;
import modele.EtatElement;

public abstract class AffichageGen implements Affichage {

	protected abstract void detruire(Affichable a) ;
	protected abstract void actualiser(Affichable a) ;
	
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
		assert(arg0.getClass().isAssignableFrom(Element.class));
		assert(arg1.getClass().isAssignableFrom(EtatElement.class));
		
		switch(((EtatElement)arg1)) {
		case POSITION_CHANGEE :
			actualiser(((Element)arg0));
			break;
		case DETRUIT:
			detruire(((Element)arg0));
			break;

		}
			
		
	}
}
