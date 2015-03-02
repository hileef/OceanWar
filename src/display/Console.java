package display;

import java.util.Scanner;

import core.Displayable;

public class Console extends ADisplay {
	
	private String[][] matrice;
	private Scanner sc;

	public Console(int size) {
		super(size);
		sc = new Scanner(System.in);
		matrice = new String[worldSize()][worldSize()];
		clear();
	}

	@Override
	public void update(Displayable e) {
		if(e.isDisplayable()) {
			int x = e.position().x();
			int y = e.position().y();
			String cmp = matrice[x][y];
			int c;
			if(cmp.equals("  ")) {
				matrice[x][y] = e.toString() + " ";
			} else if((c = (cmp.charAt(0) - '0')) > 0 && c <= 9) {
				matrice[x][y] = ((++c) + "x");
			} else {
				matrice[x][y] = "2x";
			}
		}
	}
	
	private String lineToString() {
		String out = "";
		out = out.concat("      ");
		for(int j = 0; j < matrice.length; ++j) out = out.concat("    ");
		out = out.concat(" \n");
		return out;
	}
	
	public String toString() {
		String out = "      ";
		for(int i = 0; i < matrice.length; ++i) {
			out = out.concat(" ");
			out = out.concat((i > 9) ? "" : "0");
			out = out.concat(i + " ");
		}
		out = out.concat("\n");
		for(int i = 0; i < matrice.length; ++i) {		
			out = out.concat(lineToString());
			out = out.concat((i > 9) ? "" : "0");
			out = out.concat(i + " >  ");
			for(int j = 0; j < matrice.length; ++j) {
				out = out.concat(" " + matrice[j][i] + " ");
				if(j == matrice.length - 1) out = out.concat(" ");
			}
				
			out = out.concat("\n");
			if(i == matrice.length - 1)
				out = out.concat(lineToString());
		}
		return out;
	}

	@Override
	public void refresh(double seconds) {
		System.out.println(this);
		clear();
		pause(seconds);
	}

	private void clear() {
		for(int i = 0; i < matrice.length; ++i)
			for(int j = 0; j < matrice.length; ++j)
				matrice[i][j] = "  ";
	}

	@Override
	public boolean askForReplay() {
		clear();
		
		System.out.print("Voulez-vous rejouer ? (oui/non) ");
		while(true) {
			String test = sc.nextLine().toLowerCase();
			if(test.length() > 0)
			switch(test.charAt(0)) {
			case 'o' :
				return true;
			case 'n' :
				return false;
			default : ;
			}
		}

	}

	@Override
	public void reset() {
		clear();
	}
	
}
