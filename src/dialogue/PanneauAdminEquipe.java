package dialogue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dialogue.TableEquipe.boutonAjouterListener;
import dialogue.TableEquipe.boutonSupprimerListener;
import dialogue.TableEquipe.editFieldListener;
import inscriptions.Equipe;
import inscriptions.Personne;

public class PanneauAdminEquipe extends JPanel {
	
	Equipe equipe;
	private SortedSet<Personne> membres = new TreeSet<>();
	JLabel nomEquipe = new JLabel();
	JPanel panelMembres = new JPanel();
	JComboBox listMembres = new JComboBox();
	JPanel panelComboMembres = new JPanel();
	JLabel labelTitre = new JLabel("Liste des membres de l'équipe : ");
	private Dimension taille = new Dimension((int) (Fenetre.WIDTH * 0.45),(int) (Fenetre.HEIGHT * 0.80));
	private Dimension size = new Dimension((int) (Fenetre.WIDTH * 0.45),45);
	
	public PanneauAdminEquipe(Equipe e)
	{
		super();
		nomEquipe.setPreferredSize(new Dimension((int) taille.getWidth(),40));
		nomEquipe.setBorder(BorderFactory.createTitledBorder("Votre équipe sélectionné :"));
		nomEquipe.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(nomEquipe);
		this.add(panelMembres);
		this.setVisible(false);
		this.setPreferredSize(taille);
	}
	
	public void setTailleAll()
	{
		panelComboMembres.setPreferredSize(size);
		labelTitre.setPreferredSize(size);
		panelMembres.setPreferredSize(new Dimension((int) (Fenetre.WIDTH * 0.45),150));
	}
	
	public JLabel getNomEquipe() {
		return nomEquipe;
	}
	
	public void setMembres(Equipe e)
	{
		this.membres = e.getMembres();
	}
	
	public void remplirMembres()
	{
		panelMembres.removeAll();
		panelMembres.repaint();
		setTailleAll();
		listMembres.removeAllItems();
		for(Personne p : membres){
    		listMembres.addItem(p.toString());
        }
		panelComboMembres.add(labelTitre);
		labelTitre.setPreferredSize(new Dimension((int) (Fenetre.WIDTH * 0.25),20));
		panelComboMembres.add(listMembres);
		panelMembres.add(panelComboMembres);
	}

	public void setNomEquipe(String text) {
		this.nomEquipe.setText(text);
	}	
	
	public void setAll(Equipe equipe)
	{
		this.setNomEquipe(equipe.getNom());
		this.setMembres(equipe);
		this.remplirMembres();
	}
}
