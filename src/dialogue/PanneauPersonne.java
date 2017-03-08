package dialogue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

	public PanneauPersonne() 
	{
		// Instantiation
		
		boutonAjoute.setEnabled(false);
		boutonEdite.setEnabled(false);

		//Panneau afficherPersonne
		
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
	
	private void setPanneauAjoutePersonne()
	{
		ajoutePersonne.setBackground(Color.GRAY);
		nomAjoutField.addKeyListener(new ajoutFieldListener());
		prenomAjoutField.addKeyListener(new ajoutFieldListener());
		mailAjoutField.addKeyListener(new ajoutFieldListener());
		boutonAjoute.addActionListener(new boutonAjouteListener());
		nomAjoutField.setPreferredSize(new Dimension(130, 20));
		prenomAjoutField.setPreferredSize(new Dimension(130, 20));
		mailAjoutField.setPreferredSize(new Dimension(130, 20));
		ajoutePersonne.add(new JLabel("Nom : "));
		ajoutePersonne.add(nomAjoutField);
		ajoutePersonne.add(new JLabel("Prénom : "));
		ajoutePersonne.add(prenomAjoutField);
		ajoutePersonne.add(new JLabel("Email : "));
		ajoutePersonne.add(mailAjoutField);
		ajoutePersonne.add(boutonAjoute);
		this.add(ajoutePersonne);
	}
	
	private void setComboPersonne()
	{
		//Appel des listeners pour les combo
		comboPersonne.addActionListener(new comboItemListener());
		comboEquipe.addActionListener(new comboEquipeListener());
		comboCompetition.addActionListener(new comboCompetitionListener());
		comboEquipeDispo.addActionListener(new comboEquipeDispoListener());
		comboCompetitionDispo.addActionListener(new comboCompetitionDispoListener());
		
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
		panelSelectPersonne.add(labelSelectPersonne);
		panelSelectPersonne.add(comboPersonne);
		this.add(panelSelectPersonne);
	}
	
	private void setAfficherPersonne()
	{
		
		nomField.addKeyListener(new fieldListener());
		prenomField.addKeyListener(new fieldListener());
		mailField.addKeyListener(new fieldListener());
		
		panelAfficherPersonne.add(new JLabel("Nom : "));
		nomField.setPreferredSize(new Dimension(150, 20));
		panelAfficherPersonne.add(nomField);
		
		panelAfficherPersonne.add(new JLabel("Prénom : "));
		prenomField.setPreferredSize(new Dimension(150, 20));
		panelAfficherPersonne.add(prenomField);
		
		panelAfficherPersonne.add(new JLabel("Mail : "));
		mailField.setPreferredSize(new Dimension(150, 20));
		panelAfficherPersonne.add(mailField);
		
		boutonEdite.addActionListener(new boutonEditeListener());
		boutonEdite.setPreferredSize(new Dimension(150,20));
		panelAfficherPersonne.add(boutonEdite);
		
		panelAfficherPersonne.setBorder(BorderFactory.createTitledBorder("Liste des Personnes"));
		this.add(panelAfficherPersonne);
		

	}
	
	private void setAfficherEquipesPersonne()
	{
		
		panelAfficherEquipePersonne.add(new JLabel("Ses équipes : "));
		comboEquipe.setPreferredSize(new Dimension(200, 20));
		
		panelAfficherEquipePersonne.add(comboEquipe);
		boutonSupprEquipe.addActionListener(new boutonSupprEquipeListener());
		panelAfficherEquipePersonne.add(boutonSupprEquipe);
		
		panelAfficherEquipePersonne.add(new JLabel("Equipe(s) disponible(s)"));
		comboEquipeDispo.setPreferredSize(new Dimension(100,20));
		panelAfficherEquipePersonne.add(comboEquipeDispo);
		
		boutonAjouteEquipe.addActionListener(new boutonAjouteEquipeListener());
		panelAfficherEquipePersonne.add(boutonAjouteEquipe);
		panelAfficherEquipePersonne.setPreferredSize(new Dimension(320,200));
		
		panelAfficherEquipePersonne.setBorder(BorderFactory.createTitledBorder("Liste des Personnes"));
		this.add(panelAfficherEquipePersonne);
	}
	
	private void setAfficherCompetitionsPersonne()
	{
		
		panelAfficherCompetitionsPersonne.add(new JLabel("Ses compétitions"));
		comboCompetition.setPreferredSize(new Dimension(200, 20));
		
		panelAfficherCompetitionsPersonne.add(comboCompetition);
		boutonSupprCompetition.addActionListener(new boutonSupprCompetitionListener());
		panelAfficherCompetitionsPersonne.add(boutonSupprCompetition);
		panelAfficherCompetitionsPersonne.add(new JLabel("Compétition(s) disponible(s)"));
		comboCompetitionDispo.setPreferredSize(new Dimension(200, 20));
		panelAfficherCompetitionsPersonne.add(comboCompetitionDispo);
		boutonAjouteCompetition.addActionListener(new boutonAjouteCompetitionListener());
		panelAfficherCompetitionsPersonne.add(boutonAjouteCompetition);
		boutonSupprPersonne.addActionListener(new boutonSupprPersonneListener());
		
		panelAfficherCompetitionsPersonne.setPreferredSize(new Dimension(320,200));
		
		panelAfficherCompetitionsPersonne.setBorder(BorderFactory.createTitledBorder("Liste des Personnes"));
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
	
	private void verifyAjoutField()
	{
		if(nomAjoutField.getText().equals("") || prenomAjoutField.getText().equals("") || mailAjoutField.getText().equals(""))
		{
			boutonAjoute.setEnabled(false);
		}
		else
		{
			boutonAjoute.setEnabled(true);
		}
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
					System.out.println(selectEquipeDispo);
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
			selectPersonne.delete();
			JOptionPane.showMessageDialog(null, selectPersonne.getPrenom() + " à été supprimer",
					"Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}