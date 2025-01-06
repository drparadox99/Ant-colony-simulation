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

/** Cette classe modélise un oiseau de la famille des corvidés.
*/
public class Geai_Bleu extends Oiseau{   
    //int dx;
    //int dy;
    Object objCollision;
    Terrain terrain;

    /** Constructeur de la classe Geai_Bleu.

		@param terrain Le terrain sur lequel le geai bleu apparait.
	*/
	public Geai_Bleu(Terrain terrain){
		super(null);
		this.point = new Point(new Random().nextInt(this.fenetreWidth),new Random().nextInt(this.fenetreHeight) );
		this.droite = this.haut = this.bas = false;
		this.gauche = true;
		tabImageOrientiation[0] = "sprite/Geai_BleuHaut.jpeg";
		tabImageOrientiation[1] = "sprite/Geai_BleuDroite.jpeg";
		tabImageOrientiation[2] = "sprite/Geai_BleuBas.jpeg";
		tabImageOrientiation[3] = "sprite/Geai_BleuGauche.jpeg";
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
	
	/** Déplace le geai bleu sur le terrrain de façon aléatoire.
	*/
	public void deplacer(){
		this.mettreAJourEtat();
		this.creerMouvementImage();
		
		//4 vers le bas //3 vers le haut// 2  vers la gauche // 1 vers la droite
		int valAlea ;				
			orientationImage();		
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
	
	/** Détecte une collistion entre le geai bleu et les fourmis.
	*/
	public void detectCollision(){
		Vector<Object> lstObj = terrain.fenetre.retourneLstAffiche();		
		Rectangle c  ;	
		Iterator<Object> iter = lstObj.iterator();
		int size = lstObj.size();
		for(int i=0;i<size;i++){
			if(i < (terrain.fenetre.retourneLstAffiche()).size() ){
				Object obj = lstObj.get(i);		 	
				if(obj instanceof Fourmi && !(obj instanceof Reine)){
					c = new Rectangle(((Fourmi)obj).point.x ,((Fourmi)obj).point.y,25,25);
					//if(new Rectangle(this.point.x,this.point.y,30,25).intersects(c) || new Rectangle(this.point.x,this.point.y,30,25).intersects(o) || new Rectangle(this.point.x,this.point.y,30,25).intersects(d) || new Rectangle(this.point.x,this.point.y,30,25).intersects(e) ){					
					if(new Rectangle(this.point.x,this.point.y,25,25).intersects(c)){
						this.objCollision = obj;  //l'objet avec lequel on est entré en colision
						Animal animal = (Animal)obj;				
						attaquer(animal);
						animal.vie -=10;
						//System.out.println("Animal.vie " + animal.vie);					
					}
				}
			}		  
		}
	}

	/** Permet au geai bleu de voltiger.
	*/
	public void creerMouvementImage(){
		if(gauche){
			if(this.point.x % 2 == 0) tabImageOrientiation[3] = "sprite/birdGauche1.jpg";
			else tabImageOrientiation[3] = "sprite/birdGauche2.jpg";			
		}
		if(droite){
			if(this.point.x % 2 == 0) tabImageOrientiation[1] = "sprite/birdDroite1.jpg";
			else tabImageOrientiation[1] = "sprite/birdDroite2.jpg";			
		}
		if(bas){
			if(this.point.y % 2 == 0) tabImageOrientiation[2] = "sprite/birdBas1.jpg";
		 	else tabImageOrientiation[2] = "sprite/birdBas2.jpg";			
		}
		if(haut){
			if(this.point.y % 2 == 0) tabImageOrientiation[0] = "sprite/birdHaut1.jpg";
			else tabImageOrientiation[0] = "sprite/birdHaut2.jpg";			
		}
	}

	/** Permet au geai bleu d'attaquer ses ennemis.
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

	/** Change la direction du geai bleu de façon aléatoire.
	*/
	public void appelTimer(){		
        int duree = random.nextInt(4)+1;	
        this.changeDirection(duree);		            	
	}

	/** Nourrit le geai bleu.
	*/	
	public void manger(){
		this.vie +=12;
	}
}

