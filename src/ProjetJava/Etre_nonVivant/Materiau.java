package ProjetJava.Etre_nonVivant;

import ProjetJava.GUI_for_Displayable;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.*;  

/**Cette classe modélise la matière utlisée pour la construction du nid des fourmis
*/
public class Materiau extends Provision{	

	/** Le constructeur de la class Materiau
		@param point les coordonées x et y du matétiau
		@param fenetre La fenêtre sur laquelle nous plaçons le matériau
 	*/
	public Materiau(Point point,GUI_for_Displayable fenetre){
		super(point,null,fenetre);
		this.image = new ImageIcon("sprite/matos.jpg");
		this.x = point.x;
		this.y = point.y;
	}
}