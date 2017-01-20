package inscriptions;

import utilitaires.ligneDeCommande.*;

public class MenuPrincipal 
{
	private Menu menuPrincipal = new Menu("Menu Principal");
	private Inscriptions inscriptions;
	public MenuPrincipal()
	{
		inscriptions = Inscriptions.getInscriptions();
		Menu menuPrincipal = new Menu("Menu Principal");
		Menu voirPersonne = new Menu("Voir une personne","1");
		Menu ajouterPersonne = new Menu("Ajouter une personne","2");
		Menu supprimerPersonne = new Menu("Supprimer une personne","3");
		menuPrincipal.ajoute(voirPersonne);
		menuPrincipal.ajoute(ajouterPersonne);
		menuPrincipal.ajoute(supprimerPersonne);
		menuPrincipal.ajouteQuitter("q");
		
		ajouterPersonne.setAction(new Action()
				{
					public void optionSelectionnee()
					{
						menuAjouterPersonne();
					}
				});
		
		this.menuPrincipal=menuPrincipal;
	}
	
	public void start()
	{
		menuPrincipal.start();
	}
	
	public void menuAjouterPersonne()
	{
		String nom = "";
		String prenom = "";
		String mail = "";
		System.out.println("Nom : ");
		System.out.println("Prénom : ");
		System.out.println("Mail : ");
	}
}
