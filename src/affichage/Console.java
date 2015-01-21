package affichage;

import java.util.HashMap;

public class Console {

	private int compteActualisations;
	private HashMap<Integer, Affichable> liste;
	
	public Console() {
		liste = new HashMap<Integer, Affichable>();
		compteActualisations = 0;
	}
	
	public void ajouterElement(Affichable a) {
		liste.put(new Integer(a.id()), a);
	}
	
	public Affichable element(int id) {
		return liste.get(new Integer(id));
	}
	
	public void actualiser() {
		System.out.println("Actualisation #" + (++compteActualisations));
		for(Affichable a : liste.values()) System.out.println(a);
	}
	
	
}
