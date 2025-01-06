package ProjetJava.Etre_Vivant;

import ProjetJava.Fourmiliere;
import ProjetJava.Etre_nonVivant.Materiau;
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
public class Ouvriere extends Fourmi{
	    private int retard;
	    private Vector<Pheromone> lstPheromone;
	    private boolean deplacementAleatoire;
	    private Point destination;
	    private Object objCollision;
	    private boolean collisionDetectee;
	    private boolean prochaineCollision;
	    private boolean faire = false;
	    private int semainesNaissance;

		/** Constructeur de la class Ouvrière.
			@param fourmiliere La fourmilière à laquelle la ouvrière appartient.
		*/
		public Ouvriere(Fourmiliere fourmiliere){
			super(new Point(new Random().nextInt(205)+fourmiliere.point.x, new Random().nextInt(160)+fourmiliere.point.y),fourmiliere ) ;
			tabImageOrientiation[0] = "sprite/ouvriereHaut.png";
			tabImageOrientiation[1] = "sprite/ouvriereDroite.png";
			tabImageOrientiation[2] = "sprite/ouvriereBas.png";
			tabImageOrientiation[3] = "sprite/ouvriereGauche.png";
			changeDirection(random.nextInt(4)+1);
			this.retard = 10000;
			this.addImage(fourmiliere.terrain.fenetre);
			this.dx = 3;
			this.dy = 3;
			this.lstPheromone  = new Vector<Pheromone>();
			this.destination = new Point(random.nextInt(185)+fourmiliere.point.x, random.nextInt(150)+fourmiliere.point.y);
			this.objCollision = null;
			this.deplacementAleatoire = true;
			this.collisionDetectee = false;
			this.prochaineCollision = true;
			this.faire = false;
			this.semainesNaissance = fourmiliere.terrain.horloge.retourneSemaines();
			//this.listeAnimaux.add(this);
			//this.fourmiliere = fourmiliere;
		}

