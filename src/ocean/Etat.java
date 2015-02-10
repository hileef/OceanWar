package ocean;

/**
 * Enum representant un etat d'element a notifier,
 * notamment a l'ocean et a l'affichage (mais peut etre d'autres plus tard!)
 * 
 * => DETRUIT signale que l'element a ete detruit et qu'il ne doit plus etre reference ou utilise
 * => POSITION signale que la position de l'element peut avoir ete changee,
 *        utilisee notamment par l'affichage
 *
 */
public enum Etat {
	DETRUIT, POSITION;
}
