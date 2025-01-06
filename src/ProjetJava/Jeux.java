package ProjetJava;
import ProjetJava.*;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Color;
import java.util.Random;
import java.awt.event.ActionListener;     
import java.awt.event.ActionEvent; 
import javax.swing.Timer;
import java.awt.Rectangle;
    
/** Cette class représente la classe qui lance la simulation
*/
public class Jeux {

    public static void main(String[] args) { 
	    GUI_for_Displayable fenetre = new GUI_for_Displayable("Ant Colony Simulation",900,600,Color.WHITE);       	     
        Terrain terrain = new Terrain(null,Color.GREEN,fenetre); 
        fenetre.addDisplayable(terrain);
        terrain.getCellules(fenetre);

         terrain.peupler(fenetre);           
            try {
            while(true){    		
            fenetre.repaint();      		  		             
            terrain.simulation();           
          	 Thread.sleep(60) ;	            
        	}
        } catch (InterruptedException e) { System.out.println("Erreur");}
  
    }	
}


