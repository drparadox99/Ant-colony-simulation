package ProjetJava.Etre_nonVivant;

import ProjetJava.GUI_for_Displayable;
import ProjetJava.Terrain;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.awt.*;  

public class Pheromone extends Terrain{
	ImageIcon   imgPheromone;
	public int x;
	public int y;

	/** Le constructeur de la class Pheromone. 
		@param point les coordonées x,y de la phèromone.
		@param color la couleur du phèromone.
		@param fenetre La fenêtre laquelle nous palçons la phèreomone.
 	*/
	public Pheromone(Point point,Color color,GUI_for_Displayable fenetre){
		super(point,color,fenetre);		
		this.x = point.x;
		this.y = point.y;
		this.imgPheromone = new ImageIcon("sprite/pher.jpg");	
	}

	/** Le constructeur de la class Pheromone. 
		@param g L'objet graphics permettant de dessiner la phèromone sur le terrain.
		@param c La composante sur laquelle nous dessions la phèromone.
 	*/
	public void draw(Graphics g,Component c){ 
    	this.imgPheromone.paintIcon(c,g,this.x,this.y); 
	 }

 	/** Ajoute la phèromone dans la liste des objets à dessiner sur le terrain.		
 	*/
  	public void addImage(){
 		this.fenetre.addElement(this);
 	}

 	/** Enlève l'animal dans la liste des objets à dessiner sur le terrain.		
 	*/
  	public void rmImage(){
		this.fenetre.removeElement(this);
 	}
	
}