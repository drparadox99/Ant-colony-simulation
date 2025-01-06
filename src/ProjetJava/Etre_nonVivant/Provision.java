package ProjetJava.Etre_nonVivant;

import ProjetJava.Etre_Vivant.Ouvriere;
import ProjetJava.GUI_for_Displayable;
import ProjetJava.Terrain;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.*; 

/** Cette classe modélise les fourninutres nécessaires pour la survie des animaux.
 */	
public abstract class Provision extends Terrain{
	public int x;
	public int y;
	public boolean transportee;
	public Ouvriere transporteuse;
	public boolean touchee;
	
	/** Le constructeur de la class Provision.
		@param point Les coordonées x et y de la provision.
		@param color La couleur de la provision.
		@param fenetre La fenêtre sur laquelle nous placons la provision.
 	*/
	public Provision(Point point,Color color,GUI_for_Displayable fenetre){
		super(point,color,fenetre);
		this.image = null;
		this.x = point.x;
		this.y = point.y;
		this.transportee = false;
		this.transporteuse = null;
		this.touchee = false ;
	}

	/** Dessine la provision sur l'écran.		
 	*/
	public void draw(Graphics g,Component c){ 
    this.image.paintIcon(c,g,this.x,this.y); 
	 }

	/** Ajoute la provision dans la liste des objets à dessiner sur le terrain.		
 	*/
  	public void addImage(){
 		this.fenetre.addElement(this);
 	}

 	/** Enlève la provision de la liste des objets à dessiner sur le terrain.		
 	*/
 	public void rmImage(){
 		this.fenetre.removeElement(this);
 	}

}