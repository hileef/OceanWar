package affichage;

public class Graphique implements Affichage {

//	private int tailleMatrice;
//	private int idVaguesMin;
	
	public Graphique(int tailleMatrice) {
//		this.tailleMatrice = tailleMatrice;
		ProfGraphique.ouvrir("test", tailleMatrice * 50, tailleMatrice * 50);
//		vagues();
	}

	@Override
	public void actualiserPosition(Affichable a) {
		if(!ProfGraphique.placerElement(a.id(), a.position().x() * 50, a.position().y() * 50)) {
			ProfGraphique.ajouterImage(a.id(), "./src/images/" + a.imageURL(), a.position().x() * 50, a.position().y() * 50);
		} 
			
	}

	@Override
	public void actualiserAffichage() {
		ProfGraphique.actualiser();
		
	}
	
//	private void vagues() {
//		idVaguesMin = Simulation.idUnique();
//		for(int k = 1; k < tailleMatrice * tailleMatrice; ++k)
//			Simulation.idUnique();
//		for(int i = 0; i < tailleMatrice; ++i)
//			for(int j = 0; j < tailleMatrice; ++j) {
//				int k = i * tailleMatrice + j + idVaguesMin;
//					ProfGraphique.ajouterImage(k, "./src/images/Vague.gif", j, i);
//			}
//		for(int i = 0; i < tailleMatrice; ++i)
//			for(int j = 0; j < tailleMatrice; ++j) {
//				int k = i * tailleMatrice + j + idVaguesMin;
//				ProfGraphique.placerElement(k, j * 50, i * 50);
//			}
//
//	}
	
}
