package ProjetJava;

/**Cette classe modélise l'appariel horaire qui déclenche les évènements sur le terrain
*/
public class Horloge {

    private int secondes;
    private int minutes;
    private int heures;
    private int jours;
    private int mois;
    private int annees;
    private int semaines;

    /**Le constructeur initialisant l'horloge.
    */
    public Horloge(){
        this.secondes = 0;
        this.minutes = 0;
        this.heures = 0;
        this.jours = 0;
        this.mois = 0;
        this.annees = 0;
        this.semaines =0;        
    }

    /**Demarre l'horloge.
    */
    public void demarrer(){
    	this.secondes += 1;
    	this.minutes = this.minutes + this.secondes/60;
    	this.heures = this.heures + this.minutes/60;
    	this.jours = this.jours + this.heures/24;
    	this.mois = this.mois + this.jours/30;
        this.semaines = this.semaines + this.jours/7;
    	this.annees = this.annees + this.mois/12;
    	this.secondes = this.secondes%60;
    	this.minutes = this.minutes%60;
    	this.heures = this.heures%24;
    	this.jours = this.jours%30;
    	this.mois = this.mois%12;    	
    }
    /**Affiche l'horloge.
    */
    public void afficher(){
    	System.out.println("L'Horloge indique: "+ this.heures+"H"+this.minutes + " "+ this.secondes+" secondes");
    	System.out.println("L'Horloge indique: "+ this.jours + " jours et "+ this.semaines+ " semaines");
    }
    /**Retourne les secondes écroulées depuis la création du terrain.
        @return Renvoie les secondes écroulées.
    */
    public int retourneSecondes(){
        return this.secondes;
    }
    /**Retourne les minutes écroulées depuis la création du terrain.
        @return Renvoie les minutes écroulées.
    */
    public int retourneMinutes(){
        return this.minutes;
    }
    /**Retourne les heures écroulées depuis la création du terrain.
        @return Renvoie les heures écroulées.    
    */
    public int retourneHeures(){
        return this.heures;
    }
    /**Retourne les années écroulées depuis la création du terrain.
        @return Renvoie les années écroulées.    
    */
    public int retourneAnnees(){
        return this.annees;
    }
    /**Retourne les mois passés depuis la création du terrain.
        @return Renvoie les mois passés. 
    */
    public int retourneMois(){
        return this.mois;
    }
    /**Retourne les semaines écroulées depuis la création du terrain.
        @return Renvoie les semaines écroulées.    
    */
    public int retourneSemaines(){
        return this.semaines;
    }
   
}
