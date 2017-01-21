package inscriptions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
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
							System.out.println("Sauvegarde réussi !");
							
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
					}
				};
	}
	
	//Gérer une personne ou équipe
	
	static Menu getMenuVoirUnePersonne()
	{
		Menu menuVoirUnePersonne = new Menu("Personne ou équipe ?", "Gérer une personne ou une équipe","1");
		menuVoirUnePersonne.ajoute(getListeVoirUnePersonne());
		menuVoirUnePersonne.ajoute(getListeVoirUneEquipe());
		menuVoirUnePersonne.ajoute(getListeSupprimerUnePersonne());
		// TODO : menuVoirUnePersonne.ajoute(getListeSupprimerUneEquipe());
		menuVoirUnePersonne.ajouteRevenir("r");
		return menuVoirUnePersonne;
	}
	
	static Liste<Personne> getListeVoirUnePersonne()
	{
		Liste<Personne> liste = new Liste<>("Voir une personne","1",getActionListeVoirUnePersonne());
		liste.ajouteRevenir("r");
		return liste;
	}
	
	static Liste<Equipe> getListeVoirUneEquipe()
	{
		
		Liste<Equipe> liste= new Liste<>("Voir une équipe","2",getActionListeVoirUneEquipe());
		liste.ajouteRevenir("r");
		return liste;
	}
	
	static Liste<Personne> getListeSupprimerUnePersonne()
	{
		Liste<Personne> liste = new Liste<>("Supprimer une personne","3",getActionListeSupprimerUnePersonne());
		liste.ajouteRevenir("r");
		return liste;
	}
	
	//Liste voir personne
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
				
			}

			@Override
			public Option getOption(Personne element) 
			{
				return new Option("Afficher "+element.getPrenom()+" "+element.getNom(),null,getActionAfficherPersonne(element));
			}
		};
		
	}
	
	private static Action getActionAfficherPersonne(Personne element) 
	{
		return new Action()
				{
					public void optionSelectionnee()
					{
						System.out.print("Prénom : "+element.getPrenom()+" Nom : "+element.getNom()+" Mail : "+element.getMail());
						if(!element.getEquipes().isEmpty())
						{
							element.getEquipes().toString();
						}
						else
						{
							System.out.println(element.getPrenom()+"n'a pas d'équipe");
						}
						
						if(!element.getCompetitions().isEmpty())
						{
							element.getCompetitions().toString();
						}
						else
						{
							System.out.println(element.getPrenom()+"ne participe à aucune compétition");
						}
					}
				};
	}
	
	//Liste voir equipe
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
				
			}

			@Override
			public Option getOption(Equipe element) 
			{
				return new Option("Afficher "+element.getNom(),null, getActionAfficherEquipe(element));
			}
		};
		
	}
	
	private static Action getActionAfficherEquipe(Equipe element)
	{
		return new Action()
				{
					public void optionSelectionnee()
					{
						System.out.println("Nom : "+element.getNom());
						if(!element.getCompetitions().isEmpty())
						{
							element.getCompetitions().toString();
						}
						else
						{
							System.out.println(element.getNom()+"ne participe à aucune compétition");
						}
					}
				};
	}
	
	//Liste supprimer une personne 
	private static ActionListe<Personne> getActionListeSupprimerUnePersonne()
	{
		return new ActionListe<Personne>()
		{

			@Override
			public List<Personne> getListe() 
			{
				return new ArrayList<>(inscriptions.getPersonnes());
			}

			@Override
			public void elementSelectionne(int indice, Personne element) 
			{
				
			}

			@Override
			public Option getOption(Personne element) {
				// TODO Auto-generated method stub
				return new Option("Supprimer "+element.getPrenom()+" "+element.getNom(),null,getActionSupprimerPersonne(element));
			}
			
		};
	}
	
	//Liste supprimer une equipe 
	private static ActionListe<Equipe> getActionListeSupprimerUneEquipe()
	{
		return new ActionListe<Equipe>()
				{

					@Override
					public List<Equipe> getListe() 
					{
						return new ArrayList<>(inscriptions.getEquipes());
					}

					@Override
					public void elementSelectionne(int indice, Equipe element) 
					{

					}

					@Override
					public Option getOption(Equipe element) 
					{
						return new Option("Supprimer "+element.getNom(),null,getActionSupprimerEquipe(element));
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
                prenom = utilitaires.EntreesSorties.getString("Prénom : "),
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
	
	
	//Ajouter une compétition
	
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
						String jour=utilitaires.EntreesSorties.getString("Jour : "),
								mois=utilitaires.EntreesSorties.getString("Mois : "),
								annee=utilitaires.EntreesSorties.getString("Annee : ");
						String dateClotureString = annee+"-"+mois+"-"+jour;
						LocalDate dateCloture = LocalDate.parse(dateClotureString);
						boolean enEquipe = false;
						String reponse = "";
						while(reponse.equals("o") || reponse.equals("n"))
						{
							reponse=utilitaires.EntreesSorties.getString("En équipe ? o : Oui n : Non");
						}
						enEquipe=reponse.equals("o");
						inscriptions.createCompetition(nom, dateCloture, enEquipe);
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
						System.out.println(personne.getPrenom()+" à été supprimer !");
						menuPrincipal.start();
					}
				};
	}
	
	//Supprimer une équipe
	
	static Option getOptionSupprimerEquipe(Equipe equipe)
	{
		return new Option("Supprimer "+equipe.getNom(),"1",getActionSupprimerEquipe(equipe));
	}
	
	private static Action getActionSupprimerEquipe(Equipe equipe)
	{
		return new Action()
				{
					public void optionSelectionnee()
					{
						equipe.delete();
						System.out.println(equipe.getNom()+" à été supprimer !");
						menuPrincipal.start();
					}
				};
	}
}
