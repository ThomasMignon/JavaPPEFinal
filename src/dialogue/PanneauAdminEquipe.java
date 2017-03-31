package dialogue;

import java.awt.Component;
import java.awt.Dimension;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
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
	private Dimension taille = new Dimension((int) (Fenetre.WIDTH * 0.45),(int) (Fenetre.HEIGHT * 0.80));
	
	public PanneauAdminEquipe(Equipe e)
	{
		super();
		nomEquipe.setPreferredSize(new Dimension((int) taille.getWidth(),40));
		nomEquipe.setBorder(BorderFactory.createTitledBorder("Votre équipe sélectionné :"));
		this.add(nomEquipe);
		this.add(panelMembres);
		this.setVisible(false);
		this.setPreferredSize(taille);
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
		listMembres.removeAll();
		listMembres.repaint();
		
		for(Personne p : membres){
    		listMembres.addItem(p.toString());
        }
		panelMembres.add(listMembres);
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
