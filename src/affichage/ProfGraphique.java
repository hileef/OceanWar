package affichage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MediaTracker;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Classe proposant un ensemble de méthodes statiques permettant l'affichage
 * d'images et de textes au sein d'une fenêtre ainsi que la lecture des
 * caractères saisis au clavier lorsque la fenêtre a le focus.
 * <p>
 * L'ouverture de la fenêtre ({@link Graphique#ouvrir}) provoque son affichage.
 * Images et Textes sont ajoutés à la fenêtre par {@link Graphique#ajouterImage}
 * et ({@link Graphique#ajouterLibellé}). Des méthodes au nom suffisament
 * parlant permettent de placer, déplacer, supprimer une ou tous les images et
 * textes. La mise à jour de l'affichage peut être forcé par l'emploi de la
 * méthode {@link Graphique#actualiser}.
 * <p>
 * Les images doivent être fournies sous la forme de fichier gif, jpeg ou png.
 * Les fichiers gif animés ou transparents sont supportés.
 * <p>
 * La fenêtre peut être iconifiée, maximisée ou fermée par les icones se
 * trouvant en haut à droite. Toutefois, la fermeture de la fenêtre par l'icone
 * correspondant provoque automatiquement la fin de l'application
 * (System.exit(0)).
 * <p>
 * La lecture d'un caractère par la méthode {@link Graphique#lireCaractère} est
 * bloquante alors que la méthode {@link Graphique#lireCaractèreNonBloquant} ne
 * l'est pas.
 * <p>
 * Tout appel de méthode avant l'ouverture de la fenêtre provoque une erreur.
 * Enfin, si la méthode {@link Graphique#fermer} a été appelée, tout appel autre
 * qu'à la méthode {@link Graphique#ouvrir} provoque une erreur.
 */
public class ProfGraphique {
	private static JFrame frame = null;
	private static JCanvas panel = null;
	private static Queue<Character> keyboard = null;

	private ProfGraphique() {
	}

	/**
	 * Ouvre la fenêtre dans laquelle seront affichées les éléments (textes ou
	 * images).
	 * 
	 * @param titre
	 *            Intitulé de la fenêtre.
	 * @param largeur
	 *            Largeur initiale de la fenêtre (nombre de pixels).
	 * @param hauteur
	 *            Hauteur initiale de la fenêtre (nombre de pixels).
	 */
	public static void ouvrir(String titre, int largeur, int hauteur) {
		frame = new JFrame(titre);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		panel = new JCanvas();
		panel.setFont(new Font("Arial", Font.BOLD, 18));
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(largeur, hauteur));

		keyboard = new LinkedList<Character>();
		frame.addKeyListener(new KeyRecorder(keyboard));

		frame.getContentPane().add(panel);
		frame.pack();

		frame.setVisible(true);
	}

	/**
	 * Ajoute une image qui sera affichée dans la fenêtre.
	 * 
	 * @param id
	 *            Identifiant de l'élément.
	 * @param nomFichier
	 *            Nom (relatif ou absolu) du fichier (gif, jpeg ou png)
	 *            contenant l'image.
	 * @param x
	 *            Position horizontale (0 à gauche de la fenêtre).
	 * @param y
	 *            Position verticale (0 en haut de la fenêtre).
	 * @return true si l'image a pu être ajoutée (le fichier existe et est dans
	 *         un format acceptable) et que la fenêtre ne contient auncun
	 *         élément portant un idenfiant égal à id.
	 */
	public static boolean ajouterImage(int id, String nomFichier, int x, int y) {
		return panel.ajouterElément(id, new Image(nomFichier, x, y));
	}

	/**
	 * Ajoute un élément textuel qui sera affiché dans la fenêtre.
	 * 
	 * @param id
	 *            Identifiant de l'élément.
	 * @param libellé
	 *            Texte devant être affiché.
	 * @param x
	 *            Position horizontale (0 à gauche de la fenêtre).
	 * @param y
	 *            Position verticale (0 en haut de la fenêtre).
	 * @return true si la fenêtre ne contient auncun élément portant un
	 *         idenfiant égal à id.
	 */
	public static boolean ajouterLibellé(int id, String libellé, int x, int y) {
		return panel.ajouterElément(id, new Libellé(libellé, x, y));
	}

	/**
	 * Retourne la postion graphique en X d'un élément.
	 * @param id
	 *            Identifiant de l'élément.
	 * @return
	 *            Sa position en X.
	 */
	public int getX(int id) {
		return panel.getX(id);
	}
	
	/**
	 * Retourne la postion graphique en Y d'un élément. 
	 * @param id
	 *            Identifiant de l'élément.
	 * @return
	 *            Sa position en Y.
	 */
	public int getY(int id) {
		return panel.getY(id);
	}
	
	/**
	 * Place élément (texte ou image) de la fenêtre.
	 * 
	 * @param id
	 *            Identifiant de l'élément devant être placé.
	 * @param x
	 *            Position horizontale (0 à gauche de la fenêtre).
	 * @param y
	 *            Position verticale (0 en haut de la fenêtre).
	 * @return false si aucun élément de la fenêtre ne porte un idenfiant égal à
	 *         id.
	 */
	public static boolean placerElément(int id, int x, int y) {
		return panel.placerElément(id, x, y);
	}

	/**
	 * Déplace un élément (texte ou image) de la fenêtre.
	 * 
	 * @param id
	 *            Identifiant de l'élément devant être déplacé.
	 * @param dx
	 *            Déplacement horizontal (négatif pour la gauche).
	 * @param dy
	 *            Déplacement vertical (négatif pour le haut).
	 * @return false si aucun élément de la fenêtre ne porte un idenfiant égal à
	 *         id.
	 */
	public static boolean déplacerElément(int id, int dx, int dy) {
		return panel.déplacerElément(id, dx, dy);
	}

	/**
	 * Retire un élément (texte ou image) de la fenêtre.
	 * 
	 * @param id
	 *            Identifiant de l'élément devant être retiré.
	 * @return false si aucun élément de la fenêtre ne porte un idenfiant égal à
	 *         id.
	 */
	public static boolean retirerElément(int id) {
		return panel.retirerElément(id);
	}

	/** Retire toutes les images de la fenêtre. */
	public static void retirerTout() {
		panel.effacer();
	}

	/** Actualise l'affichage de la fenêtre. */
	public static void actualiser() {
		panel.repaint();
	}

	/** Ferme la fenêtre. */
	public static void fermer() {
		retirerTout();
		frame.dispose();
		panel = null;
		frame = null;
		keyboard = null;
	}

	/**
	 * Lire le prochain caratère. Les caractères saisis alors que la fenêtre a
	 * le focus sont bufferisés. La méthode retourne les caractères dans l'ordre
	 * où ils ont été saisis. La lecture est bloquante (i.e. si le buffer est
	 * vide, le programme attend qu'un caractère soit saisi).
	 * 
	 * @return Le prochain caractère saisi.
	 * @see GUITools#lireCaractèreNonBloquant
	 */
	public static char lireCaractère() {
		char c;
		synchronized (keyboard) {
			while (keyboard.isEmpty())
				try {
					keyboard.wait();
				} catch (InterruptedException e) {
				}
			c = keyboard.poll();
		}
		return c;
	}

	/** Voir la méthode {@link GUITools#lireCaractèreNonBloquant}. */
	public static final char CHAR_UNDEFINED = KeyEvent.CHAR_UNDEFINED;

	/**
	 * Lire le prochain caratère. Les caractères saisis alors que la fenêtre a
	 * le focus sont bufferisés. La méthode retourne les caractères dans l'ordre
	 * où ils ont été saisis. La lecture n'est pas bloquante. Si le buffer est
	 * vide, la valeur {@link GUITools#CHAR_UNDEFINED} est retournée.
	 * 
	 * @return Le prochain caractère saisi ou {@link GUITools#CHAR_UNDEFINED}.
	 * @see GUITools#lireCaractère
	 */
	public static char lireCaractèreNonBloquant() {
		char c = CHAR_UNDEFINED;
		synchronized (keyboard) {
			if (!keyboard.isEmpty())
				c = keyboard.poll();
		}
		return c;
	}

	// Partie technique ne devant pas être employée en dehors de la classe
	// ci-dessus.
	private static abstract class Elément {
		private int x, y;

		public Elément(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public abstract void affiche(Graphics2D g2d);

		public boolean isOK() {
			return true;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}
	}

	private static class Image extends Elément {
		private ImageIcon image;

		public Image(String nomFichier, int x, int y) {
			super(x, y);
			image = new ImageIcon(nomFichier);
		}

		public void affiche(Graphics2D g2d) {
			g2d.drawImage(image.getImage(), getX(), getY(), null);
		}

		public boolean isOK() {
			return image.getImageLoadStatus() != MediaTracker.ERRORED;
		}
	}

	private static class Libellé extends Elément {
		private String libellé;

		public Libellé(String libellé, int x, int y) {
			super(x, y);
			this.libellé = libellé;
		}

		public void affiche(Graphics2D g2d) {
			g2d.drawString(libellé, getX(), getY());
		}
	}

	private static class JCanvas extends JPanel {
		private static final long serialVersionUID = 1L;
		private Map<Integer, Elément> éléments = new HashMap<Integer, Elément>();

		public boolean ajouterElément(int id, Elément i) {
			if (!i.isOK())
				return false;
			synchronized (éléments) {
				if (éléments.containsKey(id))
					return false;
				éléments.put(id, i);
			}
			return true;
		}

		public boolean retirerElément(int id) {
			synchronized (éléments) {
				return éléments.remove(id) != null;
			}
		}

		public boolean placerElément(int id, int x, int y) {
			synchronized (éléments) {
				Elément i = éléments.get(id);
				if (i == null)
					return false;
				i.setX(x);
				i.setY(y);
				return true;
			}
		}

		public boolean déplacerElément(int id, int x, int y) {
			synchronized (éléments) {
				Elément i = éléments.get(id);
				if (i == null)
					return false;
				i.setX(i.getX() + x);
				i.setY(i.getY() + y);
				return true;
			}
		}

		public int getX(int id) {
			synchronized (éléments) {
				Elément i = éléments.get(id);
				if (i == null)
					return Integer.MAX_VALUE;
				return i.getX();
			}
		}

		public int getY(int id) {
			synchronized (éléments) {
				Elément i = éléments.get(id);
				if (i == null)
					return Integer.MAX_VALUE;
				return i.getY();
			}
		}

		public void effacer() {
			synchronized (éléments) {
				éléments.clear();
			}
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			synchronized (éléments) {
				for (Elément i : éléments.values())
					i.affiche(g2d);
			}
			// une grille (pratique pour debug)
			// for (int x = 30; x < 840; x += 30)
			// g2d.drawLine(x, 0, x, 930);
			// for (int y = 30; y < 930; y += 30)
			// g2d.drawLine(0, y, 840, y);
		}
	}

	private static class KeyRecorder implements KeyListener {
		private final Queue<Character> file;

		public KeyRecorder(Queue<Character> file) {
			this.file = file;
		}

		public void keyPressed(KeyEvent e) {
			char c = KeyEvent.CHAR_UNDEFINED;
			switch (e.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				c = '2';
				break;
			case KeyEvent.VK_LEFT:
				c = '4';
				break;
			case KeyEvent.VK_RIGHT:
				c = '6';
				break;
			case KeyEvent.VK_UP:
				c = '8';
				break;
			}
			if (c != KeyEvent.CHAR_UNDEFINED)
				synchronized (file) {
					file.add(c);
					file.notifyAll();
				}
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() != KeyEvent.CHAR_UNDEFINED)
				synchronized (file) {
					file.add(e.getKeyChar());
					file.notifyAll();
				}
		}
	}

}
