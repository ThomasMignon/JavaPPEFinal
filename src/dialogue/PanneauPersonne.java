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

import inscriptions.Inscriptions;
import inscriptions.Personne;

public class PanneauPersonne extends JPanel 
{
	private JComboBox combo;
	
	private Inscriptions inscriptions = Panneau.getInscriptions();;
	
	private JPanel ajoutePersonne = new JPanel();
	private JPanel affichePersonne = new JPanel();
	
	private JTextField nom = new JTextField();
	private JTextField prenom = new JTextField();
	private JTextField mail = new JTextField();
	
	private JTextField nomField = new JTextField();
	private JTextField prenomField = new JTextField();
	
	private JButton boutonAjoute = new JButton("Ajouter");
	private JOptionPane message;
	private int[] id = new int[inscriptions.getPersonnes().size()];
	private String[] nomPrenom = new String[inscriptions.getPersonnes().size()];

	public PanneauPersonne() {
		// Instantiation
		this.setLayout(new BorderLayout());

		// Menu deroulant pour selectionner personne
		int i = 0;
		for (Personne p : inscriptions.getPersonnes()) {
			String name = p.getNom() + " " + p.getPrenom();
			System.out.println(name + " recup");
			nomPrenom[i] = name;
			i++;
		}
		combo = new JComboBox(nomPrenom);
		combo.setPreferredSize(new Dimension(150, 20));
		this.add(combo, BorderLayout.NORTH);

		// Afficher une personne séléctionner
		combo.addActionListener(new comboItemListener());
		affichePersonne.add(new JLabel("Nom : "));
		nomField.setPreferredSize(new Dimension(150, 20));
		affichePersonne.add(nomField);
		affichePersonne.add(new JLabel("Prénom : "));
		prenomField.setPreferredSize(new Dimension(150, 20));
		affichePersonne.add(prenomField);
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
				nomField.setText(nomP);
				prenomField.setText(prenomP);
			}
		}
	}
	
	class boutonAjouteListener implements ActionListener {

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

	class comboItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Object select = combo.getSelectedItem();
			setPanneauAfficherPersonne(select);
		}
	}
}
