package dialogue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.FontMetrics;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Panneau extends JPanel
{
	public void paintComponent (Graphics g)
	{
		System.out.println("Test");
		Font font = new Font("Courier",Font.BOLD,25);
		g.drawLine(0,50,this.getWidth(),50);
		g.setFont(font);
		String titre = "Gestionnaire de compétition";
		g.drawString(titre, (this.getWidth()/2)-centerText(g,font,titre),40);
		JButton bouton = new JButton("Test");
		bouton.setPreferredSize(new Dimension(100,100));
		this.add(bouton);
	}
	
	public int centerText(Graphics g, Font font, String text)
	{
		return g.getFontMetrics(font).stringWidth(text)/2;
	}
}
