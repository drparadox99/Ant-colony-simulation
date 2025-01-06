package ProjetJava.Etre_Vivant;

import ProjetJava.GUI_for_Displayable;
import ProjetJava.Habitude;
import java.awt.event.*;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Vector;
import java.util.Random;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.awt.*;  

/**Cette classe modélise les animaux du terrain.
*/
public abstract class Animal extends EtreVivant implements Habitude{
protected int vie;
public boolean vivant;
protected boolean gauche,droite,haut,bas;
protected int dy;
protected int dx;
protected String tabImageOrientiation [];

/** Le constructeur de la class Animal. 
	@param point les coordonées x,y de l'animal sur le terrain.
 */
public Animal(Point point){	
	super(point);
	this.color = null;
	this.gauche = this.haut = this.bas = false;
	this.droite = true;
	this.vie = 100;
	this.vivant = true;
	this.tabImageOrientiation = new String[4];
}


/** Dessine L'animal sur l'écran.		
	@param g L'objet graphics permettant de dessiner l'animal sur le terrain.
	@param c La composante sur laquelle nous dessions l'animal.
 */
 public void draw(Graphics g,Component c){ 
    this.image.paintIcon(c,g,point.x,point.y); 
 }
 
 /** Ajoute l'animal dans la liste des objets à dessiner sur le terrain.		
	@param fenetre La fenêtre sur laquelle nous dessinons l'animal.
 */	
 void addImage(GUI_for_Displayable fenêtre){
 	fenêtre.addElement(this);
 }

 /** Vérifie si la coordonée x de l'animal ne dépasse pas les délimitations de la fenêtre.
		Retourne vrai si l'animal ne dépasse les delimitations, faux sinon.
 */
 boolean enRegleX(){
 	if(this.point.x >= 1 && this.point.x < this.fenetreWidth) return true; 	
 	return false;
 }

 /** Vérifie si la coordonée y de l'animal ne dépasse pas les délimitations de la fenêtre. 
		@return  Retourne vrai si l'animal ne dépasse les delimitations, faux sinon.
 */
 boolean  enRegleY(){
 	if(this.point.y >= 1 && this.point.y < this.fenetreHeight) return true; 	
 	return false;
 }
 
 /** Détermine si l'animal est vivant ou mort.
 */
 void mettreAJourEtat(){
 	if(this.vie < 0) this.vivant = false;
 }

/** Détermine l'image appropriée en fonction de la direction de l'animal.	
*/
 public void orientationImage(){		
		if(this.droite){
		 this.image = new ImageIcon(tabImageOrientiation[1]);
		}
		else if(this.gauche) {			
			this.image = new ImageIcon(tabImageOrientiation[3]);
		}
		else if(this.haut){
			this.image = new ImageIcon(tabImageOrientiation[0]);
		} 
		else if(this.bas) {
			this.image = new ImageIcon(tabImageOrientiation[2]); 
		}
}

/** Change la direction de l'animal en fonction de l'indice passé en paramètre.
	@param i indique la direction vers laqulle l'animal doit se diriger.
*/
public void changeDirection(int i){
		if(i == 1) {			
			 this.droite = true;
			 this.haut = this.bas = this.gauche = false;
			 this.orientationImage();
		}else if(i == 2) {
			this.gauche = true;
			this.haut = this.bas =  this.droite = false;
			this.orientationImage();
		}else if(i == 3){
			this.haut = true;
			this.droite = this.bas = this.gauche = false;
			this.orientationImage();			
		}else if(i == 4) {
			this.bas = true;
			this.haut = this.droite = this.gauche = false;
			this.orientationImage();				
		}
}


/** Déplace l'animal.
 */
 abstract public void deplacer();
 /** Change la direction de l'animal.
 */
 abstract public void appelTimer();

/** Fait manger l'animal.
 */
 abstract public void manger();

}
