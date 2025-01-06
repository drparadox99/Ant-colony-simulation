package ProjetJava.Etre_nonVivant;

import ProjetJava.GUI_for_Displayable;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.*; 

/**Cette classe modélise l'alimentation ramasée et consomée par les fourmis
*/
public class Nourriture extends Provision{

	/** Le constructeur de la class Nourriture 
		@param point les coordonées x et y de la nourriture
		@param fenetre La fenêtre sur laquelle nous placons la nourriture
 	*/
	public Nourriture(Point point,GUI_for_Displayable fenetre){
		super(point,null,fenetre);
		this.image = new ImageIcon("sprite/bouffe.png");
		this.x = point.x;
		this.y = point.y;
	}	
}