package ProjetJava.Etre_nonVivant;

import ProjetJava.Displayable;
import ProjetJava.GUI_for_Displayable;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Random;
import javax.swing.ImageIcon;

/** Cette classe modélise la nature dans laquelle les évènements se déroulent
*/
public abstract class Nature implements Displayable {
	public Point point;
	protected Shape shape;
	protected Color color;
	protected int fenetreWidth;
	protected int fenetreHeight;
	public ImageIcon image;
	protected Random random ;


	/** Le constructor initialisant les dimensions de la fenêtre

	*/
	public Nature(){
		this.fenetreWidth = GUI_for_Displayable.fenetreWidth-35;			
		this.fenetreHeight = GUI_for_Displayable.fenetreHeight -80 ;
		this.point = null;
		this.shape = null;
		this.color = null;
		this.image = null;
		this.random = new Random();
	}
	

	/** Retourne l'objet de type Color appartenant à cette classe
	@return Retourne un objet de type Color
	*/
	public java.awt.Color getColor(){
		return this.color;
	}
	/** Retourne l'objet de type shape appartenant à cette classe		
	@return  Retourne un objet de type Shape
	*/
	public java.awt.Shape getShape(){		         		         
		   return this.shape;	
	}



}
