package ProjetJava;

import ProjetJava.Etre_nonVivant.Provision;
import ProjetJava.Etre_nonVivant.Nourriture;
import ProjetJava.Etre_nonVivant.Materiau;
import ProjetJava.Etre_Vivant.Guerriere;
import ProjetJava.Etre_Vivant.Reine;
import ProjetJava.Etre_Vivant.Animal;
import ProjetJava.Etre_Vivant.Ouvriere;
import ProjetJava.Etre_Vivant.AnimalMenacant;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.*;
import java.util.Vector;
import javax.swing.ImageIcon;
/** Cette classe modélise l'habitation des fourmis.
*/
public class Fourmiliere extends EtreNonVivant{	
	public Vector<Provision>  provisionsCollectees;
	public Vector<Animal> lstFourmis;	
	public int widthFourmiliere ;
	public int heightFourmiliere ;
	protected int annee ;
	protected int mois;
	protected int semaine;
	public Terrain terrain;
	public Point point;
	protected Shape shape;
	public GUI_for_Displayable fenetre;
	
	/** Constructeur de la classe Fourmilière.
		@param terrain Le terrain sur lequel nous allons positionner la fourmilière.
		@param nbrCellule L'indice de la cellule sur le terrain sur laquelle nous allons positionner la fourmilière.
		@param fenetre La fenêtre sur laqualle la fourmilière sera placée.
	*/
	 Fourmiliere(Terrain terrain,int nbrCellule,GUI_for_Displayable fenetre){
	 	super(null);
		this.fenetre = fenetre;
		this.terrain = terrain;		
		this.positionnerFourmiliere(terrain,nbrCellule);
		this.point =  ((Rectangle)this.shape).getLocation() ;
		this.provisionsCollectees = new Vector<Provision>();
		this.lstFourmis = new Vector<Animal>();
		this.widthFourmiliere = (int)((Rectangle)this.shape).getWidth();
		this.heightFourmiliere = (int)((Rectangle)this.shape).getHeight();		
		this.terrain.lstFourmilieres.add(this);
		this.genesis();
	}

	/** Fait naitre les premières fourmis de la fourmilière.
	*/	
	private void genesis(){
        this.lstFourmis.add(new Reine(this));		
		for(int i = 0 ; i<2;i++){
	        this.lstFourmis.add(new Guerriere(this));
			this.lstFourmis.add(new Ouvriere(this));
		}		
	}

	/** Retourne la quantité de nourriture qui dans la fourmilière.
		@return  La quantité de nourriture disponible dans la fourmilière.
	*/
	public int nourritureRestant(){
		int nbr = 0;
		for(Provision obj:this.provisionsCollectees){
		 if (obj instanceof Nourriture){
		 	nbr++;
		 }
		}
		//System.out.println("Le vrai nombre de nourriture est " + nbr);
		return nbr;
	}

	/** Positionne la fourmilière sur le terrain et la cellule passés en paramètre.
		@param terrain Le terrain surlequel nous placons la fourmilière.
		@param l'indice L'indice de la cellule du terrain sur lequel nous allons placer la fourmilière.
	*/
	void positionnerFourmiliere(Terrain terrain, int nbrCellule){
		this.shape = new Rectangle((terrain.retourneCellule(nbrCellule)).point.x,(terrain.retourneCellule(nbrCellule)).point.y,224,182);;
        //terrain.lstCellules.get(nbrCellule).color = Color.RED; 
	}


	/** Retourne une portion de nourriture disponible dans la fourmilière.
		@return Retourne une portion de nourriture contenue dans la fourmilière.
	*/	
	public Provision retourneNourriture(){
		for(Provision obj:this.provisionsCollectees){
		 	if (obj instanceof Nourriture) return obj;		 
		}
		return null;
	}

