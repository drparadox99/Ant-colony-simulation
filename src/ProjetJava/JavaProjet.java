package ProjetJava;
//import ProjetJava.*;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Color;
import java.util.Random;
import java.awt.event.ActionListener;     
import java.awt.event.ActionEvent; 
import javax.swing.Timer;
import java.awt.Rectangle;
    
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author GOGOLI
 */
public class JavaProjet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	GUI_for_Displayable fenetre = new GUI_for_Displayable("Kos-Kos",900,600,Color.WHITE);       	     
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
