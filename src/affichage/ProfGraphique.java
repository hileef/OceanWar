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
 * caract�res saisis au clavier lorsque la fen�tre a le focus.
 * <p>
 * L'ouverture de la fen�tre ({@link Graphique#ouvrir}) provoque son affichage.
 * Images et Textes sont ajout�s � la fen�tre par {@link Graphique#ajouterImage}
 * et ({@link Graphique#ajouterLibell�}). Des m�thodes au nom suffisament
 * parlant permettent de placer, d�placer, supprimer une ou tous les images et
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
 * La lecture d'un caract�re par la m�thode {@link Graphique#lireCaract�re} est
 * bloquante alors que la m�thode {@link Graphique#lireCaract�reNonBloquant} ne
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
	 * Ouvre la fen�tre dans laquelle seront affich�es les �l�ments (textes ou
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
		return panel.ajouterEl�ment(id, new Image(nomFichier, x, y));
	}

	/**
	 * Ajoute un �l�ment textuel qui sera affich� dans la fen�tre.
	 * 
	 * @param id
	 *            Identifiant de l'�l�ment.
	 * @param libell�
	 *            Texte devant �tre affich�.
	 * @param x
	 *            Position horizontale (0 � gauche de la fen�tre).
	 * @param y
	 *            Position verticale (0 en haut de la fen�tre).
	 * @return true si la fen�tre ne contient auncun �l�ment portant un
	 *         idenfiant �gal � id.
	 */
	public static boolean ajouterLibell�(int id, String libell�, int x, int y) {
		return panel.ajouterEl�ment(id, new Libell�(libell�, x, y));
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
	public static boolean placerEl�ment(int id, int x, int y) {
		return panel.placerEl�ment(id, x, y);
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
	public static boolean d�placerEl�ment(int id, int dx, int dy) {
		return panel.d�placerEl�ment(id, dx, dy);
	}

	/**
	 * Retire un �l�ment (texte ou image) de la fen�tre.
	 * 
	 * @param id
	 *            Identifiant de l'�l�ment devant �tre retir�.
	 * @return false si aucun �l�ment de la fen�tre ne porte un idenfiant �gal �
	 *         id.
	 */
	public static boolean retirerEl�ment(int id) {
		return panel.retirerEl�ment(id);
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
	 * Lire le prochain carat�re. Les caract�res saisis alors que la fen�tre a
	 * le focus sont bufferis�s. La m�thode retourne les caract�res dans l'ordre
	 * o� ils ont �t� saisis. La lecture est bloquante (i.e. si le buffer est
	 * vide, le programme attend qu'un caract�re soit saisi).
	 * 
	 * @return Le prochain caract�re saisi.
	 * @see GUITools#lireCaract�reNonBloquant
	 */
	public static char lireCaract�re() {
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

	/** Voir la m�thode {@link GUITools#lireCaract�reNonBloquant}. */
	public static final char CHAR_UNDEFINED = KeyEvent.CHAR_UNDEFINED;

	/**
	 * Lire le prochain carat�re. Les caract�res saisis alors que la fen�tre a
	 * le focus sont bufferis�s. La m�thode retourne les caract�res dans l'ordre
	 * o� ils ont �t� saisis. La lecture n'est pas bloquante. Si le buffer est
	 * vide, la valeur {@link GUITools#CHAR_UNDEFINED} est retourn�e.
	 * 
	 * @return Le prochain caract�re saisi ou {@link GUITools#CHAR_UNDEFINED}.
	 * @see GUITools#lireCaract�re
	 */
	public static char lireCaract�reNonBloquant() {
		char c = CHAR_UNDEFINED;
		synchronized (keyboard) {
			if (!keyboard.isEmpty())
				c = keyboard.poll();
		}
		return c;
	}

	// Partie technique ne devant pas �tre employ�e en dehors de la classe
	// ci-dessus.
	private static abstract class El�ment {
		private int x, y;

		public El�ment(int x, int y) {
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

	private static class Image extends El�ment {
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

	private static class Libell� extends El�ment {
		private String libell�;

		public Libell�(String libell�, int x, int y) {
			super(x, y);
			this.libell� = libell�;
		}

		public void affiche(Graphics2D g2d) {
			g2d.drawString(libell�, getX(), getY());
		}
	}

	private static class JCanvas extends JPanel {
		private static final long serialVersionUID = 1L;
		private Map<Integer, El�ment> �l�ments = new HashMap<Integer, El�ment>();

		public boolean ajouterEl�ment(int id, El�ment i) {
			if (!i.isOK())
				return false;
			synchronized (�l�ments) {
				if (�l�ments.containsKey(id))
					return false;
				�l�ments.put(id, i);
			}
			return true;
		}

		public boolean retirerEl�ment(int id) {
			synchronized (�l�ments) {
				return �l�ments.remove(id) != null;
			}
		}

		public boolean placerEl�ment(int id, int x, int y) {
			synchronized (�l�ments) {
				El�ment i = �l�ments.get(id);
				if (i == null)
					return false;
				i.setX(x);
				i.setY(y);
				return true;
			}
		}

		public boolean d�placerEl�ment(int id, int x, int y) {
			synchronized (�l�ments) {
				El�ment i = �l�ments.get(id);
				if (i == null)
					return false;
				i.setX(i.getX() + x);
				i.setY(i.getY() + y);
				return true;
			}
		}

		public int getX(int id) {
			synchronized (�l�ments) {
				El�ment i = �l�ments.get(id);
				if (i == null)
					return Integer.MAX_VALUE;
				return i.getX();
			}
		}

		public int getY(int id) {
			synchronized (�l�ments) {
				El�ment i = �l�ments.get(id);
				if (i == null)
					return Integer.MAX_VALUE;
				return i.getY();
			}
		}

		public void effacer() {
			synchronized (�l�ments) {
				�l�ments.clear();
			}
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			synchronized (�l�ments) {
				for (El�ment i : �l�ments.values())
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
