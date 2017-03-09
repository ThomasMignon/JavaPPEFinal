package dialogue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FontMetrics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import inscriptions.Inscriptions;

public class Panneau extends JPanel
{
	private JPanel menu = new JPanel();
	private static PanneauPersonne panneauPersonne;
	private static JPanel panneauEquipe;
	private static JPanel panneauCompetition;
	private JLabel titre = new JLabel("Application de gestion de comp�tition");
	private JButton boutonPersonne = new JButton("Gestion de personne");
	private JButton boutonEquipe = new JButton("Gestion d'�quipe");
	private JButton boutonCompetition = new JButton("Gestion de comp�tition");
	private static Inscriptions inscriptions;
	private String panneauUse = "";
	
	public Panneau()
	{
		
		this.setLayout(new BorderLayout());
		
		Font police = new Font("Tahoma", Font.BOLD, 16);
		titre.setFont(police);
		titre.setHorizontalAlignment(JLabel.CENTER);
		this.add(titre,BorderLayout.NORTH);
		
		this.add(menu, BorderLayout.SOUTH);
		menu.setBackground(Color.BLACK);
		menu.add(boutonPersonne);
		menu.add(boutonEquipe);
		menu.add(boutonCompetition);
		boutonPersonne.addActionListener(new boutonPersonneListener());
		boutonEquipe.addActionListener(new boutonEquipeListener());
		boutonCompetition.addActionListener(new boutonCompetitionListener());
	}
	
	private void setPanneauPersonne()
	{
		switch(panneauUse)
		{
		 case "panneauEquipe":this.remove(panneauEquipe);break;
		 case "panneauCompetition":this.remove(panneauCompetition);break;
		 default:break;
		}
		panneauPersonne = new PanneauPersonne();
		panneauUse = "panneauPersonne";
		boutonPersonne.setEnabled(false);
		boutonEquipe.setEnabled(true);
		boutonCompetition.setEnabled(true);
		this.add(panneauPersonne,BorderLayout.CENTER);
		this.repaint();
	}
	
	private void setPanneauEquipe()
	{
		switch(panneauUse)
		{
		 case "panneauCompetition":this.remove(panneauCompetition);break;
		 case "panneauPersonne":this.remove(panneauPersonne);break;
		}
		panneauEquipe = new JPanel();
		panneauUse = "panneauEquipe";
		boutonPersonne.setEnabled(true);
		boutonEquipe.setEnabled(false);
		boutonCompetition.setEnabled(true);
		this.add(panneauEquipe);
		this.repaint();
	}
	
	private void setPanneauCompetition()
	{
		switch(panneauUse)
		{
		 case "panneauEquipe":this.remove(panneauEquipe);break;
		 case "panneauPersonne":this.remove(panneauPersonne);break;
		}
		panneauCompetition = new JPanel();
		panneauUse = "panneauCompetition";
		boutonPersonne.setEnabled(true);
		boutonEquipe.setEnabled(true);
		boutonCompetition.setEnabled(false);
		this.add(panneauCompetition);
		this.repaint();
	}
	
	public int centerText(Graphics g, Font font, String text)
	{
		return g.getFontMetrics(font).stringWidth(text)/2;
	}
	
	class boutonPersonneListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			titre.setText("Gestion des personnes");
			setPanneauPersonne();
		}
		
	}
	
	class boutonEquipeListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			titre.setText("Gestion des �quipes");
			setPanneauEquipe();
		}
		
	}
	
	class boutonCompetitionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			titre.setText("Gestion des comp�titions");
			setPanneauCompetition();
		}
		
	}
	
	public static Inscriptions getInscriptions()
	{
		inscriptions = Inscriptions.getInscriptions();
		return inscriptions;
	}
}
