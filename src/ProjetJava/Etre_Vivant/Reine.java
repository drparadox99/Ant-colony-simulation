package ProjetJava.Etre_Vivant;

import ProjetJava.Fourmiliere;
import ProjetJava.Etre_nonVivant.Pheromone;
import ProjetJava.Etre_nonVivant.Provision;
import ProjetJava.Terrain;
import java.util.Random;
import java.awt.*; 
import javax.swing.ImageIcon;
import java.util.Vector;

/** Cette classe modélise un type d'insectes hyménoptères.
 */	
public class Reine extends Fourmi{
    private Vector<Pheromone> lstPheromone;      
    private int annneNaissance;

	/** Constructeur de la class Reine.
		@param fourmiliere La fourmilière à laquelle la reine appartient.	
	*/
	public Reine(Fourmiliere fourmiliere){
		super(new Point(fourmiliere.point.x +fourmiliere.widthFourmiliere/2,fourmiliere.point.y+ fourmiliere.heightFourmiliere/2 ),fourmiliere);
		this.image = new ImageIcon("sprite/reine.png"); 
		this.addImage(fourmiliere.terrain.fenetre);  
		this.lstPheromone  = new Vector<Pheromone>();		
		this.annneNaissance = fourmiliere.terrain.horloge.retourneAnnees();
		this.vie = 150;
	}	

	/** Ajoute des fourmis dans la liste des fourmis de la fourmilière.
		@param typeAnimal Le type de Fourmis à ajouter dans la liste.	
	*/
	public void incuber(String typeAnimal){							
		if(typeAnimal.equals("Guerriere")) fourmiliere.lstFourmis.add(new Guerriere(fourmiliere));	
		if(typeAnimal.equals("Ouvriere")) fourmiliere.lstFourmis.add(new Ouvriere(fourmiliere));
	}

	/** Retourne la quantité  du type de fourmis passé en paramètre dans la fourmilère.
		@param typeAnimal Le type d'animal dont la quantité est à déterminer.
	*/
	public int retourneQuantiteDeFourmi(String typeAnimal){
			int quantiteGuerriere = 0; 
			int quantiteOuvriere = 0;
			for(Animal a: fourmiliere.lstFourmis){
				if(a instanceof Guerriere) quantiteGuerriere++;
				if(a instanceof Ouvriere) quantiteOuvriere++;					
			}		
			if(typeAnimal.equals("Guerriere")) return quantiteGuerriere;
			if(typeAnimal.equals("Ouvriere")) return quantiteOuvriere;				
			return -1;
	}


	/** Ajoute des fourmis sur le terrain du moment que leur effectif diminue dans la fourmilière.		
	*/
	public void ecloreTimer(){
			this.image = new ImageIcon("sprite/reine2.png"); //sprite pour les fourmis
        	if(retourneQuantiteDeFourmi("Guerriere") < 2) this.incuber("Guerriere");
        	if(retourneQuantiteDeFourmi("Ouvriere") < 2) this.incuber("Ouvriere");
    }


	/** Nourrit la reine.
	*/	
	public void manger(){
		for(int i = 0; i<4;i++){
			if(fourmiliere.nourritureRestant() > 0){
				this.vie += 10;;
				//System.out.println("La reine vient de se nourir");
				Provision p = fourmiliere.retourneNourriture();
				fourmiliere.provisionsCollectees.remove(p);
				p.rmImage();
				Terrain.quantite_Nourritures--;
			}else{
				this.vie -= 10;
				//System.out.println("La reine n'a pas pu se nourir");
			}
		}	
	}
	
	/** Immobilise la reine.
	*/	
	public void deplacer(){
		this.point.x += 0;
 		this.point.y += 0;
	}

	/** Change l'orientation de l'image de la reine.
	*/	
	public void appelTimer(){
		this.image = new ImageIcon("sprite/reine2.png");
	}

	/**Retourne l'année de naissance de la reine.
	@return Retourne l'année de naissance.
	*/
	public int retourneAnneeNaissance(){
		return this.annneNaissance;
	}

}