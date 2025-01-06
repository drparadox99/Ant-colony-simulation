package ProjetJava;

import ProjetJava.Etre_nonVivant.Nature;
import java.awt.Point;
/** Cette classe modélise les entités qui ne sont pas animées de vie.
 */	
public abstract class EtreNonVivant extends Nature{
	/** Le constructeur de la classe NonEtreVivant.
		@param point Les coordonnées de l'être non vivant.
	*/
	EtreNonVivant(Point point){
		this.point = point ;
	}
}