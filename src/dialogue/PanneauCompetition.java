package dialogue;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
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
import javax.swing.table.DefaultTableModel;

import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

@SuppressWarnings("serial")
public class PanneauCompetition extends JPanel {
	
	private JComboBox<String> comboPersonne = new JComboBox<>();
	private JComboBox<String> comboEquipe = new JComboBox<>();
	private JComboBox<String> comboEquipeDispo = new JComboBox<>();
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
	private JPanel panelAfficherCompetitionsEquipe = new JPanel();
	private JPanel panelAfficherPersonneEquipe = new JPanel();
	private JPanel panelTableauEquipe = new JPanel();
	
	private JTextField nomAjoutField = new JTextField();
	private JTextField prenomAjoutField = new JTextField();
	private JTextField mailAjoutField = new JTextField();
	
	private JTextField nomField = new JTextField();
	private JTextField dateField = new JTextField();
	

	private JButton boutonAjout = new JButton("Ajouter");
	private JButton boutonEdite = new JButton("Editer");
	private JButton boutonAjouterMembre = new JButton("Ajouter ce membre");
	private JButton boutonSupprMembre = new JButton("Supprimer de ce membre");
	private JButton boutonSupprCompetition = new JButton("Supprimer de cette compétition");
	private JButton boutonAjouteCompetition = new JButton("Ajouter à cette compétition");
	private JButton boutonSupprEquipe = new JButton("Supprimer cette équipe");
	
	private Dimension tailleEdit = new Dimension(Fenetre.WIDTH * 80 / 100, Fenetre.HEIGHT * 12 / 100 );
	private Dimension tailleListEquipe = new Dimension(Fenetre.WIDTH * 80 / 100, Fenetre.HEIGHT * 12 / 100 );
	private Dimension tailleListCompetition = new Dimension(Fenetre.WIDTH * 40 / 100-2, Fenetre.HEIGHT * 35 / 100 );
	private Dimension tailleListPersonne = new Dimension(Fenetre.WIDTH * 40 / 100 -2, Fenetre.HEIGHT * 35 / 100 );
	private Container panelAfficherEquipePersonne;
	
	public PanneauCompetition()
	{
		// Instantiation

		boutonAjout.setEnabled(false);
		boutonEdite.setEnabled(false);

		//Panneau afficherPersonne
//		setListener();
		
		setPanneauAfficherEquipe();
		
		// Ajouter une personne
		setPanneauAjouteEquipe();
	}

	private void setPanneauAfficherEquipe() {
		// Menu deroulant pour selectionner personne
		setComboEquipe();

		// Afficher une personne séléctionner, éditer
		setAfficherEquipe();
		 
		//Afficher les équipes d'une personne séléctionner 
		setAfficherCompetitionsEquipes();
	
		//Afficher les compétitions d'une personne séléctionner
		setAfficherEquipesPersonne();
//		setListener();
		
		//Bouton pour supprimer la personne séléctionner
		this.add(boutonSupprEquipe);
		
	}
	
	private void setAfficherEquipesPersonne() {
		// TODO Auto-generated method stub
		
	}

	private void setAfficherTableauEquipe()
	{
		String[] donnees = {"test"};
		DefaultTableModel tableModel = new DefaultTableModel(donnees, 0);
		tableModel.addRow(donnees);
		//tableModel.isCellEditable(1, 1);
		for (Equipe e : inscriptions.getEquipes()) 
		{
			Object[] objs = {e.getNom()};
			tableModel.addRow(objs);
		}
		JTable table = new JTable(tableModel);
		panelTableauEquipe.add(table);

		this.add(panelTableauEquipe);
	}
//liste des équipes
	private void setAfficherPersonneEquipe(Component boutonAjouteEquipe) {

		panelAfficherEquipePersonne.add(new JLabel("Ses équipes : "));
		comboEquipe.setPreferredSize(new Dimension(200, 20));
		
		panelAfficherEquipePersonne.add(comboEquipe);
		panelAfficherEquipePersonne.add(boutonSupprEquipe);
		
		panelAfficherEquipePersonne.add(new JLabel("Equipe(s) disponible(s)"));
		comboEquipeDispo.setPreferredSize(new Dimension(200,20));
		panelAfficherEquipePersonne.add(comboEquipeDispo);
		
		panelAfficherEquipePersonne.add(boutonAjouteEquipe);
		panelAfficherEquipePersonne.setPreferredSize(tailleListEquipe);
		panelAfficherPersonneEquipe.setBorder(BorderFactory.createTitledBorder("Liste des Equipes"));
		this.add(panelAfficherPersonneEquipe);
		
	}

