package ProjetJava;

//package display;

import ProjetJava.Etre_nonVivant.Pheromone;
import ProjetJava.Etre_nonVivant.Materiau;
import ProjetJava.Etre_nonVivant.Nourriture;
import ProjetJava.Etre_Vivant.Animal;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.util.Vector;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.ImageIcon;
import java.util.Vector;
import java.util.Iterator;
/** Fen&ecirc;tre pour l'affichage d'objets de type Affichable et l'interaction souris avec ces objets. L'affichage peut se faire sur un fond color&eacute; ou sur une image. */
public class GUI_for_Displayable extends JFrame implements MouseListener{

	private int offset = 4;
	private float fontSize = 10.5f;
	private Graphic p;
	private Color bg = null;
	private BufferedImage img = null;
	private Vector<Displayable> elements;
	private Vector<MouseEvent> clics;
	private JTextField jtf;
    private Vector<Object> lstAffiche ;
    private Vector<Object> effAffiche ;
    public static  int fenetreWidth ;
    public static int fenetreHeight;

	/** Cr&eacute;e une JFrame permettant d'afficher sur la couleur de fond sp&eacute;cifi&eacute;e des Affichable ayant des coordonn&eacute;es dans [0..largeur,0..hauteur]. */
	public GUI_for_Displayable(String title, int width, int height, Color bg){
		super(title);
		this.elements = new Vector<Displayable>();
		this.clics = new Vector<MouseEvent>();
		this.bg = bg;
		this.setLayout(new BorderLayout());
		this.p = new Graphic();
		this.p.addMouseListener(this);
		this.getContentPane().add(this.p,BorderLayout.CENTER);
		jtf = new JTextField(20);
		this.getContentPane().add(jtf,BorderLayout.SOUTH);
		this.setSize(width,height);
		this.setResizable(false);
		this.setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth())/2,(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight())/2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.lstAffiche = new Vector<Object>();
		this.effAffiche = new Vector<Object>();
		this.fenetreWidth = width;
		this.fenetreHeight = height;
	}

	/** Cr&eacute;e une JFrame permettant d'afficher des Affichable sur l'image de fond sp&eacute;cifi&eacute;e.
	  * @throws IOException si le fichier image sp&eacute;cifi&eacute; n'existe pas ou n'est pas une image lisible. */
	public GUI_for_Displayable(String title, String imageFile) throws IOException{
		super(title);
		this.elements = new Vector<Displayable>();
		this.clics = new Vector<MouseEvent>();
		this.bg = Color.BLACK;
		this.img = ImageIO.read(new File(imageFile));
		this.setLayout(new BorderLayout());
		this.p = new Graphic();
		this.p.addMouseListener(this);
		this.getContentPane().add(this.p,BorderLayout.CENTER);
		jtf = new JTextField(20);
		this.getContentPane().add(jtf,BorderLayout.SOUTH);
		this.setSize(img.getWidth(),img.getHeight()+48);
		this.setResizable(false);
		this.setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth())/2,(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight())/2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/** Ajoute un Affichable &agrave; l'interface et rafraichit l'affichage. */
	public void addDisplayable(Displayable d){
		this.elements.add(d);
		this.p.repaint();
	}

	public void addElement(Object o){
		this.lstAffiche.add(o);
	}
	public void removeElement(Object o){
		//this.lstAffiche.remove(o);
		this.effAffiche.add(o);
	}

	public Vector<Object> retourneLstAffiche(){
		return this.lstAffiche;
	}

	public int retourneLargeur(){
		return this.fenetreWidth;
	}

	public int retourneLongeur(){
		return this.fenetreHeight;
	}

	/** Retire un Affichable de l'interface et rafraichit l'affichage. */
	public boolean removeDisplayable(Displayable d){
		if(this.elements.remove(d)){
			this.p.repaint();
			return true;
		}
		else return false;
	}

	public void mouseClicked(MouseEvent e){
		this.clics.add(e);
	}

	public void mouseEntered(MouseEvent e){}

	public void mouseExited(MouseEvent e){}

	public void mousePressed(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}

	/** Retourne un MouseEvent correspondant au dernier clic de l'utilisateur, ou null s'il n'y a plus de clic &agrave; renvoyer. */
	public MouseEvent getClic(){
		if(this.clics.isEmpty()) return null;
		else return this.clics.remove(0);
	}

	private class Graphic extends JPanel{
		public void paint(Graphics gr){
			if(GUI_for_Displayable.this.bg != null){
				gr.setColor(GUI_for_Displayable.this.bg);
				gr.fillRect(0,0,this.getWidth(),this.getHeight());
			}
			if(GUI_for_Displayable.this.img != null){
				gr.drawImage(GUI_for_Displayable.this.img,0,0,this);
			}
			for(Displayable d:GUI_for_Displayable.this.elements){
				gr.setColor(d.getColor());
				Shape sh = d.getShape();
				if(sh != null){
				if(sh instanceof java.awt.geom.Line2D || sh instanceof java.awt.geom.Path2D){
					((Graphics2D) gr).setStroke(new BasicStroke(5.0f));
					((Graphics2D) gr).draw(sh);
				}
				else ((Graphics2D) gr).fill(sh);
			    }
			    /*
				gr.setColor(Color.BLACK);
				Font f = gr.getFont().deriveFont(Font.BOLD);
				f = f.deriveFont(fontSize+2);
				gr.setFont(f);
				Point p = d.getStringPosition();
				String[] s = d.getString().split("\n");
				*/
				/*int max = 0;
				for(int i=1;i<s.length;i++){if(s[i].length()>s[max].length()) max = i;}
				Rectangle2D r2d = f.getStringBounds(s[max],((Graphics2D) gr).getFontRenderContext());
				gr.setColor(Color.WHITE);
				gr.fillRect(p.x,p.y,(int) r2d.getWidth(),(int) (r2d.getHeight()*s.length+((s.length-1)*GUI_for_Displayable.this.offset)));
				gr.setColor(Color.BLACK);*/
				//for(int i=0;i<s.length;i++) gr.drawString(s[i],p.x,p.y+(i+1)*GUI_for_Displayable.this.offset);
			}
				/*
				Iterator<Object> iter = lstAffiche.iterator();
				while (iter.hasNext()) {
				    Object obj = iter.next();
				    if(obj instanceof Animal) ((Animal)obj).draw(gr,this);
					if(obj instanceof Pheromone) ((Pheromone)obj).draw(gr,this);
					if(obj instanceof Nourriture) ((Nourriture)obj).draw(gr,this);
					if(obj instanceof Materiau) ((Materiau)obj).draw(gr,this);
				}
				*/

				int size = lstAffiche.size();
				for(int i= 0;i<size;i++){
					Object obj = lstAffiche.get(i);
					if(obj instanceof Animal) ((Animal)obj).draw(gr,this);
					if(obj instanceof Pheromone) ((Pheromone)obj).draw(gr,this);
					if(obj instanceof Nourriture) ((Nourriture)obj).draw(gr,this);
					if(obj instanceof Materiau) ((Materiau)obj).draw(gr,this);
				}
				size = effAffiche.size();
				for(int i = 0; i <size ;i++){
					Object obj = effAffiche.get(i);
					if(obj != null){
						lstAffiche.remove(obj);
					}
				}
				/*
				for(Object obj:effAffiche){
					lstAffiche.remove(obj);
				}		*/
				effAffiche.clear();

		}
	}

	/** Affiche le message m dans une boite de dialogue. */
	public void displayMessage(String m){
		JOptionPane.showMessageDialog(this,m);
	}

	/** Affiche le texte s dans la zone de texte en bas de la fen&ecirc;tre. */
	public void setBottomFieldText(String s){
		this.jtf.setText(s);
	}

}
