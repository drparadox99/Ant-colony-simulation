package ProjetJava.Etre_Vivant;

import java.awt.Point;

/** Cette classe modélise les animaux verébrés théropodes.
 */	
public abstract class Oiseau extends AnimalMenacant{
	/**Constructeur de la classe AniamlMencacant
	@param point Les coordonnées x,y de l'animal menaçant sur le terrain
	*/
	public Oiseau(Point point){
		super(point);
	}

}