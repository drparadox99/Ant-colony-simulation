package ProjetJava;

import ProjetJava.Etre_nonVivant.Materiau;
import ProjetJava.Etre_nonVivant.Nourriture;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Vector;

/** Cette classe modélise les cellule qui composent le terrain.
*/
public class Cellule extends Terrain{	
	Vector<Nourriture>  lstNourriture;

	/** Le constructeur de la class Cellule.
		@param point les coordonées x et y de la cellule.
		@param shape La forme géometrique de la fourmilière.
		@param color La couleur de la cellule.
		@param fenetre La fenêtre sur laquelle la cellure va être placée.
 	*/
	public Cellule(Point point,Shape shape,Color color,GUI_for_Displayable fenetre){
		super(point,color,fenetre);	
		this.shape = shape;		
		this.point =  ((Rectangle)shape).getLocation();       
        this.ajouterNourriture();
        this.ajouterMateriau();
	}

	/** AJoute de la nourriture sur le terrain, l'emplacement etant aléatoire.
 	*/	
	public void ajouterNourriture(){
		Random random =  new Random();
		int nbrBouffe = random.nextInt(1)+1 ;
		for(int i = 0; i <= nbrBouffe; i++){
			int x = random.nextInt(180) + this.point.x ;
			int y = random.nextInt(160) + this.point.y ;	
			Nourriture bouffe = new Nourriture(new Point(x,y),this.fenetre);
			bouffe.addImage();
			//Terrain.quantite_Nourritures++;
		}
	}

	/** AJoute des matériaux sur le terrain, leur emplacement étant aléatoire.
 	*/	
	public void ajouterMateriau(){
		Random random =  new Random();
		int nbrMatos = random.nextInt(1) ;
		for(int i = 0; i <= nbrMatos; i++){
			int x = random.nextInt(180) + this.point.x ;
			int y = random.nextInt(160) + this.point.y ;	
			Materiau matos = new Materiau(new Point(x,y),this.fenetre);
			matos.addImage();
			Terrain.quantite_Materiaux++;
		}
	}

}