		/** Déplace l'ouvrière sur le terrrain de façon aléatoire.
		*/
		public void deplacer(){
			//Verifie l'état de l'animal, si il est vivant ou pas
			this.mettreAJourEtat();

			//4 vers le bas //3 vers le haut// 2  vers la gauche // 1 vers la droite
			int valAlea ;

			//detectionCollision est appelée si il n'a pas détécté de collision
			if(prochaineCollision) detectCollision();
			if(deplacementAleatoire){
				if(this.point.x  < 1 && this.gauche){
					valAlea = random.nextInt(2);
					if(valAlea == 0) changeDirection(4);
					else changeDirection(1);
				}else if(this.point.x > this.fenetreWidth  && this.droite) {
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
				}else if(this.point.y > this.fenetreHeight  && this.bas ) {
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
			}else{
			 		retourAuNid();
			}
		}

		/** Fait rentrer l'ouvrière à son nid.
		*/
		public void retourAuNid(){
			if(this.objCollision != null){  //On met à jour la position de la nourriture losqu'on retourne au nid

				if(((Provision)this.objCollision).transporteuse == this ){
					((Provision)this.objCollision).x = this.point.x+20;
					((Provision)this.objCollision).y = this.point.y+12;
				}else{
					 this.prochaineCollision = true;
					this.deplacementAleatoire = true; 	//si la liste est vide, il y a plus de phèromone
					this.collisionDetectee = false;
					faire = false;
					//this.objCollision = null;
				}
			}
			if(faire){
			Pheromone p = null ;	 //On répupère la position de la dernière phèremone insérée dans la liste de phèromones

			if(!this.lstPheromone.isEmpty()){  //on extrait la position de la dermière phèromone largée
				p = this.lstPheromone.get(this.lstPheromone.size()-1);
				this.destination.x = p.x;
				this.destination.y = p.y;
			}
			//this.destination.x != this.point.x

				if(this.destination.x >= this.point.x && !(this.destination.x - this.point.x  < 3)){
					this.droite = true;
					this.gauche = this.haut = this.bas = false;

					this.orientationImage();
					this.point.x +=this.dx;
				}else if(this.destination.x <= this.point.x && !(this.point.x- this.destination.x  < 3)){
					this.gauche = true;
					this.haut = this.droite = this.bas = false;
					this.orientationImage();
					this.point.x -= this.dx;
				}
				//this.destination.y != this.point.y
			else if(this.destination.y >= this.point.y && !(this.destination.y - this.point.y  < 3)){
					this.haut = this.droite = this.gauche = false;
					this.bas = true;
					this.orientationImage();
					this.point.y += this.dy;
				}else if(destination.y <= this.point.y && !(this.point.y- this.destination.y  < 3)){
					this.haut = true;
					this.bas = this.droite = this.gauche = false;
					this.orientationImage();
					this.point.y -= this.dy;
				}
			else{ //On est arrivé à la position x,y de la phèromone laquelle on va enlever de la liste
				if(!this.lstPheromone.isEmpty()){
					int size = this.lstPheromone.size()-1;
					this.lstPheromone.remove(size);
					p.rmImage(); //on enlève la phèromone de l'écran
					if(this.lstPheromone.size() == 0) {
						this.deplacementAleatoire = true; 	//si la liste est vide, il y a plus de phèromone
						this.collisionDetectee = false;
						this.prochaineCollision = true;
						if(!this.fourmiliere.provisionsCollectees.contains((Provision)objCollision)){
							this.fourmiliere.provisionsCollectees.add((Provision)objCollision);
							((Provision)this.objCollision).transportee = false;
						}
					    //System.out.println("LA taille Provision" + this.fourmiliere.provisionsCollectees.size());
					   	if(this.objCollision instanceof Materiau){
					   		((Provision)this.objCollision).x += 20;
							//((Provision)this.objCollision).y = this.point.y+12;
					   	}
					}
				}else{
			        this.prochaineCollision = true;
					this.deplacementAleatoire = true; 	//si la liste est vide, il y a plus de phèromone
					this.collisionDetectee = false;
					if(!this.fourmiliere.provisionsCollectees.contains((Provision)objCollision)){
						this.fourmiliere.provisionsCollectees.add((Provision)objCollision);
						((Provision)this.objCollision).transportee = false;
					}
					//System.out.println("LA TAILLE Nourriture" + this.fourmiliere.provisionsCollectees.size());
					if(this.objCollision instanceof Materiau){
					   	((Provision)this.objCollision).x += 20;
					 }

					}
				}
			 }
			}

			/** Détècte une collistion entre l'ouvrière et d'autres animaux qui font pas partie de sa fourmilière et d'autres objets.
			*/
			void detectCollision(){
			Vector<Object> lstObj = this.fourmiliere.terrain.fenetre.retourneLstAffiche();
			Rectangle c  ;
			Rectangle o ;
			Rectangle d ;
			Rectangle e ;
			Rectangle n;
			//for(Object obj: lstObj){
			    int size = lstObj.size();
			    for(int i= 0;i<size;i++){
			    	if(i < (this.fourmiliere.terrain.fenetre.retourneLstAffiche()).size() ){
					    Object obj = lstObj.get(i);
						if(obj instanceof Provision){
							//n = new Rectangle(((Provision)obj).point.x ,((Provision)obj).point.y,25,25);
							//if(new Rectangle(this.point.x,this.point.y,25,25).intersects(n)){
							c = new Rectangle(((Provision)obj).x ,((Provision)obj).y,5,5 );
							o = new Rectangle(((Provision)obj).x+10 ,((Provision)obj).y,5,5 );
							d = new Rectangle(((Provision)obj).x ,((Provision)obj).y+7,5,5 );
							e = new Rectangle(((Provision)obj).x+10 ,((Provision)obj).y+7,5,5 );
							//if(new Rectangle(this.point.x,this.point.y,30,25).intersects(c) || new Rectangle(this.point.x,this.point.y,30,25).intersects(o) || new Rectangle(this.point.x,this.point.y,30,25).intersects(d) || new Rectangle(this.point.x,this.point.y,30,25).intersects(e) ){
							if(this.point.x > ((Provision)obj).x && this.point.x < ((Provision)obj).x+30 && this.point.y > ((Provision)obj).y && this.point.y < ((Provision)obj).y+ 30
							 || this.point.x+25 > ((Provision)obj).x && this.point.x+25 < ((Provision)obj).x+ 30 && this.point.y > ((Provision)obj).y && this.point.y < ((Provision)obj).y+ 30 ||
							 this.point.x > ((Provision)obj).x && this.point.x < ((Provision)obj).x+ 30 && this.point.y+25 > ((Provision)obj).y && this.point.y+25 < ((Provision)obj).y+ 30 ||
							 this.point.x+25 > ((Provision)obj).x && this.point.x+25 < ((Provision)obj).x+ 30 && this.point.y+25 > ((Provision)obj).y && this.point.y+25 < ((Provision)obj).y+ 30 ||
							 new Rectangle(this.point.x,this.point.y,30,25).intersects(c) || new Rectangle(this.point.x,this.point.y,30,25).intersects(o) || new Rectangle(this.point.x,this.point.y,30,25).intersects(d) || new Rectangle(this.point.x,this.point.y,30,25).intersects(e)
							 ){
								this.objCollision = obj;   //l'objet avec lequel on est entré en colision
								//if(!this.fourmiliere.provisionsCollectees.contains((Provision)obj) && ((Provision)obj).transporteuse != this  ){
								if(!this.fourmiliere.provisionsCollectees.contains((Provision)obj) &&  ( ((Provision)obj).transporteuse != this && ( ((Provision)obj).transporteuse == null || (((Provision)obj).transporteuse).fourmiliere != this.fourmiliere  ))){
									eliminerDoublant((Provision)obj);
									this.prochaineCollision = false;
									((Provision)obj).transportee = true;
									((Provision)obj).transporteuse = this;
									((Provision)obj).touchee = true;
									//this.fourmiliere.pheromonesCollectees.add((Nourriture)objCollision);
									this.collisionDetectee = true;
									faire = true;

								}
							}
						}
					}
			 	}
		    }

		  /**Enlève de la fourmilière la provision volée par une ouvrière appartenant à une autre fourmilière.
		  */
		  void eliminerDoublant(Provision duVol){
		  	if(duVol.transporteuse != null){
		  		Fourmiliere f = duVol.transporteuse.fourmiliere;
		  		f.provisionsCollectees.remove(duVol);
		  	}

		  }

		/** Change la direction de l'ouvrière de façon aléatoire.
		*/
		public void appelTimer(){
	        if(deplacementAleatoire){
	            int duree = random.nextInt(4)+1;	//fournit une diréction aléatoire
	            this.changeDirection(duree);				//Pour changer la direction de la fourmis
	            Pheromone pheromone = new Pheromone(point,Color.RED,this.fourmiliere.fenetre); //Créer un objet d'une phèromone
	            if(lstPheromone.isEmpty()) lstPheromone.add(new Pheromone(new Point(random.nextInt(185)+fourmiliere.point.x, random.nextInt(150)+fourmiliere.point.y),Color.RED,this.fourmiliere.fenetre));
	               	this.lstPheromone.add(pheromone);  	//ajoute l'objet dans la liste des phèromone de la fourmis
	                pheromone.addImage();			//affiche l'image  de la phèromone à l'écran
	            }
	    }

		/**Nourrit la guerrière.
		*/
		public void manger(){
			for(int i = 0; i<1;i++){
				if(fourmiliere.nourritureRestant() > 0){
					this.vie +=10;
					//System.out.println("L'ouvriere vient de se nourir");
					Provision p = fourmiliere.retourneNourriture();
					fourmiliere.provisionsCollectees.remove(p);
					p.rmImage();
					Terrain.quantite_Nourritures--;
				}else{
					this.vie -= 10;
					//System.out.println("L'ouvriere n'a pas pu se nourir");
				}
			}
		}

		/**Retourne la semaine de naissance de l'ouvrière.
		@return Retourne la semaine de naissance de l'ouvrière
		*/
		public int retourneSemainesNaissance(){
			return this.semainesNaissance;
		}
	}
