package dialogue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import inscriptions.Inscriptions;

public class Panneau extends JPanel
{
	private JLabel titre = new JLabel("Application de gestion de compétition");
	private static Inscriptions inscriptions;
	JTabbedPane tab = new JTabbedPane();
	
	
	public Panneau()
	{
		
		this.setLayout(new BorderLayout());
		
		Font police = new Font("Tahoma", Font.BOLD, 16);
		titre.setFont(police);
		titre.setHorizontalAlignment(JLabel.CENTER);
		tab.addTab("Gestion de personne", new PanneauPersonne());
		tab.addTab("Gestion d'équipe", new JPanel());
		tab.addTab("Gestion de compétition", new JPanel());
		this.add(tab,BorderLayout.CENTER);
	}
	

	
	public int centerText(Graphics g, Font font, String text)
	{
		return g.getFontMetrics(font).stringWidth(text)/2;
	}
	
	public static Inscriptions getInscriptions()
	{
		inscriptions = Inscriptions.getInscriptions();
		return inscriptions;
	}
}
