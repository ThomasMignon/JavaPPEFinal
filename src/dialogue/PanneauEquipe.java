package dialogue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dialogue.PanneauPersonne.ajoutFieldListener;
import dialogue.PanneauPersonne.boutonAjouteCompetitionListener;
import dialogue.PanneauPersonne.boutonAjouteEquipeListener;
import dialogue.PanneauPersonne.boutonAjouteListener;
import dialogue.PanneauPersonne.boutonEditListener;
import dialogue.PanneauPersonne.boutonSupprCompetitionListener;
import dialogue.PanneauPersonne.boutonSupprEquipeListener;
import dialogue.PanneauPersonne.boutonSupprPersonneListener;
import dialogue.PanneauPersonne.comboCompetitionDispoListener;
import dialogue.PanneauPersonne.comboCompetitionListener;
import dialogue.PanneauPersonne.comboEquipeDispoListener;
import dialogue.PanneauPersonne.comboEquipeListener;
import dialogue.PanneauPersonne.comboItemListener;
import dialogue.PanneauPersonne.fieldListener;
import dialogue.PanneauAdminEquipe;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class PanneauEquipe extends JPanel {

	private JComboBox<String> comboPersonne = new JComboBox<>();
	private JComboBox<String> comboEquipe = new JComboBox<>();
	private JComboBox<String> comboPersonneDispo = new JComboBox<>();
	private JComboBox<String> comboCompetition = new JComboBox<>();
	private JComboBox<String> comboCompetitionDispo = new JComboBox<>();
	
	private Inscriptions inscriptions = Panneau.getInscriptions();
	
	Personne selectPersonne;
	Personne selectPersonneDispo;
	Equipe selectEquipe;
	Competition selectCompetition;
	Competition selectCompetitionDispo;
	
	private JPanel ajouteEquipe = new JPanel();
	private JPanel panelSelectEquipe = new JPanel();
	private JPanel panelAfficherEquipe = new JPanel();
	private PanneauAdminEquipe panelAdminEquipe;
	private JPanel panelTableauEquipe = new JPanel();
	
	private JLabel nomEquipe = new JLabel();
	
	private JTextField nomAjoutField = new JTextField();
	private JTextField prenomAjoutField = new JTextField();
	private JTextField mailAjoutField = new JTextField();
	
	private JTextField nomField = new JTextField();

	private JButton boutonAjout = new JButton("Ajouter");
	private JButton boutonEdit  = new JButton("Editer");
	private JButton boutonAjouterMembre = new JButton("Ajouter ce membre");
	private JButton boutonSupprMembre = new JButton("Supprimer de ce membre");
	private JButton boutonSupprCompetition = new JButton("Supprimer de cette compétition");
	private JButton boutonAjouteCompetition = new JButton("Ajouter à cette compétition");
	private JButton boutonSupprEquipe = new JButton("Supprimer cette équipe");
	
	private Dimension taillePanelAjout = new Dimension((int) (Fenetre.WIDTH * 0.45),(int) (Fenetre.HEIGHT * 0.80));
	
	public PanneauEquipe()
	{	
		// Menu deroulant pour selectionner personne
		setTableEquipe();
		 
		//Afficher les équipes d'une personne séléctionner 
		setAdminEquipe();
	}
	
	public PanneauAdminEquipe getPanelAdminEquipe()
	{
		return panelAdminEquipe;
	}
	
	public void setPanelAdminEquipe(PanneauAdminEquipe p)
	{
		this.panelAdminEquipe = p;
	}
	
	public Equipe getSelectEquipe()
	{
		return selectEquipe;
	}
	
	public JLabel getLabelNomEquipe()
	{
		return nomEquipe;
	}
	
	public void setLabelNomEquipe(String text)
	{
		nomEquipe.setText(text);
	}
	
	public void setSelectEquipe(Equipe e)
	{
		this.selectEquipe = e;
	}
	
	private JPanel setAfficherTableauEquipe()
	{
		TableEquipe table = new TableEquipe(this);
		panelTableauEquipe.add(table);
		return panelTableauEquipe;
	}
	public boolean isCellEditable(){  
        return false;  
    }

	private void setAdminEquipe() {
		
		panelAdminEquipe = new PanneauAdminEquipe(selectEquipe);
		this.setPanelAdminEquipe(panelAdminEquipe);
		this.add(panelAdminEquipe);
		
		
	}

	
	private void afficherMembresEquipe(Equipe equipe)
	{
		if(equipe.getMembres().size() > 0)
			equipe.getMembres();
	}

	private void setTableEquipe() {
		panelSelectEquipe.add(setAfficherTableauEquipe());
		this.add(panelSelectEquipe);		
	}

	private void setPanneauAjouteEquipe() {
		ajouteEquipe.setBackground(Color.WHITE);
		nomAjoutField.setPreferredSize(new Dimension(130, 20));
		ajouteEquipe.add(new JLabel("Nom : "));
		ajouteEquipe.add(nomAjoutField);
		ajouteEquipe.add(boutonAjout);
		this.add(ajouteEquipe);
	}
	
	private void setPanneauAfficherEquipe(String select) 
	{
		for (Equipe eq : inscriptions.getEquipes()) 
		{
			if ((eq.getNom()).equals(select)) 
			{
				String nomEquipe = eq.getNom();
				System.out.println(nomEquipe);

				System.out.println(eq+" lol");
				comboEquipe.removeAllItems();
				nomField.setText(nomEquipe);
				
			}
		}
	}
	
	private void setListener()
	{
		nomAjoutField.addKeyListener(new ajoutFieldListener());
		comboEquipe.addActionListener(new comboItemListener());
	}
	
	private void listEquipePersonne(Equipe eq)
	{
		if(!eq.getMembres().isEmpty())
		{
			for(Personne p : eq.getMembres())
			{
				comboPersonne.addItem(p.getNom());
			}
		}
		comboPersonne.addItem("Exemple");
	}
	
	

	private void listEquipeSelectPersonne(Equipe e) {
		// TODO Auto-generated method stub
		
	}
	
	class comboItemListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			setPanneauAfficherEquipe(comboEquipe.getSelectedItem().toString());
		}
	}
	
	class fieldListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) 
		{
			
		}

		@Override
		public void keyReleased(KeyEvent e) 
		{
			
		}

		@Override
		public void keyTyped(KeyEvent e) 
		{
			
		}
		
	}
	
	class ajoutFieldListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent arg0) 
		{
		
		}

		@Override
		public void keyReleased(KeyEvent arg0) 
		{
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) 
		{
			
		}
		
	}
	
	public PanneauEquipe getPanneauEquipe()
	{
		return this;
	}
}
