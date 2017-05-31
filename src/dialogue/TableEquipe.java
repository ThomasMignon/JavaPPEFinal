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

import dialogue.PanneauEquipe.addEquipeListener;
import dialogue.PanneauEquipe.ajoutFieldListener;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class TableEquipe extends JPanel{
    //Notre Panneau de pagination
    private PaginationPanel paginationPanel;
    //Un observateur
    private PaginationObserver paginationObserver;
    //Panneau Principale
    private PanneauEquipe panneauEquipe;
    //Le panneau qui va afficher les données et le panneau principal   

    public JPanel dataLayer, contentPane;
    private Inscriptions inscriptions = Panneau.getInscriptions();
    public SortedSet<Equipe> equi = inscriptions.getEquipes();
    ArrayList equipes = new ArrayList(equi);
    Object selectE;
    private JTextField nomAjoutField; 
    private JButton boutonAjout;
    
    
    public TableEquipe(PanneauEquipe p)
    {
    	this.initComponents();
    	this.panneauEquipe = p;
    }

    private void initComponents() {
        dataLayer = new JPanel();
        
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        
        /*
         * Construction de notre systéme de pagination pour la liste
         * fournie par la méthode getList()
         */
        paginationPanel = new PaginationPanel<Equipe>(getList(),10);
        //Instanciation avec classe anonyme de notre observateur
        paginationObserver = new PaginationObserver<Equipe>(){

            /*
             * Implmentation de la méthode update de l'interface
             */
            @Override
            public void update(List<Equipe> equipes) {
                dataLayer.removeAll();

                dataLayer.repaint();
                
                dataLayer.setPreferredSize(new Dimension((int) (Fenetre.WIDTH * 0.45),(int) (Fenetre.HEIGHT * 0.7)));
                for(Equipe e : equipes){
                	if(!e.getIsDelete())
                	{
                		JPanel panel = new JPanel();
                    	JTextField nomEquipe = new JTextField(e.getNom());
                    	nomEquipe.setPreferredSize(new Dimension(120,25));
                    	nomEquipe.addKeyListener(new editFieldListener(e,nomEquipe));
                    	panel.add(new JLabel("Nom : "));
                    	panel.add(nomEquipe);
                    	JButton ajouter = new JButton("Ajouter membre");
                    	panel.add(ajouter);
                    	ajouter.addActionListener(new boutonAjouterListener(e));
                        panel.setPreferredSize(new Dimension(460, 35));
                        JButton supprimer = new JButton("Supprimer");
                        supprimer.addActionListener(new boutonSupprimerListener(e));
                        panel.add(supprimer);
                       
                        dataLayer.add(panel);
                	}
                }
                //Ajout d'une �quipe
                JPanel addPanel = new JPanel();
                
                //Instantiation des champs pour le panel d'ajout
                nomAjoutField = new JTextField();
                boutonAjout = new JButton("Ajouter");
              
                //Listener des champs
                boutonAjout.addActionListener(new addEquipeListener());
        		nomAjoutField.addKeyListener(new ajoutFieldListener());
                
        		addPanel.add(new JLabel("Nom : "));
        		nomAjoutField.setPreferredSize(new Dimension(120,20));
        		addPanel.add(nomAjoutField);
        		addPanel.add(boutonAjout);
        		addPanel.setPreferredSize(new Dimension(460, 35));
        		dataLayer.add(addPanel);
                
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
     * Création du listener sur l'édition
     * 
     */
    
    class editFieldListener implements KeyListener
	{
    	private Equipe equipe;
    	private JTextField jt;
    	 
	    public editFieldListener(Equipe equipe,JTextField jt) {
	        super();
	        this.equipe = equipe;
	        this.jt = jt;
	    }

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void keyReleased(KeyEvent e) {
			inscriptions.editeEquipe(equipe,jt.getText());	
		}


		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
    
    
    /**
     * 
     * Création du listener sur le bouton Supprimer
     * 
     */
    
    class boutonSupprimerListener implements ActionListener
	{
    	private Equipe e;
    	 
	    public boutonSupprimerListener(Equipe e) {
	        super();
	        this.e = e;
	    }
    	    
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{		
			inscriptions.remove(e);
			refresh();
		}
	}
    
    public void refresh() 
    {
    	this.remove(contentPane);
    	this.initComponents();
    	dataLayer.repaint();
		panneauEquipe.refresh();
	}
    	
    /**
     * 
     * Création du listener sur le bouton Edit
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
		}
	}
    
    class boutonAjouterListener implements ActionListener
	{
    	private Equipe e;
    	private String nom;
    	 
	    public boutonAjouterListener(Equipe e) {
	        super();
	        this.e = e;
	    }
    	    
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			refresh();
			JComboBox box = new JComboBox();
			box.addItem(inscriptions.getPersonnes());
			dataLayer.repaint();
			dataLayer.updateUI();
			panneauEquipe.getPanelAdminEquipe().setAll(e);
			panneauEquipe.getPanelAdminEquipe().setVisible(true);
		}
	}
    
    class ajoutFieldListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) 
		{
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				if(verifRecup())
				{
					inscriptions.createEquipe(nomAjoutField.getText());
					System.out.println("AJOUT");
					nomAjoutField.setText("");
					refresh();
					nomAjoutField.requestFocus();
				}
				else
				{
					System.out.println("Déjà inscrit");
					nomAjoutField.setText("");
					nomAjoutField.requestFocus();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) 
		{
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) 
		{
			
		}
		
	}
    
    class addEquipeListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(verifRecup())
			{
				inscriptions.createEquipe(nomAjoutField.getText());
				System.out.println("AJOUT");
				nomAjoutField.setText("");
				refresh();
			}
			else
			{
				System.out.println("D�j�inscrit");
				nomAjoutField.setText("");
			}
		}
	}
    
    public boolean verifRecup()
	{
		for(Equipe e : inscriptions.getEquipes())
		{
			if(e.getNom().equals(nomAjoutField.getText()))
			{
				System.out.println(e.getNom()+"="+nomAjoutField.getText());
				return false;
			}
		}
		return true;
	}
    
    /*
     * Création d'une liste de données é paginer
     */
    private List<Equipe> getList(){
        
        return equipes;
    }
}
