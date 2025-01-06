package ProjetJava.Etre_Vivant;

import ProjetJava.Etre_nonVivant.Nature;
import java.awt.Point;
/** Cette classe modélise les entités animées de vie.
 */	
public abstract class EtreVivant extends Nature{

/** Le constructeur de la classe ÊtreVivant.
	@param point Les coordonnées de l'être vivant.
*/
EtreVivant(Point point ){
	this.point = point;
}



}