	private void setAfficherCompetitionsEquipes() {
		panelAfficherCompetitionsEquipe.add(new JLabel("Les personnes : "));
		comboCompetition.setPreferredSize(new Dimension(200, 20));
		
		panelAfficherCompetitionsEquipe.add(comboCompetition);
		panelAfficherCompetitionsEquipe.add(boutonSupprCompetition);
		
		panelAfficherCompetitionsEquipe.add(new JLabel("Equipe(s) disponible(s)"));
		comboCompetitionDispo.setPreferredSize(new Dimension(100,20));
		panelAfficherCompetitionsEquipe.add(comboCompetitionDispo);
		
		panelAfficherCompetitionsEquipe.add(boutonAjouteCompetition);
		panelAfficherCompetitionsEquipe.setPreferredSize(tailleListCompetition);
		
		panelAfficherCompetitionsEquipe.setBorder(BorderFactory.createTitledBorder("Liste des Personnes"));
		this.add(panelAfficherCompetitionsEquipe);
		
	}

	private void setAfficherEquipe(){
		panelAfficherEquipe.add(new JLabel("Nom : "));
		nomField.setPreferredSize(new Dimension(130, 20));
		panelAfficherEquipe.add(nomField);
		panelAfficherEquipe.add(new JLabel("Date : "));
		nomField.setPreferredSize(new Dimension(130, 20));
		nomField.setPreferredSize(new Dimension(130, 20));
		dateField.setPreferredSize(new Dimension(130, 20));
		panelAfficherEquipe.add(dateField);
		
		panelAfficherEquipe.add(Box.createHorizontalStrut(40));
		
		boutonEdite.setPreferredSize(new Dimension(80,20));
		panelAfficherEquipe.add(boutonEdite);
		
		panelAfficherEquipe.setBorder(BorderFactory.createTitledBorder("Informations  de competition"));
		panelAfficherEquipe.setPreferredSize(tailleEdit);
		
		this.add(panelAfficherEquipe);	
	}
	
	private void afficherMembresEquipe(Equipe equipe)
	{
		if(equipe.getMembres().size() > 0)
			equipe.getMembres();
	}

	private void setComboEquipe() {
		for (Competition c : inscriptions.getCompetitions()) 
		{
			comboEquipe.addItem(c.getNom());
		}
		comboEquipe.setPreferredSize(new Dimension(300, 20));
		panelSelectEquipe.setBorder(BorderFactory.createTitledBorder("Liste des Competitions"));
		JLabel labelSelectEquipe = new JLabel("Sélectionner une competition à administrer :");
		labelSelectEquipe.setPreferredSize(new Dimension(300,20));
		//comboEquipe.setBackground(Color.WHITE);
		panelSelectEquipe.add(labelSelectEquipe);
		panelSelectEquipe.add(comboEquipe);
		panelSelectEquipe.setPreferredSize(tailleListEquipe);
		this.add(panelSelectEquipe);
		
	}
	private void setPanneauAjouteEquipe(){
		ajouteEquipe.setBackground(Color.WHITE);
		nomAjoutField.setPreferredSize(new Dimension(130, 20));
		ajouteEquipe.add(new JLabel("Nom : "));
		ajouteEquipe.add(nomAjoutField);
		ajouteEquipe.add(boutonAjout);
		this.add(ajouteEquipe);
	}
	private void setPanneauAfficherEquipe(String select) 
	{
		for (Competition c : inscriptions.getCompetitions())
		{
			String nomPrenomSelect = c.getNom(); 
			if ((c.getNom()).equals(select)) 
			{
				String maCompetition = c.getNom();
				System.out.println(maCompetition);

				System.out.println(c+" lol");
				comboCompetition.removeAllItems();
				nomField.setText(maCompetition);
				
			}
		}
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
	
}