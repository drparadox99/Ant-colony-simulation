package ProjetJava.Etre_Vivant;

import java.awt.Shape;
import java.awt.Point;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionListener;     
import java.awt.event.ActionEvent; 
import java.awt.*;
import javax.swing.ImageIcon;

/**Cette classe modélise les aniamux menacants sur le terrain.
*/
public abstract class AnimalMenacant extends Animal{
	/** Le constructeur de la class Animal. 
		@param point Les coordonées x et y de l'animal menaçant.
 	*/
	public AnimalMenacant(Point point){
		super(point);	
	}		
}

