package dialogue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import inscriptions.Inscriptions;

public class Panneau extends JPanel
{
	private JLabel titre = new JLabel("Application de gestion de comp�tition");
	private static Inscriptions inscriptions;
	JTabbedPane tab = new JTabbedPane();
	
	
	public Panneau()
	{
		inscriptions = Inscriptions.getInscriptions();
		this.setLayout(new BorderLayout());
		Font police = new Font("Tahoma", Font.BOLD, 18);
		titre.setFont(police);
		titre.setHorizontalAlignment(JLabel.CENTER);
		tab.addTab("Gestion de personne", new PanneauPersonne());
		tab.addTab("Gestion d'équipe", new PanneauEquipe());
<<<<<<< HEAD
		tab.addTab("Gestion de compétition", new PanneauCompetition());
		tab.setBackground(Color.CYAN);
=======
		tab.addTab("Gestion de compétition", new JPanel());
		tab.setBackground(new Color(208,182,241));
>>>>>>> origin/master
		tab.setFont(police);
		this.add(tab,BorderLayout.CENTER);
	}
	
	public void paintComponent(Graphics g){
        g.setFont(new Font("Tahoma", Font.BOLD, 80));
        
    }

	
	public int centerText(Graphics g, Font font, String text)
	{
		return g.getFontMetrics(font).stringWidth(text)/2;
	}
	
	public static Inscriptions getInscriptions()
	{
		return inscriptions;
	}
}
