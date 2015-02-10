package affichage;

import java.util.Scanner;

import ocean.Element;
import ocean.Ocean;

public class Console extends AffichageGen {

	private String[][] matrice;
	private Scanner sc;

	public Console() {
		sc = new Scanner(System.in);
		matrice = new String[Ocean.TAILLE_MATRICE][Ocean.TAILLE_MATRICE];
		viderMatrice();
	}
	
	@Override
	public void actualiser(Element a) {
		int x = a.position().x();
		int y = a.position().y();
		String cmp = matrice[x][y];
		int c;
		if(cmp.equals("  ")) {
			matrice[x][y] = a.toString() + " ";
		} else if((c = (cmp.charAt(0) - '0')) > 0 && c <= 9) {
			matrice[x][y] = ((++c) + "x");
		} else {
			matrice[x][y] = "2x";
		}
	}

	@Override
	public void actualiser(double secondes, boolean clignotant) {
		System.out.println(matriceToString());
		viderMatrice();
		pause(secondes);
	}

	private String matriceToString() {
		String out = "      ";
		for(int i = 0; i < matrice.length; ++i) {
			out = out.concat(" ");
			out = out.concat((i > 9) ? "" : "0");
			out = out.concat(i + " ");
		}
		out = out.concat("\n");
		for(int i = 0; i < matrice.length; ++i) {		
			out = out.concat(ligneToString());
			out = out.concat((i > 9) ? "" : "0");
			out = out.concat(i + " >  ");
			for(int j = 0; j < matrice.length; ++j) {
				out = out.concat(" " + matrice[j][i] + " ");
				if(j == matrice.length - 1) out = out.concat(" ");
			}
				
			out = out.concat("\n");
			if(i == matrice.length - 1)
				out = out.concat(ligneToString());
		}
		return out;
	}

	private void viderMatrice() {
		for(int i = 0; i < matrice.length; ++i)
			for(int j = 0; j < matrice.length; ++j)
				matrice[i][j] = "  ";
	}

	private String ligneToString() {
		String out = "";
		out = out.concat("      ");
		for(int j = 0; j < matrice.length; ++j) out = out.concat("    ");
		out = out.concat(" \n");
		return out;
	}

	@Override
	protected void detruire(Element a) {
		;
	}

	@Override
	public boolean demanderSiRejouer() {
		char c;
		do { System.out.print("Desirez-vous rejouer (oui / non) ? "); }
		while((c = sc.nextLine().charAt(0)) != 'o' && c != 'n') ;
		return (c == 'o');
	}

	@Override
	public void reinitaliser() {
		viderMatrice();
	}

}
