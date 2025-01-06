package ProjetJava;

import ProjetJava.Etre_nonVivant.Nourriture;
import ProjetJava.Etre_Vivant.Scorpion;
import ProjetJava.Etre_Vivant.Geai_Bleu;
import ProjetJava.Etre_Vivant.AnimalMenacant;
import java.util.*;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;

/**Cette classe modélise l'espace de terre sur lequel la simuilation se désoule. 
*/
  public class Terrain extends EtreNonVivant{
	protected Vector<Cellule> lstCellules;	
	private Point cellPoint;
	protected Vector<Fourmiliere> lstFourmilieres;
	protected Vector<AnimalMenacant> lstAnimauxMenacants;
	public static int quantite_AnimauxMenacants = 0;
	public static int quantite_Nourritures = 0;
	public static int quantite_Materiaux = 0;
	public GUI_for_Displayable fenetre;
	public  Horloge horloge;


	/** Le constructor de la class Terrain.
		@param point les coordonnées x,y de l'objet.
		@param color la couleur du térrain.
		@param fenetre la fenêtre sur lequel le terrai apparait.	
	*/
	public Terrain(Point point,Color color,GUI_for_Displayable fenetre){
		super(point);
		//this.point =  point;
		this.shape = new Rectangle(0,0,900,600);	
		this.color = color;
		this.cellPoint = new Point();		
		this.lstCellules = new Vector<Cellule>();		
		this.cellPoint.x = 0;
		this.cellPoint.y = 0;
		this.lstFourmilieres = new Vector<Fourmiliere>();
		this.lstAnimauxMenacants = new Vector<AnimalMenacant>();
		this.fenetre = fenetre;
		this.horloge = new Horloge();
	}

	/** La fonction qui recupère les cellules de la fenêtre.
		@param fenetre la fenêtre pour laquelle les cellules sont destnées.	
	*/
	public void getCellules(GUI_for_Displayable fenetre){		
		for(int i=0; i<3;i++){
			for(int j=0 ; j<4;j++){
				this.lstCellules.add(new Cellule(null,new Rectangle(cellPoint.x,cellPoint.y,225,183),Color.WHITE,this.fenetre) );
				this.cellPoint.x += 226;
			}
			this.cellPoint.y += 184;
			this.cellPoint.x = 0;
		} 
		 for(Cellule cell: lstCellules) fenetre.addDisplayable(cell);//Affiche les cellules à l'écran	        
	}

	/** Retourne la cellule dont l'indice est passé en paramête dans la liste de cellule.


		@return  Retourne une cellule du terrain.
	*/
	public Cellule retourneCellule(int nbrCellules){
		return this.lstCellules.get(nbrCellules);
	}


	/** Remplit le terrain avec les premiers animaux.
		@param fenetre La fenêtre sur laquelle nous allons afficher les animaux.
	*/
	public void peupler(GUI_for_Displayable fenetre){
		System.out.println("Please choose the number of ant nests you want?");
		Scanner scanner = new Scanner(System.in);    
		int nbrFourmilieres = scanner.nextInt();
		ArrayList<Integer> lstIndiceCellules = new ArrayList<Integer>();
		for(int i=0;i<12;i++) lstIndiceCellules.add(i);
		for(int j=0;j<nbrFourmilieres;j++){
		Integer indice = lstIndiceCellules.get(random.nextInt(lstIndiceCellules.size()));
		lstIndiceCellules.remove(indice);
        Fourmiliere f = new Fourmiliere(this,indice,this.fenetre);	
		}
	}

	
	/** Fonction qui lance la simulation.		
	*/
	public void simulation(){
		this.horloge.demarrer();
		this.horloge.afficher();
		//Déplacement des fourmis dans la fourmilière
		for(Fourmiliere fourmiliere: lstFourmilieres){
			fourmiliere.deplacer();
		}	
		//Déplacement des animaux menacants
		for(AnimalMenacant animal: lstAnimauxMenacants){
			animal.deplacer();
			//System.out.println((animal.listeAnimaux.get(0)).point.x + " PointX ");			
		}	

		//Enlève les animaux menacants morts du terrain
		this.verifierMortalite();

		//Mise à jour du terrain au bout 1mn
		if(this.horloge.retourneMinutes() % 18 == 0 && this.horloge.retourneSecondes() == 10){
			miseAjour();
		}


		

	}

	/** Met à jour le terrain, en ajoutant ou en enlevant des éléments.
	*/
	public void miseAjour(){	
		//System.out.println("Les Animaux menacants " + quantite_AnimauxMenacants);
		if(quantite_AnimauxMenacants <= 2) {
			//System.out.println("Je viens d'ajouter une Oiseau");
			for(int i=0;i<2;i++){
				lstAnimauxMenacants.add(new Geai_Bleu(this));
				lstAnimauxMenacants.add(new Scorpion(this));
				if(random.nextInt(2) == 0){
					lstAnimauxMenacants.add(new Geai_Bleu(this));
				}else{
					lstAnimauxMenacants.add(new Scorpion(this));					
				}
			}				
		}
		this.nourritureEstDispo();		
		//System.out.println("La quantite_Nourritures " + quantite_Nourritures );
		if(quantite_Nourritures <= 10) {
			for(Cellule cellule: lstCellules) cellule.ajouterNourriture();
		}
		this.quantite_Nourritures = 0;
	
		if(quantite_Materiaux <= 5) {
			for(Cellule cellule: lstCellules) cellule.ajouterMateriau();
		}		
	}
	

	/** Met à jour l'attribut indiquant la quantité de nourritures intactes qui reste sur le terrain.
	*/
	void nourritureEstDispo(){
		for(Object obj: this.fenetre.retourneLstAffiche()){
			if(obj instanceof Nourriture){
				if( ((Nourriture)obj).touchee == false ) this.quantite_Nourritures++;
			}
		}
	}

	/** Enlève les Animaux Menacants de l'écran et les remplace par de la nourriture.
	*/
	void verifierMortalite(){		
		Vector<AnimalMenacant> lstDeMorts = new Vector<AnimalMenacant>();
		//Détection des animaux qui sont morts
		for(AnimalMenacant obj: this.lstAnimauxMenacants){
			if (!obj.vivant){		
				//System.out.println("Une Oiseau vient de mourir");		
				//liste d'animaux menacants morts
				lstDeMorts.add(obj);
				//si un animal menacant est mort, on le remplace par de la nourriture
					Nourriture bouffe = new Nourriture(new Point(obj.point.x,obj.point.y),this.fenetre );
					bouffe.addImage();					
			}
		}
		//On enlève les animaux de la liste des éléments à afficher et de la liste des animaux Menacants
		for(AnimalMenacant obj: lstDeMorts){
			this.fenetre.removeElement(obj);
			this.lstAnimauxMenacants.remove(obj);
			this.quantite_AnimauxMenacants--;
		}
	}

	/** Nourrit tous les animaux menaçants sur le terrain.
	*/
	void timerAlimentation(){
		for(AnimalMenacant menace: lstAnimauxMenacants) menace.manger();
	}

	
}