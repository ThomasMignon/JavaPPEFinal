package dialogue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dialogue.PanneauPersonne.ajoutFieldListener;
import dialogue.PanneauPersonne.boutonAjouteListener;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class TablePersonne extends JPanel{
    //Notre Panneau de pagination
    private PaginationPanel paginationPanel;
    //Un observateur
    private PaginationObserver paginationObserver;
    //Le panneau qui va afficher les donnï¿½es et le panneau principal
    private JPanel dataLayer, contentPane;
    private Inscriptions inscriptions = Panneau.getInscriptions();
    public SortedSet<Personne> pers = inscriptions.getPersonnes();
    ArrayList personnes = new ArrayList(pers);
    Object selectE;
    PanneauPersonne p;
    int nbParPage = 0;
    //Champ pour l'ajout de personne
    private JTextField nomAjoutField;
    private JTextField prenomAjoutField;
    private JTextField mailAjoutField;
    private JButton boutonAjoute;
    
    public TablePersonne(PanneauPersonne p,int nbParPage)
    {
        this.initComponents();
        boutonAjoute.setEnabled(false);
        this.p = p;
        this.nbParPage = nbParPage;
    }

    private void initComponents() {
        dataLayer = new JPanel();
        
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        
        /*
         * Construction de notre systï¿½me de pagination pour la liste
         * fournie par la mï¿½thode getList()
         */
        paginationPanel = new PaginationPanel<Personne>(getList(),nbParPage);
        //Instanciation avec classe anonyme de notre observateur
        paginationObserver = new PaginationObserver<Personne>(){

            /*
             * Implï¿½mentation de la mï¿½thode update de l'interface
             */
            @Override
            public void update(List<Personne> personne) {
                dataLayer.removeAll();
                dataLayer.repaint();
                dataLayer.setPreferredSize(new Dimension((int)(Fenetre.WIDTH * 0.8),(int) (Fenetre.HEIGHT * 0.8)));
                for(Personne p  : personne){
                	if(!p.getIsDelete())
                	{
                		JPanel panel = new JPanel();
                    	JTextField nomPersonne = new JTextField(p.getNom());
                    	nomPersonne.setPreferredSize(new Dimension(120,25));
                    	panel.add(new JLabel("Nom : "));
                    	panel.add(nomPersonne);
                    	JTextField prenomPersonne = new JTextField(p.getPrenom());
                    	prenomPersonne.setPreferredSize(new Dimension(120,25));
                    	panel.add(new JLabel("Prénom : "));
                    	panel.add(prenomPersonne);
                    	JTextField mailPersonne = new JTextField(p.getMail());
                    	mailPersonne.setPreferredSize(new Dimension(120,25));
                    	panel.add(new JLabel("Email : "));
                    	panel.add(mailPersonne);
                    	nomPersonne.addKeyListener(new editFieldListener(p, nomPersonne,prenomPersonne,mailPersonne));
                        JButton supprimer = new JButton("Supprimer");
                        supprimer.addActionListener(new boutonSupprimerListener(p));
                        panel.add(supprimer);
                        panel.setPreferredSize(new Dimension((int)(Fenetre.WIDTH * 0.9), 35));
                        dataLayer.add(panel);
                	}
                }
                //Ajout d'une personne 
                JPanel ajoutePersonne = new JPanel();
                
                
                //Instantiation des champs pour l'ajout d'une personne
                nomAjoutField = new JTextField();
                prenomAjoutField = new JTextField();
                mailAjoutField = new JTextField();
                boutonAjoute = new JButton("    Ajouter   ");
                
                //Listener des champs
                nomAjoutField.addKeyListener(new ajoutFieldListener());
        		prenomAjoutField.addKeyListener(new ajoutFieldListener());
        		mailAjoutField.addKeyListener(new ajoutFieldListener());
        		boutonAjoute.addActionListener(new boutonAjouteListener());
                
        		
        		//Taille des champs
                nomAjoutField.setPreferredSize(new Dimension(120,25));
        		prenomAjoutField.setPreferredSize(new Dimension(120,25));
        		mailAjoutField.setPreferredSize(new Dimension(120,25));
        		nomAjoutField.setBorder(BorderFactory.createLineBorder(Color.RED));
        		prenomAjoutField.setBorder(BorderFactory.createLineBorder(Color.RED));
        		mailAjoutField.setBorder(BorderFactory.createLineBorder(Color.RED));

        		// Ajout des labels et des éléments dans le panel ajoutePersonne
        		ajoutePersonne.add(new JLabel("Nom : "));
        		ajoutePersonne.add(nomAjoutField);
        		ajoutePersonne.add(new JLabel("Prénom : "));
        		ajoutePersonne.add(prenomAjoutField);
        		ajoutePersonne.add(new JLabel("Email : "));
        		ajoutePersonne.add(mailAjoutField);
        		ajoutePersonne.add(boutonAjoute);
        		
        		//Ajout dans la table
        		dataLayer.add(ajoutePersonne);
        		
                dataLayer.repaint();
                dataLayer.updateUI();
            }
            
        };
        //Ajout de l'observateur
        paginationPanel.addPaginationObserver(paginationObserver);
        
        contentPane.add(new JScrollPane(dataLayer));
        contentPane.add(paginationPanel, BorderLayout.SOUTH);
        this.add(contentPane);
        paginationPanel.reset();
    }
    
    /**
     * 
     * Crï¿½ation du listener sur l'ï¿½dition
     * 
     */
    
    class editFieldListener implements KeyListener
	{
    	private Personne personne;
    	private JTextField nomField;
    	private JTextField prenomField;
    	private JTextField mailField;
    	 
	    public editFieldListener(Personne personne,JTextField nomField, JTextField prenomField,JTextField mailField) {
	        super();
	        this.personne = personne;
	        this.nomField = nomField;
	        this.prenomField = prenomField;
	        this.mailField = mailField;
	    }

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void keyReleased(KeyEvent e) {
			inscriptions.editePersonne(personne,nomField.getText(), prenomField.getText(), mailField.getText());
			System.out.println(personne.getNom()+": nouvelle valeur.");		
		}


		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
    
    private void refresh() 
    {
    	this.remove(contentPane);
    	this.initComponents();
		dataLayer.repaint();
		p.refresh();
		System.out.println("Refresh...");
	}
    
    /*
     * Crï¿½ation d'une liste de donnï¿½es ï¿½ paginer
     */
    
    private List<Personne> getList(){
        
        return this.personnes;
    }
    
    /**
     * 
     * Crï¿½ation du listener sur le bouton Supprimer
     * 
     */
    
    class boutonSupprimerListener implements ActionListener
	{
    	private Personne p;
    	 
	    public boutonSupprimerListener(Personne p) {
	        super();
	        this.p = p;
	    }
    	    
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{		
			inscriptions.remove(p);
			System.out.println(p.getNom()+" supprimer.");
			refresh();
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
	
	private void verifyField() 
	{
		// boutonEdit.setEnabled(verifyRegexField());
		mailAjoutField.setBorder(BorderFactory.createLineBorder(mailValid() ? Color.GREEN : Color.RED));
		nomAjoutField.setBorder(BorderFactory.createLineBorder(nomValid() ? Color.GREEN : Color.RED));
		prenomAjoutField.setBorder(BorderFactory.createLineBorder(prenomValid() ? Color.GREEN : Color.RED));
		boutonAjoute.setEnabled((isValid("nom") && isValid("prenom") && isValid("mail")));
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
	
	class boutonAjouteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			inscriptions.createPersonne(nomAjoutField.getText(), prenomAjoutField.getText(), mailAjoutField.getText());
			JOptionPane.showMessageDialog(null,
					nomAjoutField.getText() + " " + prenomAjoutField.getText() + " Ã  bien Ã©tÃ© ajouter !", "Information",
					JOptionPane.INFORMATION_MESSAGE);
			nomAjoutField.setText("");
			prenomAjoutField.setText("");
			mailAjoutField.setText("");
			refresh();
		}
	}
	
}