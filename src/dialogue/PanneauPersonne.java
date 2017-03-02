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
	Personne selectPersonne;
	
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
	private JButton boutonAjouteEquipe = new JButton("Ajouter � cette �quipe");
	private JButton boutonSupprEquipe = new JButton("Supprimer de cette �quipe");
	
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

		// Afficher une personne s�l�ctionner, �diter
		comboPersonne.addActionListener(new comboItemListener());
		affichePersonne.add(new JLabel("Nom : "));
		nomField.setPreferredSize(new Dimension(150, 20));
		affichePersonne.add(nomField);
		affichePersonne.add(new JLabel("Pr�nom : "));
		prenomField.setPreferredSize(new Dimension(150, 20));
		affichePersonne.add(prenomField);
		affichePersonne.add(new JLabel("Mail : "));
		mailField.setPreferredSize(new Dimension(150, 20));
		affichePersonne.add(mailField);
		boutonEdite.addActionListener(new boutonEditeListener());
		boutonEdite.setPreferredSize(new Dimension(150,20));
		affichePersonne.add(boutonEdite);
		affichePersonne.add(new JLabel("Ses �quipes : "));
		comboEquipe = new JComboBox();
		comboEquipe.setPreferredSize(new Dimension(150, 20));
		affichePersonne.add(comboEquipe);
		affichePersonne.add(boutonSupprEquipe);
		affichePersonne.add(new JLabel("Equipe(s) disponible(s)"));
		comboEquipeDispo = new JComboBox();
		affichePersonne.add(comboEquipeDispo);
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
		ajoutePersonne.add(new JLabel("Pr�nom : "));
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
	
	//Menu d�roulant d'�quipe d'une personne s�l�ctionner
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
			message.showMessageDialog(null, nom.getText() + " " + prenom.getText() + " � bien �t� ajouter !",
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
					message.showMessageDialog(null, nomField.getText() + " " + prenomField.getText() + " � bien �t� �diter !",
					"Information", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
}
