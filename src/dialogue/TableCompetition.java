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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import dialogue.TableEquipe.boutonAjouterListener;
import dialogue.TableEquipe.boutonSupprimerListener;
import dialogue.TableEquipe.editFieldListener;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;

public class TableCompetition extends JPanel{
	//Notre Panneau de pagination
    private PaginationPanel paginationPanel;
    //Un observateur
    private PaginationObserver paginationObserver;
    //Panneau Principale
    private PanneauCompetition panneauCompetition;
    //Le panneau qui va afficher les données et le panneau principal   

    public JPanel dataLayer, contentPane;
    private Inscriptions inscriptions = Panneau.getInscriptions();
    public SortedSet<Competition> compet = inscriptions.getCompetitions();
    ArrayList competitions = new ArrayList(compet);
    Object selectE;
    
    public TableCompetition(PanneauCompetition p)
    {
    	this.initComponents();
    	this.panneauCompetition = p;
    }
    
    private void initComponents() 
    {
        dataLayer = new JPanel();
        
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        
        /*
         * Construction de notre systéme de pagination pour la liste
         * fournie par la méthode getList()
         */
        paginationPanel = new PaginationPanel<Competition>(getList(),10);
        //Instanciation avec classe anonyme de notre observateur
        paginationObserver = new PaginationObserver<Competition>()
        {

            /*
             * Implmentation de la méthode update de l'interface
             */
            @Override
            public void update(List<Competition> competitions) 
            {
                dataLayer.removeAll();

                dataLayer.repaint();

                dataLayer.setPreferredSize(new Dimension((int) (Fenetre.WIDTH * 0.45),(int) (Fenetre.HEIGHT * 0.8)));

                dataLayer.setPreferredSize(new Dimension((int) (Fenetre.WIDTH * 0.45),(int) (Fenetre.HEIGHT * 0.7)));
//                dataLayer.add(new JLabel("Nom de l'�quipe"));

                dataLayer.setPreferredSize(new Dimension((int) (Fenetre.WIDTH * 0.45),(int) (Fenetre.HEIGHT * 0.8)));

                dataLayer.setPreferredSize(new Dimension((int) (Fenetre.WIDTH * 0.45),(int) (Fenetre.HEIGHT * 0.7)));
//                dataLayer.add(new JLabel("Date de cloture"));

                for(Competition c : competitions)
                {
                	if(!c.getIsDelete())
                	{
                		JPanel panel = new JPanel();
                    	JTextField nomCompetition = new JTextField(c.getNom());
                    	nomCompetition.setPreferredSize(new Dimension(120,25));
                    	nomCompetition.addKeyListener(new editFieldListener(c,nomCompetition));
                    	panel.add(nomCompetition);
                    	JTextField dateCompetition = new JTextField(c.getDateCloture().toString());
                    	dateCompetition.setPreferredSize(new Dimension(120,25));
                    	panel.add(dateCompetition);
//                    	JButton ajouter = new JButton("Ajouter membre");
//                    	panel.add(ajouter);
//                    	ajouter.addActionListener(new boutonAjouterListener(c));
//                        panel.setPreferredSize(new Dimension(460, 35));
                        JButton supprimer = new JButton("Supprimer");
                        supprimer.addActionListener(new boutonSupprimerListener(c));
                        panel.add(supprimer);
                       
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
    	
    private List<Competition> getList()
    {
    	return competitions;
    }
 
    class editFieldListener implements KeyListener
	{
    	private Competition competition;
    	private JTextField jt;
    	 
	    public editFieldListener(Competition competition,JTextField jt) {
	        super();
	        this.competition = competition;
	        this.jt = jt;
	    }

		@Override
		public void keyPressed(KeyEvent e) 
		{
			// TODO Auto-generated method stub
			
		}


		@Override
		public void keyReleased(KeyEvent e) {
			inscriptions.editeCompetition(competition,jt.getText());	
		}


		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
    
    class boutonSupprimerListener implements ActionListener
	{
    	private Competition c;
    	 
	    public boutonSupprimerListener(Competition c) {
	        super();
	        this.c = c;
	    }
    	    
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{		
			inscriptions.remove(c);
			//refresh();
		}
	}
}

