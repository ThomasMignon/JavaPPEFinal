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
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setContentPane(panneau);
	    this.setVisible(true);
	 }
}
