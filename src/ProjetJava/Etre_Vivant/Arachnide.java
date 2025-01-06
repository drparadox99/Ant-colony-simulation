package ProjetJava.Etre_Vivant;

import java.awt.Point;
/** Cette classe modélise les membres de la classe des Arachnide.
*/	
public abstract class Arachnide extends AnimalMenacant{
	/**Le constructeur de la classe Arachnide.
	@param point Les coordonnées de l'objet sur le terain.
	*/
	public Arachnide(Point point){
		super(point);
	}

}