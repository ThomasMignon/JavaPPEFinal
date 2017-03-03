package dialogue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class PanneauPersonne extends JPanel 
{
	private JComboBox comboPersonne;
	private JComboBox comboEquipe;
	private JComboBox comboEquipeDispo;
	
	private Inscriptions inscriptions = Panneau.getInscriptions();
	
	Object selectP = new Object();
	Object selectE = new Object();
	Object selectED = new Object();
	Personne selectPersonne;
	Equipe selectEquipe;
	Equipe selectEquipeDispo;
	
	private JPanel ajoutePersonne = new JPanel();
	private JPanel affichePersonne = new JPanel();
	
	private JTextField nom = new JTextField();
	private JTextField prenom = new JTextField();
	private JTextField mail = new JTextField();
	
	private JTextField nomField = new JTextField();
	private JTextField prenomField = new JTextField();
	private JTextField mailField = new JTextField();
	
	private JButton boutonAjoute = new JButton("Ajouter");
	private JButton boutonEdite = new JButton("Editer");
	private JButton boutonAjouteEquipe = new JButton("Ajouter à cette équipe");
	private JButton boutonSupprEquipe = new JButton("Supprimer de cette équipe");
	
	private JOptionPane message;

	public PanneauPersonne() 
	{
		// Instantiation
		this.setLayout(new BorderLayout());

		// Menu deroulant pour selectionner personne
		comboPersonne = new JComboBox();
		for (Personne p : inscriptions.getPersonnes()) 
		{
			comboPersonne.addItem(p.getNom()+" "+p.getPrenom());
			System.out.println(p.getPrenom() + p.getNom() +" recup");
		}
		comboPersonne.setPreferredSize(new Dimension(150, 20));
		this.add(comboPersonne, BorderLayout.NORTH);

		// Afficher une personne séléctionner, éditer
		comboPersonne.addActionListener(new comboItemListener());
		affichePersonne.add(new JLabel("Nom : "));
		nomField.setPreferredSize(new Dimension(150, 20));
		affichePersonne.add(nomField);
		affichePersonne.add(new JLabel("Prénom : "));
		prenomField.setPreferredSize(new Dimension(150, 20));
		affichePersonne.add(prenomField);
		affichePersonne.add(new JLabel("Mail : "));
		mailField.setPreferredSize(new Dimension(150, 20));
		affichePersonne.add(mailField);
		boutonEdite.addActionListener(new boutonEditeListener());
		boutonEdite.setPreferredSize(new Dimension(150,20));
		affichePersonne.add(boutonEdite);
		affichePersonne.add(new JLabel("Ses équipes : "));
		comboEquipe = new JComboBox();
		comboEquipe.setPreferredSize(new Dimension(150, 20));
		comboEquipe.addActionListener(new comboEquipeListener());
		affichePersonne.add(comboEquipe);
		boutonSupprEquipe.addActionListener(new boutonSupprEquipeListener());
		affichePersonne.add(boutonSupprEquipe);
		affichePersonne.add(new JLabel("Equipe(s) disponible(s)"));
		comboEquipeDispo = new JComboBox();
		comboEquipeDispo.addActionListener(new comboEquipeDispoListener());
		affichePersonne.add(comboEquipeDispo);
		boutonAjouteEquipe.addActionListener(new boutonAjouteEquipeListener());
		affichePersonne.add(boutonAjouteEquipe);
		this.add(affichePersonne, BorderLayout.CENTER);
		
		// Ajouter une personne
		ajoutePersonne.setBackground(Color.GRAY);

		boutonAjoute.addActionListener(new boutonAjouteListener());
		nom.setPreferredSize(new Dimension(130, 20));
		prenom.setPreferredSize(new Dimension(130, 20));
		mail.setPreferredSize(new Dimension(130, 20));
		ajoutePersonne.add(new JLabel("Nom : "));
		ajoutePersonne.add(nom);
		ajoutePersonne.add(new JLabel("Prénom : "));
		ajoutePersonne.add(prenom);
		ajoutePersonne.add(new JLabel("Email : "));
		ajoutePersonne.add(mail);
		ajoutePersonne.add(boutonAjoute);
		this.add(ajoutePersonne, BorderLayout.SOUTH);
	}
	
	private void setPanneauAfficherPersonne(Object select) 
	{
		for (Personne p : inscriptions.getPersonnes()) 
		{
			String nomPrenomSelect = p.getNom() + " " + p.getPrenom();
			if (nomPrenomSelect.equals(select)) {
				String nomP = p.getNom();
				String prenomP = p.getPrenom();
				String mailP = p.getMail();
				selectPersonne = p;
				comboEquipe.removeAllItems();
				listEquipeSelectPersonne(p);
				comboEquipeDispo.removeAllItems();
				listEquipeDispoPersonne(p);
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
	
	class boutonAjouteListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			inscriptions.createPersonne(nom.getText(), prenom.getText(), mail.getText(), true);
			message = new JOptionPane();
			message.showMessageDialog(null, nom.getText() + " " + prenom.getText() + " à bien été ajouter !",
					"Information", JOptionPane.INFORMATION_MESSAGE);
			nom.setText("");
			prenom.setText("");
			mail.setText("");
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
					message.showMessageDialog(null, nomField.getText() + " " + prenomField.getText() + " à bien été éditer !",
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
			message.showMessageDialog(null, selectPersonne.getPrenom() + " à bien été supprimer de " + selectEquipe.getNom(),
					"Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	class boutonAjouteEquipeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			selectEquipeDispo.add(selectPersonne, true);
			message.showMessageDialog(null, selectPersonne.getPrenom() + " à été ajouter à " + selectEquipeDispo.getNom(),
					"Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
