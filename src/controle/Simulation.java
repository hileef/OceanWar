package controle;

import bateau.Cible;
import bateau.Combattant;
import bateau.Hopital;
import affichage.Console;
import modele.*;

public class Simulation {
	
	// Pour gerer les id uniques de tous les affichables de la simulation
	private static int compteurIdUniques = 0;
	public static int idUnique() {
		++compteurIdUniques;
		return compteurIdUniques;
	}
	
	// variable pour generation semi-aléatoire des bateaux
	private static final double facteurGerme = 0.50;
	private static final double germeNombreCombattant = 2;
	private static final double germeNombreHopital = 4;
	private static final double germeNombreCible = 7;
	
	private static final double vitesseDeSimulationEnHz = 8.0;
	
	// attributs
	private Ocean o;
	private int nbPas;
	private Console affConsole;
	
	public Simulation(int nbPas) {
		if(nbPas < 1) throw new IllegalArgumentException("Nombre de pas non valide.");
		this.affConsole = new Console();
		this.nbPas = nbPas;
		this.o = new Ocean();
		usinerBateaux();
	}
	
	public void lancer() {
		affConsole.ajouterElement(o);
		affConsole.actualiser();
		for(int i = 0; i < nbPas; ++i) {
			o.pasDeSimulation();
			affConsole.actualiser();
			pause(1.0 / vitesseDeSimulationEnHz);
		}
			
	}
	
	private int calculerNombreDepuisGerme(double germe) {
		return (int) Math.floor((1 + germe) * facteurGerme + (Math.random() * germe));
	}
	
	private void usinerBateaux() {
		int nbCombattant = calculerNombreDepuisGerme(germeNombreCombattant);
		int nbCible = calculerNombreDepuisGerme(germeNombreCible);
		int nbHopital = calculerNombreDepuisGerme(germeNombreHopital);
		for(int i = 0; i < nbCombattant; ++i) o.ajouterBateauSurPositionAleatoire(new Combattant());
		for(int i = 0; i < nbCible; ++i) o.ajouterBateauSurPositionAleatoire(new Cible());
		for(int i = 0; i < nbHopital; ++i) o.ajouterBateauSurPositionAleatoire(new Hopital());
	}
	
	private static void pause(double secondes) {
		try {
			Thread.sleep((int) (1000 * secondes));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
