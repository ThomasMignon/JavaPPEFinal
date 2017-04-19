package dialogue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

public class Fenetre extends JFrame
{
	private Panneau panneau = new Panneau();
	
	public static final int WIDTH = 900;
	public static final int HEIGHT = 600;
	
	public Fenetre()
	{   
		
	    this.setTitle("Gestionnaire de compétition");
	    this.setSize(WIDTH, HEIGHT);
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setContentPane(panneau);
	    this.setVisible(true);
	 }
}
