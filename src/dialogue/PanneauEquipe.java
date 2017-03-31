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
	private JPanel panelAfficherCompetitionsEquipe = new JPanel();
	private JPanel panelAfficherPersonneEquipe = new JPanel();
	private JPanel panelTableauEquipe = new JPanel();
	
	private JTextField nomAjoutField = new JTextField();
	private JTextField prenomAjoutField = new JTextField();
	private JTextField mailAjoutField = new JTextField();
	
	private JTextField nomField = new JTextField();

	private JButton boutonAjout = new JButton("Ajouter");
	private JButton boutonEdit  = new JButton("Editer");
	private JButton boutonAjouterMembre = new JButton("Ajouter ce membre");
	private JButton boutonSupprMembre = new JButton("Supprimer de ce membre");
	private JButton boutonSupprCompetition = new JButton("Supprimer de cette comp�tition");
	private JButton boutonAjouteCompetition = new JButton("Ajouter � cette comp�tition");
	private JButton boutonSupprEquipe = new JButton("Supprimer cette �quipe");
	
	private Dimension tailleEdit = new Dimension(Fenetre.WIDTH * 80 / 100, Fenetre.HEIGHT * 12 / 100 );
	private Dimension tailleListEquipe = new Dimension(Fenetre.WIDTH * 80 / 100, Fenetre.HEIGHT * 70 / 100 );
	private Dimension tailleListCompetition = new Dimension(Fenetre.WIDTH * 40 / 100-2, Fenetre.HEIGHT * 35 / 100 );
	private Dimension tailleListPersonne = new Dimension(Fenetre.WIDTH * 40 / 100 -2, Fenetre.HEIGHT * 35 / 100 );
	
	public PanneauEquipe()
	{
		// Instantiation
		boutonAjout.setEnabled(false);
		
		setPanneauAfficherEquipe();
		
		// Ajouter une personne
		setPanneauAjouteEquipe();
	}

	private void setPanneauAfficherEquipe() {
		// Menu deroulant pour selectionner personne
		setComboEquipe();
		 
		//Afficher les �quipes d'une personne s�l�ctionner 
		setAfficherCompetitionsEquipes();
	
		//Afficher les comp�titions d'une personne s�l�ctionner
		setAfficherPersonneEquipe();
		
		setAfficherTableauEquipe();
		
		
		//Bouton pour supprimer la personne s�l�ctionner
		this.add(boutonSupprEquipe);
		
	}
	
	private JPanel setAfficherTableauEquipe()
	{
	
		TableEquipe table = new TableEquipe();
		panelTableauEquipe.add(table);
		return panelTableauEquipe;
	}
	public boolean isCellEditable(){  
        return false;  
    }
	
	class ListenerTableEquipe implements ListSelectionListener
	{
		private int id;
		
		
		@Override
		public void valueChanged(ListSelectionEvent listSelectionEvent){
	        if (listSelectionEvent.getValueIsAdjusting())
	            return;
	        ListSelectionModel lsm = (ListSelectionModel)listSelectionEvent.getSource();
	        if (lsm.isSelectionEmpty()) {
	            System.out.println("No rows selected");
	        }
	        else{
	            int selectedRow = lsm.getMinSelectionIndex();
	            System.out.println("The row "+selectedRow+" is now selected");
	            this.editValue(1);
	 
	        }
	    }

		private void editValue(int i) {
			
            System.out.println("The row "+i+" is now selected");

		}

	}

	private void setAfficherPersonneEquipe() {
		panelAfficherPersonneEquipe.add(new JLabel("Les membres :"));
		comboPersonne.setPreferredSize(new Dimension(200, 20));
		
		panelAfficherPersonneEquipe.add(comboPersonne);
		panelAfficherPersonneEquipe.add(boutonSupprCompetition);
		panelAfficherPersonneEquipe.add(new JLabel("Membre(s) � ajouter disponible(s)"));
		comboPersonneDispo.setPreferredSize(new Dimension(200, 20));
		panelAfficherPersonneEquipe.add(comboCompetitionDispo);
		panelAfficherPersonneEquipe.add(boutonAjouteCompetition);
	
		panelAfficherPersonneEquipe.setPreferredSize(tailleListCompetition);
		
		panelAfficherPersonneEquipe.setBorder(BorderFactory.createTitledBorder("Liste des Membres"));
		this.add(panelAfficherPersonneEquipe);
		
	}

	private void setAfficherCompetitionsEquipes() {
		panelAfficherCompetitionsEquipe.add(new JLabel("Les comp�titions : "));
		comboCompetition.setPreferredSize(new Dimension(200, 20));
		
		panelAfficherCompetitionsEquipe.add(comboCompetition);
		panelAfficherCompetitionsEquipe.add(boutonSupprCompetition);
		
		panelAfficherCompetitionsEquipe.add(new JLabel("Equipe(s) disponible(s)"));
		comboCompetitionDispo.setPreferredSize(new Dimension(100,20));
		panelAfficherCompetitionsEquipe.add(comboCompetitionDispo);
		
		panelAfficherCompetitionsEquipe.add(boutonAjouteCompetition);
		panelAfficherCompetitionsEquipe.setPreferredSize(tailleListCompetition);
		
		panelAfficherCompetitionsEquipe.setBorder(BorderFactory.createTitledBorder("Liste des Competitions"));
		this.add(panelAfficherCompetitionsEquipe);
		
	}

	private void setAfficherEquipe() {
		panelAfficherEquipe.add(new JLabel("Nom : "));
		nomField.setPreferredSize(new Dimension(130, 20));
		panelAfficherEquipe.add(nomField);
		
		panelAfficherEquipe.add(Box.createHorizontalStrut(40));
		
		boutonEdit.setPreferredSize(new Dimension(80,20));
		panelAfficherEquipe.add(boutonEdit);
		
		panelAfficherEquipe.setBorder(BorderFactory.createTitledBorder("Informations de l'�quipe"));
		panelAfficherEquipe.setPreferredSize(tailleEdit);
		this.add(panelAfficherEquipe);	
	}
	
	private void afficherMembresEquipe(Equipe equipe)
	{
		if(equipe.getMembres().size() > 0)
			equipe.getMembres();
	}

	private void setComboEquipe() {
//		for (Equipe e : inscriptions.getEquipes()) 
//		{
//			comboEquipe.addItem(e.getNom());
//			System.out.println(e.getNom() +" recupte");
//		}
//		comboEquipe.setPreferredSize(new Dimension(300, 20));
//		panelSelectEquipe.setBorder(BorderFactory.createTitledBorder("Liste des Equipes"));
//		JLabel labelSelectEquipe = new JLabel("S�lectionner une �quipe � administrer :");
//		labelSelectEquipe.setPreferredSize(new Dimension(300,20));
//		//comboEquipe.setBackground(Color.WHITE);
//		panelSelectEquipe.add(labelSelectEquipe);
//		panelSelectEquipe.add(comboEquipe);
		panelSelectEquipe.add(setAfficherTableauEquipe());
		//panelSelectEquipe.setPreferredSize(tailleListEquipe);
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
		//Listener pour le panneau Ajouter
		nomAjoutField.addKeyListener(new ajoutFieldListener());
		//boutonAjout.addActionListener(new boutonAjouteListener());
		
		//Listener pour les combos
		comboEquipe.addActionListener(new comboItemListener());
		//comboEquipe.addActionListener(new comboEquipeListener());
		//comboCompetition.addActionListener(new comboCompetitionListener());
//		comboEquipeDispo.addActionListener(new comboEquipeDispoListener());
//		comboCompetitionDispo.addActionListener(new comboCompetitionDispoListener());
//		
//		//Listener pour le panneau avec l'�dit
//		nomField.addKeyListener(new fieldListener());
//	
//		//Listener pour les boutons
//		boutonEdite.addActionListener(new boutonEditeListener());
//		boutonSupprEquipe.addActionListener(new boutonSupprEquipeListener());
//		boutonAjouterMembre.addActionListener(new boutonAjouteEquipeListener());
//		boutonSupprCompetition.addActionListener(new boutonSupprCompetitionListener());
		
		
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
