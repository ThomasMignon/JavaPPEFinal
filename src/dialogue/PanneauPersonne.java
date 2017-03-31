package dialogue;

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
	private JButton boutonEdit = new JButton("Editer");
	private JButton boutonAjouteEquipe = new JButton("Ajouter � cette �quipe");
	private JButton boutonSupprEquipe = new JButton("Supprimer de cette �quipe");
	private JButton boutonSupprCompetition = new JButton("Supprimer de cette comp�tition");
	private JButton boutonAjouteCompetition = new JButton("Ajouter � cette comp�tition");
	private JButton boutonSupprPersonne = new JButton("Supprimer cette personne");
	
	
	private Dimension taillePaneTable = new Dimension((int)(Fenetre.WIDTH * 0.80),(int)( Fenetre.HEIGHT * 0.50));
	private Dimension tailleTable = new Dimension((int)(Fenetre.WIDTH * 0.80),(int)( Fenetre.HEIGHT));
	private Dimension tailleListPersonne = new Dimension(Fenetre.WIDTH * 80 / 100, Fenetre.HEIGHT * 12 / 100 );
	private Dimension tailleListCompetition = new Dimension(Fenetre.WIDTH * 40 / 100-2, Fenetre.HEIGHT * 35 / 100 );
	private Dimension tailleListEquipe = new Dimension(Fenetre.WIDTH * 40 / 100 -2, Fenetre.HEIGHT * 35 / 100 );

	public PanneauPersonne() 
	{
		// Instantiation
		
		boutonAjoute.setEnabled(false);
		boutonEdit.setEnabled(false);

		//Panneau afficherPersonne
		setListener();
		
		setPanneauAfficherPersonne();
		
		// Ajouter une personne
		setPanneauAjoutePersonne();
	}
	
	private void setPanneauAfficherPersonne()
	{

		// Menu deroulant pour selectionner personne
//		setComboPersonne();
		setAfficherTableauEquipe();
		// Afficher une personne s�l�ctionner, �diter
		
//		setAfficherPersonne();
		//Afficher les �quipes d'une personne s�l�ctionner 
//		setAfficherEquipesPersonne();
	
		//Afficher les comp�titions d'une personne s�l�ctionner
//		setAfficherCompetitionsPersonne();
		
		
		//Bouton pour supprimer la personne s�l�ctionner
		this.add(boutonSupprPersonne);
	}
	
	private void setListener()
	{
		//Listener pour le panneau Ajouter
		nomAjoutField.addKeyListener(new ajoutFieldListener());
		prenomAjoutField.addKeyListener(new ajoutFieldListener());
		mailAjoutField.addKeyListener(new ajoutFieldListener());
		boutonAjoute.addActionListener(new boutonAjouteListener());
		
		//Listener pour les combos
		comboPersonne.addActionListener(new comboItemListener());
		comboEquipe.addActionListener(new comboEquipeListener());
		comboCompetition.addActionListener(new comboCompetitionListener());
		comboEquipeDispo.addActionListener(new comboEquipeDispoListener());
		comboCompetitionDispo.addActionListener(new comboCompetitionDispoListener());
		
		//Listener pour le panneau avec l'�dit
		nomField.addKeyListener(new fieldListener());
		prenomField.addKeyListener(new fieldListener());
		mailField.addKeyListener(new fieldListener());
		
		//Listener pour les boutons
		boutonEdit.addActionListener(new boutonEditListener());
		boutonSupprEquipe.addActionListener(new boutonSupprEquipeListener());
		boutonAjouteEquipe.addActionListener(new boutonAjouteEquipeListener());
		boutonSupprCompetition.addActionListener(new boutonSupprCompetitionListener());
		boutonAjouteCompetition.addActionListener(new boutonAjouteCompetitionListener());
		boutonSupprPersonne.addActionListener(new boutonSupprPersonneListener());
		
	}
	
	private void setAfficherTableauEquipe()
	{
		
		TablePersonne personneTable = new TablePersonne(this,5);
		panelAfficherPersonne.add(personneTable);
		panelAfficherPersonne.setPreferredSize(taillePaneTable);
		personneTable.setPreferredSize(tailleTable);
		this.add(panelAfficherPersonne);
	}
	
	private void setPanneauAjoutePersonne()
	{
		//Taille et bordure des �l�ments du panneau ajout
		nomAjoutField.setPreferredSize(new Dimension(130, 20));
		prenomAjoutField.setPreferredSize(new Dimension(130, 20));
		mailAjoutField.setPreferredSize(new Dimension(130, 20));
		nomAjoutField.setBorder(BorderFactory.createLineBorder(Color.RED));
		prenomAjoutField.setBorder(BorderFactory.createLineBorder(Color.RED));
		mailAjoutField.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		//Ajout des labels et des �l�ments
		ajoutePersonne.add(new JLabel("Nom : "));
		ajoutePersonne.add(nomAjoutField);
		ajoutePersonne.add(new JLabel("Pr�nom : "));
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
		
		panelAfficherPersonne.add(new JLabel("Pr�nom : "));
		prenomField.setPreferredSize(new Dimension(130, 20));
		panelAfficherPersonne.add(prenomField);
		
		panelAfficherPersonne.add(Box.createHorizontalStrut(10));
		
		panelAfficherPersonne.add(new JLabel("Mail : "));
		mailField.setPreferredSize(new Dimension(130, 20));
		panelAfficherPersonne.add(mailField);
		
		boutonEdit.setPreferredSize(new Dimension(80,20));
		panelAfficherPersonne.add(boutonEdit);
		
		panelAfficherPersonne.setBorder(BorderFactory.createTitledBorder("Informations"));
		panelAfficherPersonne.setPreferredSize(taillePaneTable);
		this.add(panelAfficherPersonne);
	}
	
	private void setAfficherEquipesPersonne()
	{
		
		panelAfficherEquipePersonne.add(new JLabel("Ses �quipes : "));
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
		
		panelAfficherCompetitionsPersonne.add(new JLabel("Ses comp�titions"));
		comboCompetition.setPreferredSize(new Dimension(200, 20));
		
		panelAfficherCompetitionsPersonne.add(comboCompetition);
		panelAfficherCompetitionsPersonne.add(boutonSupprCompetition);
		panelAfficherCompetitionsPersonne.add(new JLabel("Comp�tition(s) disponible(s)"));
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
	
	//Menu d�roulant d'�quipe d'une personne s�l�ctionner
	private void listEquipeSelectPersonne(Personne p)
	{
		if(!p.getEquipes().isEmpty())
		{
			for(Equipe e : p.getEquipes())
			{
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

	private void verifyField()
	{
		boutonEdit.setEnabled(verifyRegexField());
		mailAjoutField.setBorder(BorderFactory.createLineBorder(mailValid() ? Color.GREEN : Color.RED));
		nomAjoutField.setBorder(BorderFactory.createLineBorder(nomValid() ? Color.GREEN : Color.RED));
		prenomAjoutField.setBorder(BorderFactory.createLineBorder(prenomValid() ? Color.GREEN : Color.RED));
		boutonAjoute.setEnabled((isValid("nom") && isValid("prenom") && isValid("mail")));
	}
	
	private boolean verifyRegexField()
	{		
		return !(nomField.getText().equals(selectPersonne.getNom()) && prenomField.getText().equals(selectPersonne.getPrenom()) && mailField.getText().equals(selectPersonne.getMail()));
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
			verifyField();
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
			JOptionPane.showMessageDialog(null, nomAjoutField.getText() + " " + prenomAjoutField.getText() + " � bien �t� ajouter !",
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
			setPanneauAfficherPersonne(comboPersonne.getSelectedItem());
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
	
	class boutonEditListener implements ActionListener
	{
		//Personne personne;
		
		
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			
			System.out.println(nomField.getText());	
			inscriptions.editePersonne(selectPersonne, nomField.getText(), prenomField.getText(), mailField.getText());
			JOptionPane.showMessageDialog(null, nomField.getText() + " " + prenomField.getText() + " � bien �t� �diter !",
			"Information", JOptionPane.INFORMATION_MESSAGE);
			System.out.println(nomField.getText());
			refresh();
				
			
		}
	}
	
	class boutonSupprEquipeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			selectEquipe.remove(selectPersonne,true);
			JOptionPane.showMessageDialog(null, selectPersonne.getPrenom() + " � bien �t� supprimer de " + selectEquipe.getNom(),
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
			JOptionPane.showMessageDialog(null, selectPersonne.getPrenom() + " � �t� ajouter � " + selectEquipeDispo.getNom(),
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
			JOptionPane.showMessageDialog(null, selectPersonne.getPrenom() + " � �t� surpprimer de " + selectCompetition.getNom(),
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
				JOptionPane.showMessageDialog(null, selectPersonne.getPrenom() + " a �t� ajouter � " + selectCompetitionDispo.getNom(),
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
			int option = suppression.showConfirmDialog(null, "Etes vous s�r de vouloir supprimer " +selectPersonne.getPrenom(),"Confirmer", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(option == JOptionPane.OK_OPTION)
			{
				selectPersonne.delete();
				JOptionPane.showMessageDialog(null, selectPersonne.getPrenom() + " � �t� supprimer",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}	
			refresh();
		}
	}
}