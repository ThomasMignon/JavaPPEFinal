package dialogue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class TablePersonne extends JPanel{
    //Notre Panneau de pagination
    private PaginationPanel paginationPanel;
    //Un observateur
    private PaginationObserver paginationObserver;
    //Le panneau qui va afficher les donn�es et le panneau principal
    private JPanel dataLayer, contentPane;
    private Inscriptions inscriptions = Panneau.getInscriptions();
    public SortedSet<Personne> pers = inscriptions.getPersonnes();
    ArrayList personnes = new ArrayList(pers);
    Object selectE;
    PanneauPersonne p;
    int nbParPage = 0;
    
    public TablePersonne(PanneauPersonne p,int nbParPage)
    {
        this.initComponents();
        this.p = p;
        this.nbParPage = nbParPage;
    }

    private void initComponents() {
        dataLayer = new JPanel();
        
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        
        /*
         * Construction de notre syst�me de pagination pour la liste
         * fournie par la m�thode getList()
         */
        paginationPanel = new PaginationPanel<Personne>(getList(),nbParPage);
        //Instanciation avec classe anonyme de notre observateur
        paginationObserver = new PaginationObserver<Personne>(){

            /*
             * Impl�mentation de la m�thode update de l'interface
             */
            @Override
            public void update(List<Personne> personne) {
                dataLayer.removeAll();
                dataLayer.repaint();
                dataLayer.setPreferredSize(new Dimension(Fenetre.WIDTH/2,(int) (Fenetre.HEIGHT * 0.8)));
                dataLayer.add(new JLabel("Nom de l'�quipe :"));
                for(Personne p  : personne){
                	if(!p.getIsDelete())
                	{
                		JPanel panel = new JPanel();
                    	JTextField nomPersonne = new JTextField(p.getNom());
                    	nomPersonne.setPreferredSize(new Dimension(120,25));
                    	panel.add(nomPersonne);
                    	JTextField prenomPersonne = new JTextField(p.getPrenom());
                    	prenomPersonne.setPreferredSize(new Dimension(120,25));
                    	panel.add(prenomPersonne);
                    	JTextField mailPersonne = new JTextField(p.getMail());
                    	mailPersonne.setPreferredSize(new Dimension(120,25));
                    	panel.add(mailPersonne);
                    	nomPersonne.addKeyListener(new editFieldListener(p, nomPersonne,prenomPersonne,mailPersonne));
                    	JButton ajouter = new JButton("Ajouter membre");
                    	panel.add(ajouter);
                    	ajouter.addActionListener(new boutonAjouterListener(p));
                        panel.setPreferredSize(new Dimension(460, 35));
                        JButton supprimer = new JButton("Supprimer");
                        supprimer.addActionListener(new boutonSupprimerListener(p));
                        panel.add(supprimer);
                        panel.setBorder(BorderFactory.createEtchedBorder());
                        dataLayer.add(panel);
                	}
                }
                dataLayer.repaint();
                dataLayer.updateUI();
                System.out.println(personne);
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
     * Cr�ation du listener sur l'�dition
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
    
    
    /**
     * 
     * Cr�ation du listener sur le bouton Supprimer
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
    
    private void refresh() {
		dataLayer.removeAll();
		dataLayer.updateUI();
		dataLayer.repaint();
		
	}
    
    /**
     * 
     * Cr�ation du listener sur le bouton Edit
     * 
     */
    
    class boutonEditListener implements ActionListener
	{
    	private Equipe e;
    	private String nom;
    	 
	    public boutonEditListener(Equipe e,String nom) {
	        super();
	        this.e = e;
	        this.nom = nom;
	        e.setNom(nom);
	    }
    	    
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{		
			inscriptions.editeEquipe(e,e.getNom());
			System.out.println(e.getNom());
			System.out.println(this.nom);
		}
	}
    
    class boutonAjouterListener implements ActionListener
	{
    	private Personne p;
    	private String nom;
    	 
	    public boutonAjouterListener(Personne p) {
	        super();
	        this.p = p;
	    }
    	    
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{		
			JComboBox box = new JComboBox();
			box.addItem(inscriptions.getPersonnes());
			dataLayer.repaint();
			dataLayer.updateUI();
			System.out.println("oui");
		}
	}
    /*
     * Cr�ation d'une liste de donn�es � paginer
     */
    private List<Personne> getList(){
        
        return this.personnes;
    }
}