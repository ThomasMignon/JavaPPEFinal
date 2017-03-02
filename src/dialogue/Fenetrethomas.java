package dialogue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.Border;

public class Fenetrethomas extends JFrame
{
	
	public Fenetrethomas()
	{   
		
	    this.setTitle("Gestionnaire de compétition");
	    this.setSize(800, 400);
	    this.setResizable(true);
	    this.setLocationRelativeTo(null);  
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    
	    initComponent();
	}
	
	public void initComponent()
	{
		addCompetition panel = new addCompetition();
		this.setBackground(Color.GRAY);
	    this.setContentPane(panel);
	    this.setVisible(true);
	    
	}
}
