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
                dataLayer.setPreferredSize(new Dimension((int)(Fenetre.WIDTH * 0.8),(int) (Fenetre.HEIGHT * 0.5)));
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
                        JButton supprimer = new JButton("Supprimer");
                        supprimer.addActionListener(new boutonSupprimerListener(p));
                        panel.add(supprimer);
                        panel.setPreferredSize(new Dimension((int)(Fenetre.WIDTH * 0.9), 35));
                        dataLayer.add(panel);
                	}
                }
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
    
    private void refresh() 
    {
    	this.remove(contentPane);
    	this.initComponents();
		dataLayer.repaint();
		p.refresh();
		System.out.println("Refresh...");
	}
    
    /*
     * Cr�ation d'une liste de donn�es � paginer
     */
    
    private List<Personne> getList(){
        
        return this.personnes;
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
}