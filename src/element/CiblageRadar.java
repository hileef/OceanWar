package element;

/**
 * Enum representant le ciblage que le radar doit utiliser.
 * = Pour le ciblage BLESSE, le radar renverra en priorite des elements Blesses
 * = Pour le ciblage PROCHE, le radar renverra en priorite des elements Proches
 * = Pour le ciblage AUCUN, le radar ne renverra rien.
 */
public enum CiblageRadar {
	AUCUN, BLESSE, PROCHE;
}
