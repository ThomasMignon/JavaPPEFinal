package dialogue;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class PanneauEquipe extends JPanel {

	private JComboBox<String> comboPersonne = new JComboBox<>();
	private JComboBox<String> comboEquipe = new JComboBox<>();
	private JComboBox<String> comboEquipeDispo = new JComboBox<>();
	private JComboBox<String> comboCompetition = new JComboBox<>();
	private JComboBox<String> comboCompetitionDispo = new JComboBox<>();
	
	private Inscriptions inscriptions = Panneau.getInscriptions();
	
	Object selectP = new Object();
	Object selectE = new Object();
	Object selectED = new Object();
	Object selectC = new Object();
	Object selectCD = new Object();
	
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
	
	private JTextField nomAjoutField = new JTextField();
	private JTextField prenomAjoutField = new JTextField();
	private JTextField mailAjoutField = new JTextField();
	
	private JTextField nomField = new JTextField();

	private JButton boutonAjout = new JButton("Ajouter");
	private JButton boutonEdite = new JButton("Editer");
	private JButton boutonAjouteMembre = new JButton("Ajouter ce membre");
	private JButton boutonSupprMembre = new JButton("Supprimer de ce membre");
	private JButton boutonSupprCompetition = new JButton("Supprimer de cette compétition");
	private JButton boutonAjouteCompetition = new JButton("Ajouter à cette compétition");
	private JButton boutonSupprEquipe = new JButton("Supprimer cette équipe");
	
	public PanneauEquipe()
	{
		// Instantiation

		boutonAjout.setEnabled(false);
		boutonEdite.setEnabled(false);

		//Panneau afficherPersonne
		//setListener();
		
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
		setAfficherPersonneEquipe();
		
		
		//Bouton pour supprimer la personne séléctionner
		this.add(boutonSupprEquipe);
		
	}

	private void setAfficherPersonneEquipe() {
		// TODO Auto-generated method stub
		
	}

	private void setAfficherCompetitionsEquipes() {
		// TODO Auto-generated method stub
		
	}

	private void setAfficherEquipe() {
		// TODO Auto-generated method stub
		
	}

	private void setComboEquipe() {
		// TODO Auto-generated method stub
		
	}

	private void setPanneauAjouteEquipe() {
		ajouteEquipe.setBackground(Color.BLUE);
		nomAjoutField.setPreferredSize(new Dimension(130, 20));
		ajouteEquipe.add(new JLabel("Nom : "));
		ajouteEquipe.add(nomAjoutField);
		ajouteEquipe.add(boutonAjout);
		this.add(ajouteEquipe);
		
	}
}
