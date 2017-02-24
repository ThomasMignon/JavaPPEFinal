package dialogue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

public class Fenetre extends JFrame
{
	private Panneau panneau = new Panneau();
	public Fenetre()
	{   
		
	    this.setTitle("Gestionnaire de compétition");
	    this.setSize(800, 600);
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);               
	 
	    //Instanciation d'un objet JPanel
	    //Définition de sa couleur de fond        
	    //On prévient notre JFrame que notre JPanel sera son content pane
	    this.setContentPane(panneau);               
	    this.setVisible(true);
	  } 
}
