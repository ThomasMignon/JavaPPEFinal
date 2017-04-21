package dialogue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dialogue.TableEquipe.boutonAjouterListener;
import dialogue.TableEquipe.boutonSupprimerListener;
import dialogue.TableEquipe.editFieldListener;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class PanneauAdminEquipe extends JPanel {
	
	private Equipe equipeSelectionner;
	Equipe equipe;
	private SortedSet<Personne> membres = new TreeSet<>();
	private SortedSet<Personne> newMembres = new TreeSet<>();
	ArrayList tabMembre = new ArrayList(newMembres);
	
	 private PaginationPanel paginationPanel;
    //Un observateur
    private PaginationObserver paginationObserver;
    //Panneau Principale
    private PanneauEquipe panneauEquipe;
    private JPanel dataLayer, contentPane;
	
	Inscriptions inscriptions = new Inscriptions();
	JButton addMembre = new JButton("Add");
	JButton addSupp = createButton("Supprimer");
	JLabel nomEquipe = new JLabel();
	JPanel panelMembres = new JPanel();
	JPanel panelCompetitions = new JPanel();
	JComboBox listMembres = new JComboBox();
	JComboBox listNewMembres = new JComboBox();
	JPanel panelComboMembres = new JPanel();
	JLabel labelTitre = new JLabel("Liste des membres de l'équipe : ");
	JLabel labelTitre2 = new JLabel("Liste des personnes disponibles : ");
	private Dimension taille = new Dimension((int) (Fenetre.WIDTH * 0.45),(int) (Fenetre.HEIGHT * 0.80));
	private Dimension size = new Dimension((int) (Fenetre.WIDTH * 0.45),80);
	
	public PanneauAdminEquipe(Equipe e)
	{
		super();
		nomEquipe.setPreferredSize(new Dimension((int) taille.getWidth(),40));
		nomEquipe.setBorder(BorderFactory.createTitledBorder("Administration de l'équipe :"));
		nomEquipe.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(nomEquipe);
		this.add(panelMembres);
		setTailleAll();
		this.setVisible(false);
		this.setPreferredSize(taille);
		this.setListener();
	}
	
	public void setListener()
	{
		addSupp.addActionListener(new supprimerMembreListener(RecupOld()));
		addMembre.addActionListener(new ajouterMembreListener(RecupNew()));
	}
	
	public void setTailleAll()
	{
		panelComboMembres.setPreferredSize(size);
		labelTitre.setPreferredSize(size);
		panelMembres.setPreferredSize(new Dimension((int) (Fenetre.WIDTH * 0.45),250));
	}
	
	public Equipe getEquipe(){
		return equipeSelectionner;
	}
	
	public JLabel getNomEquipe() {
		return nomEquipe;
	}
	
	public List getList(){
        
        return tabMembre;
    }
	
	public void setMembres(Equipe e)
	{
		this.membres = e.getMembres();
		newMembres.clear();
		for(Personne p : Panneau.getInscriptions().getPersonnes()){
			if(!membres.contains(p))
				newMembres.add(p);
		}
	}
	
	public JButton createButton(String s)
	{
		return new JButton(s);
	}
	
	
	public void remplirMembres(Equipe e)
	{
		panelMembres.removeAll();
		panelMembres.repaint();
		listMembres.removeAllItems();
		listNewMembres.removeAllItems();
		setMembres(e);
		for(Personne p : membres){
    		listMembres.addItem(p);
        }
		for(Personne pnew : newMembres)
		{
			listNewMembres.addItem(pnew);
		}
		labelTitre.setPreferredSize(new Dimension((int) (Fenetre.WIDTH * 0.25),20));
		labelTitre2.setPreferredSize(new Dimension((int) (Fenetre.WIDTH * 0.25),20));
		panelMembres.add(addSupp);
		panelMembres.add(addMembre);
		if(!(listMembres.getItemCount()==0))
		{
			panelComboMembres.add(labelTitre);
			panelComboMembres.add(listMembres);
			addSupp.setEnabled(true);
		}
		else
		{
			addSupp.setEnabled(false);
			panelComboMembres.remove(labelTitre);
			panelComboMembres.remove(listMembres);
		}
		
		if(!(listNewMembres.getItemCount() == 0))
		{
			panelComboMembres.add(labelTitre2);
			panelComboMembres.add(listNewMembres);
			addMembre.setEnabled(true);
		}
		else
		{
			panelComboMembres.remove(labelTitre2);
			panelComboMembres.remove(listNewMembres);
			addMembre.setEnabled(false);
		}
			
		
		panelMembres.add(panelComboMembres);
		
		
	}
	
	private Personne RecupNew() {
		return (Personne) listNewMembres.getSelectedItem();
	}
	
	private Personne RecupOld() {
		return (Personne) listMembres.getSelectedItem();
	}

	public void remplirCompetitions()
	{
		panelCompetitions.removeAll();
		panelCompetitions.repaint();
		listMembres.removeAllItems();
		for(Personne p : membres){
    		listMembres.addItem(p);
        }
		panelComboMembres.add(labelTitre);
		labelTitre.setPreferredSize(new Dimension((int) (Fenetre.WIDTH * 0.25),20));
		panelComboMembres.add(listMembres);
		panelMembres.add(panelComboMembres);
	}

	public void setNomEquipe(String text) {
		this.nomEquipe.setText(text);
	}	
	
	public void setAll(Equipe equipe)
	{
		this.equipeSelectionner = equipe;
		this.setNomEquipe(equipe.getNom());
		this.setMembres(equipe);
		this.remplirMembres(equipe);
	}

	private void initComponents() {
        dataLayer = new JPanel();
        
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        
        /*
         * Construction de notre syst�me de pagination pour la liste
         * fournie par la m�thode getList()
         */
        paginationPanel = new PaginationPanel<Personne>(getList(),5);
        //Instanciation avec classe anonyme de notre observateur
        paginationObserver = new PaginationObserver<Personne>(){

            /*
             * Impl�mentation de la m�thode update de l'interface
             */
            @Override
            public void update(List<Personne> personnes) {
                dataLayer.removeAll();
                dataLayer.repaint();
                dataLayer.setPreferredSize(new Dimension((int) (Fenetre.WIDTH * 0.45),(int) (Fenetre.HEIGHT * 0.4)));
                dataLayer.add(new JLabel("Nom de l'équipe :"));

                for(Personne p : personnes){
                	if(!p.getIsDelete())
                	{
                		JPanel panel = new JPanel();
                    	JTextField nomEquipe = new JTextField(p.getNom());
                    	nomEquipe.setPreferredSize(new Dimension(120,25));
                    	panel.add(nomEquipe);
                    	JButton ajouter = new JButton("Ajouter membre");
                    	panel.add(ajouter);
                    	ajouter.addActionListener(new ajouterMembreListener(p));
                        panel.setPreferredSize(new Dimension(460, 35));                                        
                        dataLayer.add(panel);
                	}
                }
                dataLayer.repaint();
                dataLayer.updateUI();
                System.out.println(personnes);
            }
            
        };
        //Ajout de l'observateur
        paginationPanel.addPaginationObserver(paginationObserver);
        
        contentPane.add(new JScrollPane(dataLayer));
        contentPane.add(paginationPanel, BorderLayout.SOUTH);
        this.add(contentPane);
        paginationPanel.reset();

    }

	
	class ajouterMembreListener implements ActionListener 
	{
		private Personne p;
    	 
	    public ajouterMembreListener(Personne p) {
	        super();
	        this.p = p;
	    }
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Personne p =(Personne) listNewMembres.getSelectedItem();
			System.out.println(p);
			getEquipe().add(p, true);
			setAll(getEquipe());
		}
	}
	
	class supprimerMembreListener implements ActionListener 
	{
		private Personne p;
    	 
	    public supprimerMembreListener(Personne p) {
	        super();
	        this.p = p;
	    }
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Personne p =(Personne) listMembres.getSelectedItem();
			System.out.println(getEquipe());
			getEquipe().remove(p, true);
			setAll(getEquipe());
		}
	}
}
