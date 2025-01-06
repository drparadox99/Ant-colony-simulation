package ProjetJava.Etre_Vivant;

import ProjetJava.Fourmiliere;
import ProjetJava.Etre_nonVivant.Pheromone;
import ProjetJava.Etre_nonVivant.Provision;
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
/** Cette classe modélise un membre de la famille des insectes hyménoptères.
*/
public class Guerriere extends Fourmi{
    private int retard;
    private Vector<Pheromone> lstPheromone;
    private boolean deplacementAleatoire;
    private Point destination;
    private Object objCollision;
    private boolean collisionDetectee;
    private boolean prochaineCollision;
    private int moisNaissance;


	/** Constructeur de la class Guerrière.
		@param fourmiliere La fourmilière à laquelle la guerrière appartient.
	*/
	public Guerriere(Fourmiliere fourmiliere){
		super(new Point(new Random().nextInt(205)+fourmiliere.point.x, new Random().nextInt(160)+fourmiliere.point.y),fourmiliere);
		//this.random = new Random();
		tabImageOrientiation[0] = "sprite/guerriereHaut.png";
		tabImageOrientiation[1] = "sprite/guerriereDroite.png";
		tabImageOrientiation[2] = "sprite/guerriereBas.png";
		tabImageOrientiation[3] = "sprite/guerriereGauche.png";
		this.changeDirection(random.nextInt(4)+1);
		this.retard = 10000;
		this.addImage(fourmiliere.terrain.fenetre);
		this.dx = 3;
		this.dy = 3;
		this.objCollision = null;
		this.moisNaissance = fourmiliere.terrain.horloge.retourneMois();
		//this.listeAnimaux.add(this);

	}

	/** Déplace la guerrière sur le terrrain.
	*/
	public void deplacer(){
		//Verifie l'état de l'animal, si il est vivant ou pas
		this.mettreAJourEtat();
		int valAlea ;
		//4 vers le bas //3 vers le haut// 2  vers la gauche // 1 vers la droite
			detectCollision();
			if(this.point.x  < 1 && this.gauche){
				valAlea = random.nextInt(2);
				if(valAlea == 0) changeDirection(4);
				else changeDirection(1);
			}else if(this.point.x > this.fenetreWidth && this.droite) {
				valAlea = random.nextInt(2);
				if(valAlea == 0) changeDirection(4);
				else  changeDirection(2);
				//changeDirection(3);
				//changeDirection(2);
				//changeDirection(4);
			}else if(this.point.y < 1 && this.haut) {
				    valAlea = valAlea = random.nextInt(2);
				    if(valAlea == 0) changeDirection(2);
			  		else changeDirection(4);
			  		//changeDirection(1);
			  		//changeDirection(4);
			}else if(this.point.y > this.fenetreHeight && this.bas ) {
				valAlea = valAlea = random.nextInt(2);
				 if(valAlea == 0) changeDirection(1);
				 else changeDirection(3);
		    	 //changeDirection(2);
		    	 //changeDirection(3);
		    	 //this.droite = true;
		    }
			if(this.droite)  this.point.x += this.dx;
			else if(this.gauche) this.point.x -= this.dx;
			else if(this.bas)  this.point.y += this.dy;
			else if(this.haut)  this.point.y -= this.dy;
			if(collisionDetectee){  			 //si on a trouvé de la nourriture il faut la ramener au point de départ (au nid)
	  	  this.deplacementAleatoire = false; //Donc on met deplacementAleatoire à faux et on appele retourAuNid
			}
	}


	/** Détècte une collistion entre la guèrrière et d'autres animaux qui font pas partie de sa fourmilière et d'autres objets.
	*/
	void detectCollision(){
		Vector<Object> lstObj = fourmiliere.terrain.fenetre.retourneLstAffiche();
		Rectangle c  ;
		int size = lstObj.size();
		for(int i = 0; i<size ; i++){
			if(i < (fourmiliere.terrain.fenetre.retourneLstAffiche()).size() ){
				Object obj = lstObj.get(i);
				if(obj instanceof AnimalMenacant || obj instanceof Guerriere && ((Guerriere)obj).fourmiliere != this.fourmiliere){
					c = new Rectangle(((Animal)obj).point.x ,((Animal)obj).point.y,25,25);
					if(new Rectangle(this.point.x,this.point.y,25,25).intersects(c)){
						this.objCollision = obj;  //l'objet avec lequel on est entré en colision
						Animal animalMenacant = (Animal)obj;
						attaquer(animalMenacant);
						animalMenacant.vie -=10;
						//System.out.println("Vie mouche" + animalMenacant.vie );
					}
				}
			}
		}
	}

	/** Permet à la guerrière d'attaquer ses ennemis.
	*/
	public void attaquer(Animal animalMenacant){
		if(this.droite || animalMenacant.droite || this.gauche || animalMenacant.gauche ){
			if(animalMenacant.point.x > this.point.x){
					for(int i=0 ; i< 5 ;i++){
						if(this.enRegleX()) this.point.x -=5;
						if(animalMenacant.enRegleX()) animalMenacant.point.x +=5;
					}
					   if(this.enRegleX()) this.point.x +=5;
					   if(animalMenacant.enRegleX()) animalMenacant.point.x -=5;
			}
			if(animalMenacant.point.x < this.point.x){
					for(int i=0 ; i<5 ;i++){
						if(this.enRegleX()) this.point.x +=5;
						if(animalMenacant.enRegleX()) animalMenacant.point.x -=5;
					}
					if(this.enRegleX()) this.point.x -=5;
					if(animalMenacant.enRegleX()) animalMenacant.point.x +=5;
			}
		}else{
			if(animalMenacant.point.y > this.point.y){
					for(int i=0 ; i<5 ;i++){
						if(this.enRegleY()) this.point.y -=5;
						if(animalMenacant.enRegleY()) animalMenacant.point.y +=5;
					}
					if(this.enRegleY()) this.point.y +=5;
					if(animalMenacant.enRegleY()) animalMenacant.point.y -=5;
			}
			if(animalMenacant.point.y < this.point.y){
					for(int i=0 ; i<5 ;i++){
						if(this.enRegleY()) this.point.y +=5;
						if(animalMenacant.enRegleY()) animalMenacant.point.y -=5;
					}
					if(this.enRegleY()) this.point.y -=5;
					if(animalMenacant.enRegleY()) animalMenacant.point.y +=5;
			}
		}
	}


	/** Change la direction de la guerrière de façon aléatoire.
	*/
	public void appelTimer(){
            int duree = random.nextInt(4)+1;	//fournit une diréction aléatoire
            this.changeDirection(duree);			//Pour changer la direction de la fourmi
	}

	/** Fait manger la guerrière
	*/
	public void manger(){
		for(int i = 0; i<1;i++){
			//System.out.println("RESTANT " +  fourmiliere.nourritureRestant());
			if(fourmiliere.nourritureRestant() > 0){
				this.vie += 10;
				//System.out.println("La guerrière vient de se nourir");
				Provision p = fourmiliere.retourneNourriture();
				fourmiliere.provisionsCollectees.remove(p);
				p.rmImage();
				Terrain.quantite_Nourritures--;
			}else{
				this.vie -=10;
				//System.out.println("La guerrière n'a pas pu se nourir");
			}
		}
	}

	/**Retourne le mois de naissance de la guerrière.
	@return Retourne le mois de naissance.
	*/
	public int retourneMoisNaissance(){
		return this.moisNaissance;
	}


}
