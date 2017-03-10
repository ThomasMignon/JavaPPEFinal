package dialogue;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import inscriptions.Competition;
import inscriptions.DateInvalide;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class PanneauPersonne extends JPanel
{
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
	
	private static final int WIDTH = 500;
	
	Personne selectPersonne;
	Equipe selectEquipe;
	Equipe selectEquipeDispo;
	Competition selectCompetition;
	Competition selectCompetitionDispo;
	
	private JPanel ajoutePersonne = new JPanel();
	private JPanel panelSelectPersonne = new JPanel();
	private JPanel panelAfficherPersonne = new JPanel();
	private JPanel panelAfficherCompetitionsPersonne = new JPanel();
	private JPanel panelAfficherEquipePersonne = new JPanel();
	
	private JTextField nomAjoutField = new JTextField();
	private JTextField prenomAjoutField = new JTextField();
	private JTextField mailAjoutField = new JTextField();
	
	private JTextField nomField = new JTextField();
	private JTextField prenomField = new JTextField();
	private JTextField mailField = new JTextField();
	
	private JButton boutonAjoute = new JButton("Ajouter");
	private JButton boutonEdite = new JButton("Editer");
	private JButton boutonAjouteEquipe = new JButton("Ajouter à cette équipe");
	private JButton boutonSupprEquipe = new JButton("Supprimer de cette équipe");
	private JButton boutonSupprCompetition = new JButton("Supprimer de cette compétition");
	private JButton boutonAjouteCompetition = new JButton("Ajouter à cette compétition");
	private JButton boutonSupprPersonne = new JButton("Supprimer cette personne");
	
	
	private Dimension tailleEdit = new Dimension(Fenetre.WIDTH * 80 / 100, Fenetre.HEIGHT * 12 / 100 );
	private Dimension tailleListPersonne = new Dimension(Fenetre.WIDTH * 80 / 100, Fenetre.HEIGHT * 12 / 100 );
	private Dimension tailleListCompetition = new Dimension(Fenetre.WIDTH * 40 / 100-2, Fenetre.HEIGHT * 35 / 100 );
	private Dimension tailleListEquipe = new Dimension(Fenetre.WIDTH * 40 / 100 -2, Fenetre.HEIGHT * 35 / 100 );

	public PanneauPersonne() 
	{
		// Instantiation
		
		boutonAjoute.setEnabled(false);
		boutonEdite.setEnabled(false);

		//Panneau afficherPersonne
		setListener();
		
		setPanneauAfficherPersonne();
		
		// Ajouter une personne
		setPanneauAjoutePersonne();
	}
	
	private void setPanneauAfficherPersonne()
	{

		// Menu deroulant pour selectionner personne
		setComboPersonne();

		// Afficher une personne séléctionner, éditer
		setAfficherPersonne();
		 
		//Afficher les équipes d'une personne séléctionner 
		setAfficherEquipesPersonne();
	
		//Afficher les compétitions d'une personne séléctionner
		setAfficherCompetitionsPersonne();
		
		
		//Bouton pour supprimer la personne séléctionner
		this.add(boutonSupprPersonne);
	}
	
	private void setListener()
	{
		nomAjoutField.addKeyListener(new ajoutFieldListener());
		prenomAjoutField.addKeyListener(new ajoutFieldListener());
		mailAjoutField.addKeyListener(new ajoutFieldListener());
		boutonAjoute.addActionListener(new boutonAjouteListener());
		comboPersonne.addActionListener(new comboItemListener());
		comboEquipe.addActionListener(new comboEquipeListener());
		comboCompetition.addActionListener(new comboCompetitionListener());
		comboEquipeDispo.addActionListener(new comboEquipeDispoListener());
		comboCompetitionDispo.addActionListener(new comboCompetitionDispoListener());
		nomField.addKeyListener(new fieldListener());
		prenomField.addKeyListener(new fieldListener());
		mailField.addKeyListener(new fieldListener());
		boutonEdite.addActionListener(new boutonEditeListener());
		boutonSupprEquipe.addActionListener(new boutonSupprEquipeListener());
		boutonAjouteEquipe.addActionListener(new boutonAjouteEquipeListener());
		boutonSupprCompetition.addActionListener(new boutonSupprCompetitionListener());
		boutonAjouteCompetition.addActionListener(new boutonAjouteCompetitionListener());
		boutonSupprPersonne.addActionListener(new boutonSupprPersonneListener());
		
	}
	
	private void setPanneauAjoutePersonne()
	{
		nomAjoutField.setPreferredSize(new Dimension(130, 20));
		prenomAjoutField.setPreferredSize(new Dimension(130, 20));
		mailAjoutField.setPreferredSize(new Dimension(130, 20));
		nomAjoutField.setBorder(BorderFactory.createLineBorder(Color.RED));
		prenomAjoutField.setBorder(BorderFactory.createLineBorder(Color.RED));
		mailAjoutField.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		ajoutePersonne.add(new JLabel("Nom : "));
		ajoutePersonne.add(nomAjoutField);
		ajoutePersonne.add(new JLabel("Prénom : "));
		ajoutePersonne.add(prenomAjoutField);
		ajoutePersonne.add(new JLabel("Email : "));
		ajoutePersonne.add(mailAjoutField);
		ajoutePersonne.add(Box.createHorizontalStrut(5));
		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setPreferredSize(new Dimension(5,25));
		ajoutePersonne.add(separator);
		ajoutePersonne.add(Box.createHorizontalStrut(5));
		ajoutePersonne.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		ajoutePersonne.add(boutonAjoute);
		ajoutePersonne.setBorder(BorderFactory.createTitledBorder("Ajouter une personne"));
		this.add(ajoutePersonne);
	}
	
	private void setComboPersonne()
	{
		//Appel des listeners pour les combo
		
		for (Personne p : inscriptions.getPersonnes()) 
		{
			comboPersonne.addItem(p.getNom()+" "+p.getPrenom());
			System.out.println(p.getPrenom() + p.getNom() +" recup");
		}
		comboPersonne.setPreferredSize(new Dimension(300, 20));
		comboPersonne.setSelectedItem(selectP);
		System.out.println(selectPersonne);
		panelSelectPersonne.setBorder(BorderFactory.createTitledBorder("Liste des Personnes"));
		JLabel labelSelectPersonne = new JLabel("Sélectionner une personne à administrer :");
		labelSelectPersonne.setPreferredSize(new Dimension(300,20));
		comboPersonne.setBackground(Color.WHITE);
		panelSelectPersonne.add(labelSelectPersonne);
		panelSelectPersonne.add(comboPersonne);
		panelSelectPersonne.setPreferredSize(tailleListPersonne);
		this.add(panelSelectPersonne);
	}
	
	private void setAfficherPersonne()
	{
		panelAfficherPersonne.add(new JLabel("Nom : "));
		nomField.setPreferredSize(new Dimension(130, 20));
		panelAfficherPersonne.add(nomField);
		
		panelAfficherPersonne.add(Box.createHorizontalStrut(40));
		
		panelAfficherPersonne.add(new JLabel("Prénom : "));
		prenomField.setPreferredSize(new Dimension(130, 20));
		panelAfficherPersonne.add(prenomField);
		
		panelAfficherPersonne.add(Box.createHorizontalStrut(10));
		
		panelAfficherPersonne.add(new JLabel("Mail : "));
		mailField.setPreferredSize(new Dimension(130, 20));
		panelAfficherPersonne.add(mailField);
		
		boutonEdite.setPreferredSize(new Dimension(80,20));
		panelAfficherPersonne.add(boutonEdite);
		
		panelAfficherPersonne.setBorder(BorderFactory.createTitledBorder("Informations"));
		panelAfficherPersonne.setPreferredSize(tailleEdit);
		this.add(panelAfficherPersonne);
	}
	
	private void setAfficherEquipesPersonne()
	{
		
		panelAfficherEquipePersonne.add(new JLabel("Ses équipes : "));
		comboEquipe.setPreferredSize(new Dimension(200, 20));
		
		panelAfficherEquipePersonne.add(comboEquipe);
		panelAfficherEquipePersonne.add(boutonSupprEquipe);
		
		panelAfficherEquipePersonne.add(new JLabel("Equipe(s) disponible(s)"));
		comboEquipeDispo.setPreferredSize(new Dimension(100,20));
		panelAfficherEquipePersonne.add(comboEquipeDispo);
		
		panelAfficherEquipePersonne.add(boutonAjouteEquipe);
		panelAfficherEquipePersonne.setPreferredSize(tailleListEquipe);
		
		panelAfficherEquipePersonne.setBorder(BorderFactory.createTitledBorder("Liste des Equipes"));
		this.add(panelAfficherEquipePersonne);
	}
	
	private void setAfficherCompetitionsPersonne()
	{
		
		panelAfficherCompetitionsPersonne.add(new JLabel("Ses compétitions"));
		comboCompetition.setPreferredSize(new Dimension(200, 20));
		
		panelAfficherCompetitionsPersonne.add(comboCompetition);
		panelAfficherCompetitionsPersonne.add(boutonSupprCompetition);
		panelAfficherCompetitionsPersonne.add(new JLabel("Compétition(s) disponible(s)"));
		comboCompetitionDispo.setPreferredSize(new Dimension(200, 20));
		panelAfficherCompetitionsPersonne.add(comboCompetitionDispo);
		panelAfficherCompetitionsPersonne.add(boutonAjouteCompetition);
	
		panelAfficherCompetitionsPersonne.setPreferredSize(tailleListCompetition);
		
		panelAfficherCompetitionsPersonne.setBorder(BorderFactory.createTitledBorder("Liste des Competitions"));
		this.add(panelAfficherCompetitionsPersonne);
	}
	
	private void setPanneauAfficherPersonne(Object select) 
	{
		for (Personne p : inscriptions.getPersonnes()) 
		{
			String nomPrenomSelect = p.getNom() + " " + p.getPrenom();
			if (nomPrenomSelect.equals(select)) 
			{
				String nomP = p.getNom();
				String prenomP = p.getPrenom();
				String mailP = p.getMail();
				selectPersonne = p;
				comboEquipe.removeAllItems();
				listEquipeSelectPersonne(p);
				System.out.println(selectE);
				comboEquipeDispo.removeAllItems();
				listEquipeDispoPersonne(p);
				comboCompetition.removeAllItems();
				listCompetitionPersonne(p);
				comboCompetitionDispo.removeAllItems();
				listCompetitionDispoPersonne(p);
				nomField.setText(nomP);
				prenomField.setText(prenomP);
				mailField.setText(mailP);
			}
		}
	}
	
	//Menu déroulant d'équipe d'une personne séléctionner
	private void listEquipeSelectPersonne(Personne p)
	{
		if(!p.getEquipes().isEmpty())
		{
			for(Equipe e : p.getEquipes())
			{
				System.out.println(e.getNom());
				comboEquipe.addItem(e.getNom());
			}
		}
	}
	
	private void listEquipeDispoPersonne(Personne p)
	{
		for(Equipe e : inscriptions.getEquipes())
		{
			if(!selectPersonne.getEquipes().contains(e))
			{
				comboEquipeDispo.addItem(e.getNom());
			}
		}
	}
	
	private void listCompetitionPersonne(Personne p)
	{
		if(!p.getCompetitions().isEmpty())
		{
			for(Competition c : p.getCompetitions())
			{
				comboCompetition.addItem(c.getNom());
			}
		}
	}
	
	private void listCompetitionDispoPersonne(Personne p)
	{
		for(Competition c : inscriptions.getCompetitions())
		{
			if(!selectPersonne.getCompetitions().contains(c))
			{
				if(!c.estEnEquipe())
				{
					comboCompetitionDispo.addItem(c.getNom());
				}
			}
		}
	}
	
	private boolean isValid(String s)
	{
		switch(s){
		case "nom" : return nomValid();
		case "prenom" : return prenomValid();
		case "mail" : return mailValid();
		}
		return false;
	}
	
	
	private boolean mailValid() {
		return mailAjoutField.getText().matches("[a-zA-Z0-9._-]{1,20}@[a-zA-Z]{3,10}\\.[a-z]{2,6}");		
	}

	private boolean prenomValid() {
		return prenomAjoutField.getText().matches("[a-zA-Z ]{3,}");
	}

	private boolean nomValid() {
		return nomAjoutField.getText().matches("[a-zA-Z ]{3,}");	
	}

	private void verifyAjoutField()
	{
	
		mailAjoutField.setBorder(BorderFactory.createLineBorder(mailValid() ? Color.GREEN : Color.RED));
		nomAjoutField.setBorder(BorderFactory.createLineBorder(nomValid() ? Color.GREEN : Color.RED));
		prenomAjoutField.setBorder(BorderFactory.createLineBorder(prenomValid() ? Color.GREEN : Color.RED));
		boutonAjoute.setEnabled((isValid("nom") && isValid("prenom") && isValid("mail")));
	}
	
	private void verifyField()
	{		
		if(nomField.getText().equals(selectPersonne.getNom()) && prenomField.getText().equals(selectPersonne.getPrenom()) && mailField.getText().equals(selectPersonne.getMail()))
		{
			boutonEdite.setEnabled(false);
		}
		else
		{
			boutonEdite.setEnabled(true);
		}
	}
	
	private void refresh()
	{
		this.removeAll();
		this.resetAllPanel();
		this.resetAllCombo();
		this.setPanneauAfficherPersonne();
		this.setPanneauAjoutePersonne();
		this.repaint();
		System.out.println("Refresh...");
	}
	
	private void resetAllPanel()
	{
		ajoutePersonne.removeAll();
		panelSelectPersonne.removeAll();
		panelAfficherPersonne.removeAll();
		panelAfficherEquipePersonne.removeAll();
		panelAfficherCompetitionsPersonne.removeAll();
	}
	
	
	private void resetAllCombo()
	{
		comboPersonne.removeAllItems();
		comboEquipe.removeAllItems();
		comboEquipeDispo.removeAllItems();
		comboCompetition.removeAllItems();
		comboCompetitionDispo.removeAllItems();
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
			verifyField();
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
			verifyAjoutField();
		}

		@Override
		public void keyTyped(KeyEvent arg0) 
		{
			
		}
		
	}
	
	class boutonAjouteListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			inscriptions.createPersonne(nomAjoutField.getText(), prenomAjoutField.getText(), mailAjoutField.getText(), true);
			JOptionPane.showMessageDialog(null, nomAjoutField.getText() + " " + prenomAjoutField.getText() + " à bien été ajouter !",
					"Information", JOptionPane.INFORMATION_MESSAGE);
			nomAjoutField.setText("");
			prenomAjoutField.setText("");
			mailAjoutField.setText("");
			refresh();
		}
	}

	class comboItemListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			selectP = comboPersonne.getSelectedItem();
			setPanneauAfficherPersonne(selectP);
		}
	}
	
	class comboEquipeListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			selectE = comboEquipe.getSelectedItem();
			for(Equipe e : selectPersonne.getEquipes())
			{
				String name = e.getNom();
				if(name.equals(selectE))
				{
					selectEquipe = e;
				}
			}
		}
	}
	
	class comboEquipeDispoListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			selectED = comboEquipeDispo.getSelectedItem();
			for(Equipe e : inscriptions.getEquipes())
			{
				String name = e.getNom();
				if(name.equals(selectED))
				{
					selectEquipeDispo = e;
				}
			}
		}
	}
	
	class comboCompetitionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			selectC = comboCompetition.getSelectedItem();
			for(Competition c : selectPersonne.getCompetitions())
			{
				String name = c.getNom();
				if(name.equals(selectC))
				{
					selectCompetition = c;
				}
			}
		}
	}
	
	class comboCompetitionDispoListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			selectCD = comboCompetitionDispo.getSelectedItem();
			for(Competition c : inscriptions.getCompetitions())
			{
				String name = c.getNom();
				if(name.equals(selectCD))
				{
					selectCompetitionDispo = c;
				}
			}
		}
		
	}
	
	class boutonEditeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			for (Personne p : inscriptions.getPersonnes()) 
			{
				String nomPrenomSelect = p.getNom() + " " + p.getPrenom();
				if (nomPrenomSelect.equals(selectP)) 
				{
					inscriptions.editePersonne(p, nomField.getText(), prenomField.getText(), mailField.getText());
					JOptionPane.showMessageDialog(null, nomField.getText() + " " + prenomField.getText() + " à bien été éditer !",
					"Information", JOptionPane.INFORMATION_MESSAGE);
					refresh();
				}
			}
		}
	}
	
	class boutonSupprEquipeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			selectEquipe.remove(selectPersonne,true);
			JOptionPane.showMessageDialog(null, selectPersonne.getPrenom() + " à bien été supprimer de " + selectEquipe.getNom(),
					"Information", JOptionPane.INFORMATION_MESSAGE);
			refresh();
		}
	}
	
	class boutonAjouteEquipeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			selectEquipeDispo.add(selectPersonne, true);
			JOptionPane.showMessageDialog(null, selectPersonne.getPrenom() + " à été ajouter à " + selectEquipeDispo.getNom(),
					"Information", JOptionPane.INFORMATION_MESSAGE);
			refresh();
		}
	}
	
	class boutonSupprCompetitionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			selectCompetition.remove(selectPersonne,true);
			JOptionPane.showMessageDialog(null, selectPersonne.getPrenom() + " à été surpprimer de " + selectCompetition.getNom(),
					"Information", JOptionPane.INFORMATION_MESSAGE);
			refresh();
		}
	}
	
	class boutonAjouteCompetitionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			try 
			{
				selectCompetitionDispo.add(selectPersonne);
				JOptionPane.showMessageDialog(null, selectPersonne.getPrenom() + " à été ajouter à " + selectCompetitionDispo.getNom(),
						"Information", JOptionPane.INFORMATION_MESSAGE);
				refresh();
			} 
			catch (DateInvalide e1) 
			{
				e1.printStackTrace();
			}
		}
	}
	
	class boutonSupprPersonneListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JOptionPane suppression = new JOptionPane();
			int option = suppression.showConfirmDialog(null, "Etes vous sûr de vouloir supprimer " +selectPersonne.getPrenom(),"Confirmer", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(option == JOptionPane.OK_OPTION)
			{
				selectPersonne.delete();
				JOptionPane.showMessageDialog(null, selectPersonne.getPrenom() + " à été supprimer",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}	
			refresh();
		}
	}
}