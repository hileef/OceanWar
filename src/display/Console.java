package display;

import core.Element;
import core.World;

public class Console extends ADisplay {
	
	private String[][] matrice;

	public Console() {
		matrice = new String[World.SIZE][World.SIZE];
		clear();
	}

	@Override
	public void update(Element e) {
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
	
}
