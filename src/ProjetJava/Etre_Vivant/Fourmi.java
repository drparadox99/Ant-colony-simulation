package ProjetJava.Etre_Vivant;

import ProjetJava.Fourmiliere;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Vector;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionListener;     
import java.awt.event.ActionEvent; 
import java.awt.*; 
import javax.swing.ImageIcon;

/**Cette classe modélise un membre de la famille des insectes hyménoptères.
*/
public abstract class Fourmi extends Animal{	
    protected Fourmiliere fourmiliere;
    //boolean affame;

    /** Le constructeur de la class Fourmis.
		@param point Les coordonées x et y de la fourmis.
		@param fourmiliere La fourmilière à laquelle la fourmis appartient.
 	*/
	public Fourmi(Point point,Fourmiliere fourmiliere){
		super(point);
		this.gauche = this.haut = this.bas = false;
		this.droite = true;
		//this.random = new Random();
		this.image = null;
		this.dx = 5;
		this.dy = 5;	
		this.fourmiliere = fourmiliere;
	}	
}

