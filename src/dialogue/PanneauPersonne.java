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

public class PanneauPersonne extends JPanel {
	private JComboBox<String> comboPersonne = new JComboBox<>();
	private JComboBox<String> comboEquipe = new JComboBox<>();
	private JComboBox<String> comboEquipeDispo = new JComboBox<>();
	private JComboBox<String> comboCompetition = new JComboBox<>();
	private JComboBox<String> comboCompetitionDispo = new JComboBox<>();

	private Inscriptions inscriptions = Panneau.getInscriptions();

	private JPanel ajoutePersonne = new JPanel();
	private JPanel panelSelectPersonne = new JPanel();
	private JPanel panelAfficherPersonne = new JPanel();
	private JPanel panelAfficherCompetitionsPersonne = new JPanel();
	private JPanel panelAfficherEquipePersonne = new JPanel();

	private JTextField nomAjoutField = new JTextField();
	private JTextField prenomAjoutField = new JTextField();
	private JTextField mailAjoutField = new JTextField();

	private JButton boutonAjoute = new JButton("Ajouter");

	private Dimension taillePaneTable = new Dimension((int) (Fenetre.WIDTH * 0.80), (int) (Fenetre.HEIGHT * 0.50));
	private Dimension tailleTable = new Dimension((int) (Fenetre.WIDTH * 0.80), (int) (Fenetre.HEIGHT));
	private Dimension tailleListPersonne = new Dimension(Fenetre.WIDTH * 80 / 100, Fenetre.HEIGHT * 12 / 100);
	private Dimension tailleListCompetition = new Dimension(Fenetre.WIDTH * 40 / 100 - 2, Fenetre.HEIGHT * 35 / 100);
	private Dimension tailleListEquipe = new Dimension(Fenetre.WIDTH * 40 / 100 - 2, Fenetre.HEIGHT * 35 / 100);

	public PanneauPersonne() {
		// Instantiation

		boutonAjoute.setEnabled(false);

		// Panneau afficherPersonne
		setListener();

		setPanneauAfficherPersonne();

		// Ajouter une personne
		setPanneauAjoutePersonne();
	}

	private void setPanneauAfficherPersonne() {

		// Menu deroulant pour selectionner personne
		// setComboPersonne();
		setAfficherTableauEquipe();
		// Afficher une personne séléctionner, éditer

		// setAfficherPersonne();
		// Afficher les équipes d'une personne séléctionner
		// setAfficherEquipesPersonne();

		// Afficher les compétitions d'une personne séléctionner
		// setAfficherCompetitionsPersonne();

		// Bouton pour supprimer la personne séléctionner
		// this.add(boutonSupprPersonne);
	}

	private void setListener() {
		// Listener pour le panneau Ajouter
		nomAjoutField.addKeyListener(new ajoutFieldListener());
		prenomAjoutField.addKeyListener(new ajoutFieldListener());
		mailAjoutField.addKeyListener(new ajoutFieldListener());
		boutonAjoute.addActionListener(new boutonAjouteListener());

	}

	private void setAfficherTableauEquipe() {
		TablePersonne personneTable = new TablePersonne(this, 5);
		panelAfficherPersonne.add(personneTable);
		// panelAfficherPersonne.setPreferredSize(taillePaneTable);
		this.add(panelAfficherPersonne);
	}

	private void setPanneauAjoutePersonne() {
		// Taille et bordure des élments du panneau ajout
		nomAjoutField.setPreferredSize(new Dimension(130, 20));
		prenomAjoutField.setPreferredSize(new Dimension(130, 20));
		mailAjoutField.setPreferredSize(new Dimension(130, 20));
		nomAjoutField.setBorder(BorderFactory.createLineBorder(Color.RED));
		prenomAjoutField.setBorder(BorderFactory.createLineBorder(Color.RED));
		mailAjoutField.setBorder(BorderFactory.createLineBorder(Color.RED));

		// Ajout des labels et des éléments
		ajoutePersonne.add(new JLabel("Nom : "));
		ajoutePersonne.add(nomAjoutField);
		ajoutePersonne.add(new JLabel("Pr�nom : "));
		ajoutePersonne.add(prenomAjoutField);
		ajoutePersonne.add(new JLabel("Email : "));
		ajoutePersonne.add(mailAjoutField);
		ajoutePersonne.add(Box.createHorizontalStrut(5));
		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setPreferredSize(new Dimension(5, 25));
		ajoutePersonne.add(separator);
		ajoutePersonne.add(Box.createHorizontalStrut(5));
		ajoutePersonne.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		ajoutePersonne.add(boutonAjoute);
		ajoutePersonne.setBorder(BorderFactory.createTitledBorder("Ajouter une personne"));
		this.add(ajoutePersonne);
	}

	private boolean isValid(String s) {
		switch (s) {
		case "nom":
			return nomValid();
		case "prenom":
			return prenomValid();
		case "mail":
			return mailValid();
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

	private void verifyField() {
		// boutonEdit.setEnabled(verifyRegexField());
		mailAjoutField.setBorder(BorderFactory.createLineBorder(mailValid() ? Color.GREEN : Color.RED));
		nomAjoutField.setBorder(BorderFactory.createLineBorder(nomValid() ? Color.GREEN : Color.RED));
		prenomAjoutField.setBorder(BorderFactory.createLineBorder(prenomValid() ? Color.GREEN : Color.RED));
		boutonAjoute.setEnabled((isValid("nom") && isValid("prenom") && isValid("mail")));
	}

	private void refresh() {
		this.removeAll();
		this.resetAllPanel();
		this.setPanneauAfficherPersonne();
		this.setPanneauAjoutePersonne();
		this.repaint();
		System.out.println("Refresh...");
	}

	private void resetAllPanel() {
		ajoutePersonne.removeAll();
		panelSelectPersonne.removeAll();
		panelAfficherPersonne.removeAll();
		panelAfficherEquipePersonne.removeAll();
		panelAfficherCompetitionsPersonne.removeAll();
	}

	class fieldListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {
			verifyField();
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

	}

	class ajoutFieldListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {

		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			verifyField();
		}

		@Override
		public void keyTyped(KeyEvent arg0) {

		}

	}

	class boutonAjouteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			inscriptions.createPersonne(nomAjoutField.getText(), prenomAjoutField.getText(), mailAjoutField.getText(),
					true);
			JOptionPane.showMessageDialog(null,
					nomAjoutField.getText() + " " + prenomAjoutField.getText() + " � bien �t� ajouter !", "Information",
					JOptionPane.INFORMATION_MESSAGE);
			nomAjoutField.setText("");
			prenomAjoutField.setText("");
			mailAjoutField.setText("");
			refresh();
		}
	}
}