	/** Déplace les fourmis de la fourmilière.
	*/
	void deplacer(){
		Reine reine = null ;
		for(Animal obj: this.lstFourmis){
			obj.deplacer();
			if(obj instanceof Reine) reine = (Reine)obj;			
		}		
		this.verifierMortalite();
		//terrain.horloge.afficher();		

		//Au bout de 20secondes
		if(this.terrain.horloge.retourneMinutes() % 5 == 0 && terrain.horloge.retourneSecondes() == 0 ) this.appelTimer();

		//Au bout de 2 mns
		if(this.terrain.horloge.retourneMinutes() % 40 == 0 && this.terrain.horloge.retourneSecondes() == 0 ){
			this.TimerAlimentation();
			this.envleveMAtos();
		} 

		//Au bout 1 minute
		if(this.terrain.horloge.retourneMinutes() == 10 && this.terrain.horloge.retourneSecondes() == 0){
			this.verifierEsperance();
		}

		//Production des fourmis par la reine losqu'il y a une pénurie de fourmis 
		if(this.terrain.horloge.retourneMinutes() % 20 == 0 && terrain.horloge.retourneSecondes() == 0){
			if(reine != null) reine.ecloreTimer();
			reine.image = new ImageIcon("sprite/reine.png"); //sprite pour les fourmis
		}

		//Production régulière des fourmis par la reine  
		if(this.terrain.horloge.retourneMinutes() % 21 == 0 && terrain.horloge.retourneSecondes() == 0){
			if(reine != null) {
				reine.incuber("Ouvriere");
				reine.incuber("Guerriere");
			}
		}		
	}


	/** Trouve les fourmis mortes de la fourmilère et les enlève de l'écran.
	*/
	protected void verifierMortalite(){
		Vector<Animal> dead = new Vector<Animal>();
		//Détection des animaux qui sont morts
		for(Animal obj: this.lstFourmis){
			if (!obj.vivant){				
				//liste d'animaux morts
				dead.add(obj);		
			}
		}
		//Enlève les animaux de la liste des éléments à afficher et de la liste des animaux.
		for(Animal obj: dead){
			this.terrain.fenetre.removeElement(obj);
			this.lstFourmis.remove(obj);
		}
	}

	 /** Change la direction des fourmis de la fourmilière.
	 */

	protected void appelTimer(){
    	for(Animal obj: lstFourmis){
			obj.appelTimer();
		}        	
		for(AnimalMenacant obj:terrain.lstAnimauxMenacants)obj.appelTimer();
	}


	/** Nourrit toutes les fourmis de la fourmilère, à condition qu'il y ait assez de nourritures.	
	*/
	protected void TimerAlimentation(){
        	for(Animal obj: lstFourmis){
        		if(obj instanceof Reine) obj.manger();					
			}
			for(Animal obj:lstFourmis){
				if(obj instanceof Ouvriere) obj.manger();
			}
			for(Animal obj:lstFourmis){
				if(obj instanceof Guerriere) obj.manger();
			}
			terrain.timerAlimentation();
	}	

	/** Enlève des materiau dans la fourmilière
	*/
	public void envleveMAtos(){
		Vector<Provision> lstTemp = new Vector<Provision>();
		for(Provision obj:this.provisionsCollectees){
		 	if (obj instanceof Materiau) lstTemp.add(obj);		 
		}
		for(Provision obj:lstTemp){
			this.provisionsCollectees.remove(obj);
			Terrain.quantite_Materiaux--;
			obj.rmImage();
		}
	}

	/** Vérifie que les fourmis ne dépassent pas leur durée de vie.
	*/
	protected void verifierEsperance(){
		//Au bout de 2 ans la reine meurt
			for(Animal obj: lstFourmis){
            	if(obj instanceof Reine){            		
            		if( this.terrain.horloge.retourneAnnees() - ((Reine)obj).retourneAnneeNaissance() >= 2) obj.vivant = false; 
            	}
            	if(obj instanceof Guerriere){
            		if(this.terrain.horloge.retourneMois() - ((Guerriere)obj).retourneMoisNaissance() >= 3) obj.vivant = false;            	 
            	}
            	if(obj instanceof Ouvriere){
            		if(this.terrain.horloge.retourneSemaines()-((Ouvriere)obj).retourneSemainesNaissance() >=3 )obj.vivant = false;
            	}
			}	
	}
}