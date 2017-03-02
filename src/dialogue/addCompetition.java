package dialogue;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.awt.FontMetrics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.*;

public class addCompetition extends JPanel
{
	
	private String[] options = {
			"Inscription",
			"Equipe",
			"Competition",
			"Quitter"
	};
	private boolean sendData;
	private JLabel nomLabel,dateLabel,logo;
	private JRadioButton enequipe, ensolo;
	private JTextField nom, date;
	  
	  
	
	public addCompetition()
	{
		this.setPreferredSize(new Dimension(720, 360));
	  // Logo
		logo = new JLabel(new ImageIcon("images/logo.jpg"));
	    JPanel panLogo = new JPanel();
	    panLogo.setPreferredSize(new Dimension(720, 160));
	    panLogo.setBackground(Color.white);
	    panLogo.setLayout(new BorderLayout());
	    panLogo.add(logo);
	    
	    this.add(panLogo, BorderLayout.NORTH);
	    
	    
	  //Le nom
	    JPanel panNom = new JPanel();
	    panNom.setBackground(Color.WHITE);
	    panNom.setPreferredSize(new Dimension(220, 60));
	    nom = new JTextField();
	    nom.setPreferredSize(new Dimension(100, 25));
	    panNom.setBorder(BorderFactory.createTitledBorder("Nom de la competition"));
	    nomLabel = new JLabel("Saisir un nom :");
	    panNom.add(nomLabel);
	    panNom.add(nom);
	    
	    this.add(panNom);
	    
	  //Enequipe
	    JPanel panEquipe = new JPanel();
	    panEquipe.setBackground(Color.white);
	    panEquipe.setBorder(BorderFactory.createTitledBorder("Compétition en équipe ?"));
	    panEquipe.setPreferredSize(new Dimension(240, 60));
	    enequipe = new JRadioButton("En equipe");
	    enequipe.setSelected(true);
	    ensolo = new JRadioButton("En Solo");
	    ButtonGroup bg = new ButtonGroup();
	    bg.add(ensolo);
	    bg.add(enequipe);
	    panEquipe.add(ensolo);
	    panEquipe.add(enequipe);
	    
	    this.add(panEquipe);
	    
	  //La date
	    JPanel panDate = new JPanel();
	    panDate.setBackground(Color.white);
	    panDate.setPreferredSize(new Dimension(220, 60));
	    panDate.setBorder(BorderFactory.createTitledBorder("Date de la compétition"));
	    dateLabel = new JLabel("Date : ");
	    date = new JTextField(LocalDate.now().toString());
	    date.setPreferredSize(new Dimension(90, 25));
	    panDate.add(dateLabel);
	    panDate.add(date);
	    
	    this.add(panDate);
	    
	    JPanel panValid = new JPanel();
	    panValid.setPreferredSize(new Dimension(820, 860));
	    
	    panValid.add(new JButton("Valider"), BorderLayout.SOUTH);
	    panValid.add(new JButton("Retour"), BorderLayout.SOUTH);
	    
	    this.add(panValid, BorderLayout.SOUTH);
	    
	    
	    
	}
	
	
//	public void paintComponent (Graphics g)
//	{
//		Font font = new Font("Courier",Font.BOLD,25);
//		g.drawLine(0,50,this.getWidth(),50);
//		g.setFont(font);
//		String titre = "Gestionnaire de compétition";
//		g.drawString(titre, (this.getWidth()/2)-centerText(g,font,titre),40);
//		for(int i = 0; i < options.length;i++)
//		{
//			g.drawString(options[i], (this.getWidth()/2)-centerText(g,font,options[i]), 100 + i * 50);
//		}
//		
//	
//		
//	}
//	
//	public int centerText(Graphics g, Font font, String text)
//	{
//		return g.getFontMetrics(font).stringWidth(text)/2;
//	}
}
