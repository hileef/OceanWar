package affichage;

public abstract class Affichage {
	
	public abstract void actualiserPosition(Affichable a) ;
	
	public abstract void actualiserAffichage(double secondes) ;
	
	protected void pause(double secondes) {
		try {
			Thread.sleep((int) (1000 * secondes));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
