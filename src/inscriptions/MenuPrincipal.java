package inscriptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utilitaires.ligneDeCommande.*;

public class MenuPrincipal 
{
	private static Menu menuPrincipal;
	private static Inscriptions inscriptions;
	public MenuPrincipal()
	{
		inscriptions = Inscriptions.getInscriptions();
		this.menuPrincipal=getMenuPrincipal();
	}
	
	public void start()
	{
		menuPrincipal.start();
	}
	
	public Inscriptions getInscriptions()
	{
		return this.inscriptions;
	}
	
	//Menu principal
	static Menu getMenuPrincipal()
	{
	        Menu menuPrincipal = new Menu("Menu Principal");
	        menuPrincipal.ajoute(getMenuVoirUnePersonne());
	        menuPrincipal.ajoute(getOptionAjouterPersonne());
	        menuPrincipal.ajoute(getOptionAjouterEquipe());
	        menuPrincipal.ajoute(getOptionSauvegarde());
	        menuPrincipal.ajouteQuitter("q");
	        return menuPrincipal;
	}
	
	
	//Sauvergarder
	
	static Option getOptionSauvegarde()
	{
		return new Option("Sauvegarder","s",getActionSauvegarde());
	}
	
	static Action getActionSauvegarde()
	{
		return new Action()
				{
					@Override
					public void optionSelectionnee() 
					{
						try 
						{
							inscriptions.sauvegarder();
							System.out.println("Sauvegarde r�ussi !");
							
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
					}
				};
	}
	
	//Voir une personne ou �quipe
	
	static Menu getMenuVoirUnePersonne()
	{
		Menu menuVoirUnePersonne = new Menu("Personne ou �quipe ?", "Voir une personne ou une �quipe","1");
		menuVoirUnePersonne.ajoute(getListeVoirUnePersonne());
		menuVoirUnePersonne.ajoute(getListeVoirUneEquipe());
		menuVoirUnePersonne.ajouteRevenir("r");
		return menuVoirUnePersonne;
	}
	
	static Liste<Personne> getListeVoirUnePersonne()
	{
		Liste<Personne> liste = new Liste<>("Liste des personnes","1",getActionListeVoirUnePersonne());
		liste.ajouteRevenir("r");
		return liste;
	}
	
	static Liste<Equipe> getListeVoirUneEquipe()
	{
		
		Liste<Equipe> liste= new Liste<>("Liste des �quipes","2",getActionListeVoirUneEquipe());
		liste.ajouteRevenir("r");
		return liste;
	}
	
	//Liste personne
	static ActionListe<Personne> getActionListeVoirUnePersonne() 
	{
		return new ActionListe<Personne>() {
			
			@Override
			public List<Personne> getListe()
			{
				return new ArrayList<>(inscriptions.getPersonnes());
			}
			
			@Override
			public void elementSelectionne(int indice, Personne element) 
			{
				System.out.println(" Pr�nom : "+element.getPrenom()+" Nom : "+element.getNom()+" Mail : "+element.getMail());
				Menu menuPersonne = new Menu("Actions disponibles");
				menuPersonne.ajoute(getOptionSupprimerPersonne(element));
				menuPersonne.ajouteRevenir("r");
				menuPersonne.start();
			}
		};
		
	}
	
	//Liste equipe
	static ActionListe<Equipe> getActionListeVoirUneEquipe() 
	{
		return new ActionListe<Equipe>() {
			
			@Override
			public List<Equipe> getListe()
			{
				return new ArrayList<>(inscriptions.getEquipes());
			}
			
			@Override
			public void elementSelectionne(int indice, Equipe element) 
			{
				System.out.println("Nom : "+element.getNom());
				Menu menuEquipe = new Menu("Actions disponibles");
				menuEquipe.ajoute(getOptionSupprimerEquipe(element));
				menuEquipe.ajouteRevenir("r");
				menuEquipe.start();
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
			@Override
			public void optionSelectionnee()
			{
				String nom= utilitaires.EntreesSorties.getString("Nom : "),
                prenom = utilitaires.EntreesSorties.getString("Pr�nom : "),
                mail = utilitaires.EntreesSorties.getString("Mail : ");
				inscriptions.createPersonne(nom, prenom, mail);
			}
		};
	}
	
	//Ajouter equipe
	
	static Option getOptionAjouterEquipe()
	{
		return new Option("Ajouter une equipe","3",getActionAjouterEquipe());
	}
	
	private static Action getActionAjouterEquipe()
	{
		return new Action ()
				{
					@Override
					public void optionSelectionnee()
					{
						String nom= utilitaires.EntreesSorties.getString("Nom : ");
								inscriptions.createEquipe(nom);
					}
				};
	}
	
	
	//Ajouter une comp�tition
	
	static Option getOptionAjouterCompetition()
	{
		return new Option("Ajouter une competition","4",getActionAjouterCompetition());
	}
	
	private static Action getActionAjouterCompetition()
	{
		return new Action()
				{
					@Override
					public void optionSelectionnee() 
					{
						String nom= utilitaires.EntreesSorties.getString("Nom : ");
						System.out.println("Date de cloture : ");
						int jour=utilitaires.EntreesSorties.getInt("Jour : "),
								mois=utilitaires.EntreesSorties.getInt("Mois : "),
								annee=utilitaires.EntreesSorties.getInt("Annee : ");
						boolean enEquipe = false;
						String reponse = "";
						while(reponse.equals("o") || reponse.equals("n"))
						{
							//TODO: EnEquipe ou pas 
						}
					}
				};
	}
	//Supprimer une personne 
	
	static Option getOptionSupprimerPersonne(Personne personne)
	{
		return new Option("Supprimer "+personne.getPrenom(),"1",getActionSupprimerPersonne(personne));
	}
	
	private static Action getActionSupprimerPersonne(Personne personne)
	{
		return new Action()
				{
					public void optionSelectionnee()
					{
						personne.delete();
						System.out.println(personne.getPrenom()+" � �t� supprimer !");
						menuPrincipal.start();
					}
				};
	}
	
	//Supprimer une �quipe
	
	static Option getOptionSupprimerEquipe(Equipe equipe)
	{
		return new Option("Suppreimer "+equipe.getNom(),"1",getActionSupprimerEquipe(equipe));
	}
	
	private static Action getActionSupprimerEquipe(Equipe equipe)
	{
		return new Action()
				{
					public void optionSelectionnee()
					{
						equipe.delete();
						System.out.println(equipe.getNom()+" � �t� supprimer !");
						menuPrincipal.start();
					}
				};
	}
}
