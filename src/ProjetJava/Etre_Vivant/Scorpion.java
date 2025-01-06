package ProjetJava.Etre_Vivant;

import ProjetJava.Terrain;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Vector;
import java.util.Random;
import java.awt.*;  
import javax.swing.ImageIcon;
import java.util.Iterator;

/** Cette classe modélise un arthropode de la famille des Arachnides.
 */	
public class Scorpion extends Arachnide{     
    Object objCollision;
    Terrain terrain;

    /** Constructeur de la classe Scorpion.

		@param terrain Le terrain sur lequel le scorpion apparait.
	*/
	public Scorpion(Terrain terrain){
		super(null);
		this.point = new Point(new Random().nextInt(this.fenetreWidth),new Random().nextInt(this.fenetreHeight) );
		this.droite = this.haut = this.bas = false;
		this.gauche = true;
		tabImageOrientiation[0] = "sprite/scorpionHaut.png";
		tabImageOrientiation[1] = "sprite/scorpionDroite.png";
		tabImageOrientiation[2] = "sprite/scorpionBas.png";
		tabImageOrientiation[3] = "sprite/scorpionGauche.png";
		this.orientationImage();
		this.terrain = terrain;
		this.addImage(terrain.fenetre); 
		this.appelTimer();  
		this.dx = 3;
		this.dy = 3;
		this.objCollision = null;
		this.vie  = 100;
		Terrain.quantite_AnimauxMenacants++;
		//this.listeAnimaux.add(this);
	}	
	
	/** Déplace le scorpion sur le terrrain de façon aléatoire.
	*/
	public void deplacer(){
	
		//Verifie l'état de l'animal, si il est vivant ou pas	
		this.mettreAJourEtat();
		
		//4 vers le bas //3 vers le haut// 2  vers la gauche // 1 vers la droite
		int valAlea ;				
			//orientationImage();		
			this.detectCollision(); 
			if(this.point.x  < 1 && this.gauche){
				valAlea = random.nextInt(2);
				if(valAlea == 0) changeDirection(4);
				else changeDirection(1);
			}else if(this.point.x > this.fenetreWidth && this.droite) {
				valAlea = random.nextInt(2);
				if(valAlea == 0) changeDirection(4);
				else  changeDirection(2);		
			}else if(this.point.y < 1 && this.haut) {
				    valAlea = valAlea = random.nextInt(2);
				    if(valAlea == 0) changeDirection(2);
			  		else changeDirection(4);		  					  			  		
			}else if(this.point.y > this.fenetreHeight && this.bas ) {
				valAlea = valAlea = random.nextInt(2);
				 if(valAlea == 0) changeDirection(1);
				 else changeDirection(3);		    	
		    }
			if(this.droite)  this.point.x += this.dx;
			else if(this.gauche) this.point.x -= this.dx; 
			else if(this.bas)  this.point.y += this.dy;
			else if(this.haut)  this.point.y -= this.dy;							
	}
	
	/** Détecte une collistion entre le scorpion et des fourmis.
	*/
	public void detectCollision(){
		Vector<Object> lstObj = terrain.fenetre.retourneLstAffiche();		
		Rectangle c  ;	
		int size = lstObj.size();
		for(int i=0;i<size;i++){
			if(i < (terrain.fenetre.retourneLstAffiche()).size() ){
				Object obj = lstObj.get(i);
			 	 
				if(obj instanceof Fourmi && !(obj instanceof Reine)){
					c = new Rectangle(((Fourmi)obj).point.x ,((Fourmi)obj).point.y,25,25);
					if(new Rectangle(this.point.x,this.point.y,25,25).intersects(c)){
						this.objCollision = obj;  //l'objet avec lequel on est entré en colision
						Animal animal = (Animal)obj;				
						this.attaquer(animal);
						animal.vie -=10;
					}
				}
			}
		}	
	}

	/** Permet au scorpion d'attaquer ses ennemis.
	*/
	public void attaquer(Animal animal){
		if(this.droite || animal.droite || this.gauche || animal.gauche ){
			if(animal.point.x > this.point.x){				
					for(int i=0 ; i< 5 ;i++){												
						if(this.enRegleX()) this.point.x -=5; 						
						if(animal.enRegleX()) animal.point.x +=5; 				
					}
					   if(this.enRegleX()) this.point.x +=5;
					   if(animal.enRegleX()) animal.point.x -=5;					
			}
			if(animal.point.x < this.point.x){
					for(int i=0 ; i<5 ;i++){
						if(this.enRegleX()) this.point.x +=5;						 
						if(animal.enRegleX()) animal.point.x -=5;					
					}
					if(this.enRegleX()) this.point.x -=5;
					if(animal.enRegleX()) animal.point.x +=5;					
			}
		}else{
			if(animal.point.y > this.point.y){
					for(int i=0 ; i<5 ;i++){
						if(this.enRegleY()) this.point.y -=5;
						if(animal.enRegleY()) animal.point.y +=5;
					}
					if(this.enRegleY()) this.point.y +=5;
					if(animal.enRegleY()) animal.point.y -=5;
			}
			if(animal.point.y < this.point.y){				
					for(int i=0 ; i<5 ;i++){
						if(this.enRegleY()) this.point.y +=5;
						if(animal.enRegleY()) animal.point.y -=5;
					}
					if(this.enRegleY()) this.point.y -=5;
					if(animal.enRegleY()) animal.point.y +=5;				
			}
		}
	}

	/** Change la direction du scorpion de façon aléatoire.
	*/
	public void appelTimer(){		
            int duree = random.nextInt(4)+1;	
             this.changeDirection(duree);		            	
	}

	/** Nourrit le scorpion.
	*/	
	public void manger(){
		this.vie +=10;
	}
}

