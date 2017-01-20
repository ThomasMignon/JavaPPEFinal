package inscriptions;

import java.util.ArrayList;
import java.util.List;

import utilitaires.ligneDeCommande.*;

public class MenuPrincipal 
{
	private Menu menuPrincipal = new Menu("Menu Principal");
	private static Inscriptions inscriptions;
	public MenuPrincipal()
	{
		inscriptions = Inscriptions.getInscriptions();
		Menu menuPrincipal = new Menu("Menu Principal");
		
		this.menuPrincipal=menuPrincipal;
	}
	
	public void start()
	{
		menuPrincipal.start();
	}
	
	//Menu principal
	static Menu getMenuPrincipal()
	{
	        Menu menuPrincipal = new Menu("Menu Principal");
	        menuPrincipal.ajoute(getOptionAjouterPersonne());
	        menuPrincipal.ajouteQuitter("q");
	        return menuPrincipal;
	}
	
	
	//Voir une personne 
	
	static Menu getMenuVoirUnePersonne()
	{
		Menu menuVoirUnePersonne = new Menu("Voir une personne", "1");
		menuVoirUnePersonne.ajoute(getListeVoirUnePersonne());
		return menuVoirUnePersonne;
	}
	
	static Liste<Personne> getListeVoirUnePersonne()
	{
		return new Liste<>("Liste des personnes",getActionListeVoirUnePersonne());
	}
	
	static ActionListe<Personne> getActionListeVoirUnePersonne() 
	{
		return new ActionListe<Personne>() {
			
			@Override
			public List<Personne> getListe()
			{
				return new ArrayList<>(inscriptions.getPersonnes());
			}
			
			@Override
			public void elementSelectionne(int indice, Personne element) {
				System.out.println("Id : "+element.getId_personne()+" Prénom : "+element.getNom()+" Nom : "+element.getPrenom());
				
			}
		};
		
	}

	//Ajouter personne
	static Option getOptionAjouterPersonne()
	{
		return new Option("Ajouter une personne","2",getActionAjouterPersonne());
	}

	private static Action getActionAjouterPersonne() 
	{
		return new Action ()
		{
			public void optionSelectionnee()
			{
				String nom= utilitaires.EntreesSorties.getString("Nom : "),
                prenom = utilitaires.EntreesSorties.getString("Prénom : "),
                mail = utilitaires.EntreesSorties.getString("Mail : ");
				inscriptions.createPersonne(nom, prenom, mail);
			}
		};
	}
}
