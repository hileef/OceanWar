package affichage;

public class Console extends Affichage {
	
	private String[][] matrice;

	public Console(int tailleMatrice) {
		matrice = new String[tailleMatrice][tailleMatrice];
		viderMatrice();
	}

	@Override
	public void actualiserPosition(Affichable a) {
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
	public void actualiserAffichage(double secondes) {
		this.pause(secondes);
		System.out.println(matriceToString());
		viderMatrice();
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
	
	
}
