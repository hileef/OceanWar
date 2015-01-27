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
 * Classe proposant un ensemble de m�thodes statiques permettant l'affichage
 * d'images et de textes au sein d'une fen�tre ainsi que la lecture des
 * Caracteres saisis au clavier lorsque la fen�tre a le focus.
 * <p>
 * L'ouverture de la fen�tre ({@link Graphique#ouvrir}) provoque son affichage.
 * Images et Textes sont ajout�s � la fen�tre par {@link Graphique#ajouterImage}
 * et ({@link Graphique#ajouterLibelle}). Des m�thodes au nom suffisament
 * parlant permettent de placer, deplacer, supprimer une ou tous les images et
 * textes. La mise � jour de l'affichage peut �tre forc� par l'emploi de la
 * m�thode {@link Graphique#actualiser}.
 * <p>
 * Les images doivent �tre fournies sous la forme de fichier gif, jpeg ou png.
 * Les fichiers gif anim�s ou transparents sont support�s.
 * <p>
 * La fen�tre peut �tre iconifi�e, maximis�e ou ferm�e par les icones se
 * trouvant en haut � droite. Toutefois, la fermeture de la fen�tre par l'icone
 * correspondant provoque automatiquement la fin de l'application
 * (System.exit(0)).
 * <p>
 * La lecture d'un Caractere par la m�thode {@link Graphique#lireCaractere} est
 * bloquante alors que la m�thode {@link Graphique#lireCaractereNonBloquant} ne
 * l'est pas.
 * <p>
 * Tout appel de m�thode avant l'ouverture de la fen�tre provoque une erreur.
 * Enfin, si la m�thode {@link Graphique#fermer} a �t� appel�e, tout appel autre
 * qu'� la m�thode {@link Graphique#ouvrir} provoque une erreur.
 */
public class ProfGraphique {
	private static JFrame frame = null;
	private static JCanvas panel = null;
	private static Queue<Character> keyboard = null;

	private ProfGraphique() {
	}

	/**
	 * Ouvre la fen�tre dans laquelle seront affich�es les elements (textes ou
	 * images).
	 * 
	 * @param titre
	 *            Intitul� de la fen�tre.
	 * @param largeur
	 *            Largeur initiale de la fen�tre (nombre de pixels).
	 * @param hauteur
	 *            Hauteur initiale de la fen�tre (nombre de pixels).
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
	 * Ajoute une image qui sera affich�e dans la fen�tre.
	 * 
	 * @param id
	 *            Identifiant de l'�l�ment.
	 * @param nomFichier
	 *            Nom (relatif ou absolu) du fichier (gif, jpeg ou png)
	 *            contenant l'image.
	 * @param x
	 *            Position horizontale (0 � gauche de la fen�tre).
	 * @param y
	 *            Position verticale (0 en haut de la fen�tre).
	 * @return true si l'image a pu �tre ajout�e (le fichier existe et est dans
	 *         un format acceptable) et que la fen�tre ne contient auncun
	 *         �l�ment portant un idenfiant �gal � id.
	 */
	public static boolean ajouterImage(int id, String nomFichier, int x, int y) {
		return panel.ajouterElement(id, new Image(nomFichier, x, y));
	}

	/**
	 * Ajoute un �l�ment textuel qui sera affich� dans la fen�tre.
	 * 
	 * @param id
	 *            Identifiant de l'�l�ment.
	 * @param Libelle
	 *            Texte devant �tre affich�.
	 * @param x
	 *            Position horizontale (0 � gauche de la fen�tre).
	 * @param y
	 *            Position verticale (0 en haut de la fen�tre).
	 * @return true si la fen�tre ne contient auncun �l�ment portant un
	 *         idenfiant �gal � id.
	 */
	public static boolean ajouterLibelle(int id, String Libelle, int x, int y) {
		return panel.ajouterElement(id, new Libelle(Libelle, x, y));
	}

	/**
	 * Retourne la postion graphique en X d'un �l�ment.
	 * @param id
	 *            Identifiant de l'�l�ment.
	 * @return
	 *            Sa position en X.
	 */
	public int getX(int id) {
		return panel.getX(id);
	}
	
	/**
	 * Retourne la postion graphique en Y d'un �l�ment. 
	 * @param id
	 *            Identifiant de l'�l�ment.
	 * @return
	 *            Sa position en Y.
	 */
	public int getY(int id) {
		return panel.getY(id);
	}
	
	/**
	 * Place �l�ment (texte ou image) de la fen�tre.
	 * 
	 * @param id
	 *            Identifiant de l'�l�ment devant �tre plac�.
	 * @param x
	 *            Position horizontale (0 � gauche de la fen�tre).
	 * @param y
	 *            Position verticale (0 en haut de la fen�tre).
	 * @return false si aucun �l�ment de la fen�tre ne porte un idenfiant �gal �
	 *         id.
	 */
	public static boolean placerElement(int id, int x, int y) {
		return panel.placerElement(id, x, y);
	}

	/**
	 * D�place un �l�ment (texte ou image) de la fen�tre.
	 * 
	 * @param id
	 *            Identifiant de l'�l�ment devant �tre d�plac�.
	 * @param dx
	 *            D�placement horizontal (n�gatif pour la gauche).
	 * @param dy
	 *            D�placement vertical (n�gatif pour le haut).
	 * @return false si aucun �l�ment de la fen�tre ne porte un idenfiant �gal �
	 *         id.
	 */
	public static boolean deplacerElement(int id, int dx, int dy) {
		return panel.deplacerElement(id, dx, dy);
	}

	/**
	 * Retire un �l�ment (texte ou image) de la fen�tre.
	 * 
	 * @param id
	 *            Identifiant de l'�l�ment devant �tre retir�.
	 * @return false si aucun �l�ment de la fen�tre ne porte un idenfiant �gal �
	 *         id.
	 */
	public static boolean retirerElement(int id) {
		return panel.retirerElement(id);
	}

	/** Retire toutes les images de la fen�tre. */
	public static void retirerTout() {
		panel.effacer();
	}

	/** Actualise l'affichage de la fen�tre. */
	public static void actualiser() {
		panel.repaint();
	}

	/** Ferme la fen�tre. */
	public static void fermer() {
		retirerTout();
		frame.dispose();
		panel = null;
		frame = null;
		keyboard = null;
	}

	/**
	 * Lire le prochain carat�re. Les Caracteres saisis alors que la fen�tre a
	 * le focus sont bufferis�s. La m�thode retourne les Caracteres dans l'ordre
	 * o� ils ont �t� saisis. La lecture est bloquante (i.e. si le buffer est
	 * vide, le programme attend qu'un Caractere soit saisi).
	 * 
	 * @return Le prochain Caractere saisi.
	 * @see GUITools#lireCaractereNonBloquant
	 */
	public static char lireCaractere() {
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

	/** Voir la m�thode {@link GUITools#lireCaractereNonBloquant}. */
	public static final char CHAR_UNDEFINED = KeyEvent.CHAR_UNDEFINED;

	/**
	 * Lire le prochain carat�re. Les Caracteres saisis alors que la fen�tre a
	 * le focus sont bufferis�s. La m�thode retourne les Caracteres dans l'ordre
	 * o� ils ont �t� saisis. La lecture n'est pas bloquante. Si le buffer est
	 * vide, la valeur {@link GUITools#CHAR_UNDEFINED} est retourn�e.
	 * 
	 * @return Le prochain Caractere saisi ou {@link GUITools#CHAR_UNDEFINED}.
	 * @see GUITools#lireCaractere
	 */
	public static char lireCaractereNonBloquant() {
		char c = CHAR_UNDEFINED;
		synchronized (keyboard) {
			if (!keyboard.isEmpty())
				c = keyboard.poll();
		}
		return c;
	}

	// Partie technique ne devant pas �tre employ�e en dehors de la classe
	// ci-dessus.
	private static abstract class Element {
		private int x, y;

		public Element(int x, int y) {
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

	private static class Image extends Element {
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

	private static class Libelle extends Element {
		private String libelle;

		public Libelle(String Libelle, int x, int y) {
			super(x, y);
			this.libelle = Libelle;
		}

		public void affiche(Graphics2D g2d) {
			g2d.drawString(libelle, getX(), getY());
		}
	}

	private static class JCanvas extends JPanel {
		private static final long serialVersionUID = 1L;
		private Map<Integer, Element> elements = new HashMap<Integer, Element>();

		public boolean ajouterElement(int id, Element i) {
			if (!i.isOK())
				return false;
			synchronized (elements) {
				if (elements.containsKey(id))
					return false;
				elements.put(id, i);
			}
			return true;
		}

		public boolean retirerElement(int id) {
			synchronized (elements) {
				return elements.remove(id) != null;
			}
		}

		public boolean placerElement(int id, int x, int y) {
			synchronized (elements) {
				Element i = elements.get(id);
				if (i == null)
					return false;
				i.setX(x);
				i.setY(y);
				return true;
			}
		}

		public boolean deplacerElement(int id, int x, int y) {
			synchronized (elements) {
				Element i = elements.get(id);
				if (i == null)
					return false;
				i.setX(i.getX() + x);
				i.setY(i.getY() + y);
				return true;
			}
		}

		public int getX(int id) {
			synchronized (elements) {
				Element i = elements.get(id);
				if (i == null)
					return Integer.MAX_VALUE;
				return i.getX();
			}
		}

		public int getY(int id) {
			synchronized (elements) {
				Element i = elements.get(id);
				if (i == null)
					return Integer.MAX_VALUE;
				return i.getY();
			}
		}

		public void effacer() {
			synchronized (elements) {
				elements.clear();
			}
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			synchronized (elements) {
				for (Element i : elements.values())
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
