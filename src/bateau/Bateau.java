package bateau;

import java.util.Collection;
import java.util.Observable;

import ocean.Ocean;
import deplacement.Deplaceur;
import element.CiblageRadar;
import element.Coordonnee;
import element.Direction;
import element.Element;
import element.EtatElement;

public abstract class Bateau extends Observable implements Element {
	private static final int NB_VIES_DEFAUT = 8;
	private static final int RAYON_RADAR = 3;
	
	private Ocean acces;
	
	private Integer id;
	private Coordonnee position;
	private Direction direction;
	private Deplaceur deplacement;
	private CiblageRadar ciblage;
	private int vies;
	
	public Bateau(Integer id, Ocean acces, Deplaceur deplacement) {
		this(id, acces, deplacement, CiblageRadar.AUCUN);
	}
	public Bateau(Integer id, Ocean acces, Deplaceur deplacement, CiblageRadar ciblage) {
		this.id = id;
		this.acces = acces;
		this.ciblage = ciblage;
		this.vies = NB_VIES_DEFAUT;
		this.deplacement = deplacement;
		this.position(Coordonnee.aleatoire(Ocean.TAILLE_MATRICE));
	}
	
//	protected Ocean acces() { return acces; }
//	protected void direction(Direction dir) { this.direction = dir; }
	protected void position(Coordonnee pos) {
		if(vies > 0) {
			this.position = pos;
			this.setChanged();
			this.notifyObservers(EtatElement.POSITION_CHANGEE);
		} 
	}

	@Override
	public Integer id() { return this.id; }

	@Override
	public Coordonnee position() { return this.position; }
	
	@Override
	public Direction direction() { return this.direction; }

	@Override
	public void toucher() {
		if(this.vies > 0) {
			this.vies = this.vies - 1;
			if(this.vies == 0) {
				this.setChanged();
				this.notifyObservers(EtatElement.DETRUIT);
			}
		}

	}

	@Override
	public void reparer() {
		if(this.vies > 0)
			this.vies = NB_VIES_DEFAUT;
	}
	
	@Override
	public int vies() {
		return vies;
	}
	
	@Override
	public int viesMax() {
		return NB_VIES_DEFAUT;
	}
	
	@Override
	public CiblageRadar ciblage() {
		return ciblage;
	}
	
	protected String imageURLComposante() {
		if(direction() == null)
			return ".png";
		else switch(direction()) {
		case NE:
		case E:
		case SE:
		case S:
			return "d.png";
		default:
			return ".png";
		}

	}
	
	protected Direction determinerDirection(Collection<Element> liste) {
		if(liste.isEmpty()) return deplacement.calculerDirection(position, direction);
		else return position.directionVers(liste.iterator().next().position());
	}
	
	public Collection<Element> tour() {
		Collection<Element> listeDepuisRadar = acces.radar(this, 0, RAYON_RADAR);
		Direction dir = determinerDirection(listeDepuisRadar);
		if(dir == null) this.position(position);
		else { direction = dir; this.position(position.coordonneeDansDirection(dir)); }
		return listeDepuisRadar;
	}

	@Override
	public void jouerTour() {
		tour();
	}


	
}
