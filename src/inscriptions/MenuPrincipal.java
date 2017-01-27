package inscriptions;

import java.io.IOException;
import java.time.LocalDate;
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
		menuPrincipal=getMenuPrincipal();
	}
	
	public void start()
	{
		menuPrincipal.start();
	}
	
	public Inscriptions getInscriptions()
	{
		return inscriptions;
	}
	
	//Menu principal
	static Menu getMenuPrincipal()
	{
	        Menu menuPrincipal = new Menu("Menu Principal");
	        menuPrincipal.ajoute(getListeVoirUnePersonne());
	        menuPrincipal.ajoute(getListeVoirUneEquipe());
	        menuPrincipal.ajoute(getListeCompetition());
	        menuPrincipal.ajoute(getOptionAjouterPersonne());
	        menuPrincipal.ajoute(getOptionAjouterEquipe());
	        menuPrincipal.ajoute(getOptionAjouterCompetition());
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
							System.out.println("Sauvegarde impossible. " + e);
						}
					}
				};
	}
	
	//Gestion compétition
	
	static Liste<Competition> getListeCompetition()
	{
		Liste<Competition> liste = new Liste<>("Gestion de compétition","3",getActionListeGestionCompetition());
		liste.ajouteRevenir("r");
		return liste;
	}
	
	static ActionListe<Competition> getActionListeGestionCompetition()
	{
		return new ActionListe<Competition>()
		{

			@Override
			public List<Competition> getListe()
			{
				return new ArrayList<>(inscriptions.getCompetitions());
			}

			@Override
			public void elementSelectionne(int indice, Competition element) 
			{
				
			}

			@Override
			public Menu getOption(Competition element) 
			{
				//TODO: Menu competition
				Menu menuCompetition = new Menu("Options pour "+element.getNom(),null);
				menuCompetition.ajoute(getOptionVoirUneCompetition(element));
				menuCompetition.ajoute(getOptionSupprimerUneCompetition(element));;
				menuCompetition.ajouteRevenir("r");
				return menuCompetition;
			}
			
		};
	}
	
	static Option getOptionVoirUneCompetition(Competition competition)
	{
		Option option = new Option("Détails de "+competition.getNom(),"1",getActionAfficherUneCompetition(competition));
		return option;
	}
	
	static Option getOptionSupprimerUneCompetition(Competition competition)
	{
		Option option = new Option("Supprimer "+competition.getNom(),"2",getActionSupprimerUneCompetition(competition));
		return option;
	}
	
	//Gérer une personne ou équipe
	
	static Liste<Personne> getListeVoirUnePersonne()
	{
		Liste<Personne> liste = new Liste<>("Gestion de personne","1",getActionListeVoirUnePersonne());
		liste.ajouteRevenir("r");
		return liste;
//		menuVoirUnePersonne.ajoute(getListeSupprimerUnePersonne());
//		menuVoirUnePersonne.ajoute(getListeAjouterUnePersonneCompetition());
//		menuVoirUnePersonne.ajoute(getListeVoirUneEquipe());
//		menuVoirUnePersonne.ajoute(getListeSupprimerUneEquipe());
	}
	
	static Liste<Equipe> getListeVoirUneEquipe()
	{
		
		Liste<Equipe> liste= new Liste<>("Gestion d'équipe","2",getActionListeVoirUneEquipe());
		liste.ajouteRevenir("r");
		return liste;
	}
	
	static Liste<Competition> getListeAjouterUnePersonneCompetition(Personne personne)
	{
		Liste<Competition> liste = new Liste<>("Ajouter "+personne.getPrenom()+" à une compétition","3",getActionListeCompetitionAjoutPersonne(personne));
		return liste;
	}
	static Liste<Competition> getListeAjouterUneEquipeCompetition(Equipe equipe)
	{
		Liste<Competition> liste = new Liste<>("Ajouter "+equipe.getNom()+" à une compétition","2",getActionListeAjouterUneEquipeCompetition(equipe));
		liste.ajouteRevenir("r");
		return liste;
	}
	
	static Liste<Personne> getListeSupprimerUnePersonne()
	{
		Liste<Personne> liste = new Liste<>("Supprimer une personne","4",getActionListeSupprimerUnePersonne());
		liste.ajouteRevenir("r");
		return liste;
	}
	
	static Liste<Equipe> getListeSupprimerUneEquipe()
	{
		Liste<Equipe> liste = new Liste<>("Supprimer une équipe","5",getActionListeSupprimerUneEquipe());
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
			public Menu getOption(Personne element) 
			{
				Menu menuPersonne = new Menu("Option pour  "+element.getPrenom()+" "+element.getNom(),null);
				menuPersonne.ajoute(getOptionVoirUnePersonne(element));
				menuPersonne.ajoute(getOptionSupprimerPersonne(element));
				menuPersonne.ajoute(getListeAjouterUnePersonneCompetition(element));
				menuPersonne.ajoute(getListeAjouterUnePersonneEquipe(element));
				//TODO:D'autres options pour l'utilisateur
				menuPersonne.ajouteRevenir("r");
				return menuPersonne;
			}
		};
		
	}
	
	private static Option getOptionVoirUnePersonne(Personne element)
	{
		return new Option("Détails sur "+element.getPrenom(),"1",getActionVoirPersonne(element));
	}
	
	private static Action getActionVoirPersonne(Personne element) 
	{
		return new Action()
				{
					public void optionSelectionnee()
					{
						System.out.println("Prénom : "+element.getPrenom()+" | Nom : "+element.getNom()+" | Mail : "+element.getMail());
						if(!element.getEquipes().isEmpty())
						{
							System.out.println(element.getEquipes().toString());
						}
						else
						{
							System.out.println(element.getPrenom()+ " n'a pas d'équipe");
						}
						
						if(!element.getCompetitions().isEmpty())
						{
							System.out.println("Participe à "+element.getCompetitions().toString());
						}
						else
						{
							System.out.println(element.getPrenom()+" ne participe à aucune compétition");
						}
					}
				};
	}
	
	//Liste voir equipe
	private static ActionListe<Equipe> getActionListeVoirUneEquipe() 
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
			public Menu getOption(Equipe element) 
			{
				//return new Option("Afficher "+element.getNom(),null, getActionAfficherEquipe(element));
				Menu menuEquipe = new Menu("Option pour "+element.getNom(),null);
				//TODO : Ajouter des options pour les équipes
				menuEquipe.ajoute(getOptionVoirUneEquipe(element));
				menuEquipe.ajoute(getListeAjouterUneEquipeCompetition(element));
				menuEquipe.ajouteRevenir("r");
				return menuEquipe;
			}
		};
		
	}
	
	private static Option getOptionVoirUneEquipe(Equipe equipe)
	{
		return new Option("Détails de l'équipe "+equipe.getNom(),"1",getActionAfficherEquipe(equipe));
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
							System.out.println("Membre de l'équipe : "+element.getCompetitions().toString());
						}
						else
						{
							System.out.println(element.getNom()+"ne participe à aucune compétition");
						}
					}
				};
	}
	
	//Liste voir une compétition
	
	private static Action getActionAfficherUneCompetition(Competition element) 
	{
		return new Action()
				{
					@Override
					public void optionSelectionnee() 
					{
						if(!element.getCandidats().isEmpty())
						{
							System.out.println(element.getCandidats().toString());
						}
						else
						{
							System.out.println("Cette compétition n'as pas encore de participants");
						}
						System.out.println("Date de cloture : "+element.getDateCloture());
					}
				};
	}

	//Liste supprimer une compétition
	

	
	//Liste ajouter une personne dans une compétition
	
		private static ActionListe<Competition> getActionListeCompetitionAjoutPersonne(Personne personne)
		{
			return new ActionListe<Competition>()
			{

				@Override
				public List<Competition> getListe()
				{
					return new ArrayList<>(inscriptions.getCompetitions());
				}

				@Override
				public void elementSelectionne(int indice, Competition element) 
				{
					element.add(personne);
					System.out.println(personne.getPrenom()+" à bien été ajouter à "+element.getNom());
				}

				@Override
				public Option getOption(Competition element) 
				{
					//return new Option("Supprimer "+element.getNom(),null,getActionSupprimerUneCompetition(element));
//					Option option = new Option("Ajouter une personne à "+element.getNom(),null,getActionAjouterUnePersonneCompetition(personne,element));
//					return option;
					return null;
				}
				
			};
		}
		
		//Liste ajouter une equipe dans une competition
		
		private static ActionListe<Competition> getActionListeAjouterUneEquipeCompetition(Equipe equipe)
		{
			return new ActionListe<Competition>()
					{

						@Override
						public List<Competition> getListe() 
						{
							return new ArrayList<>(inscriptions.getCompetitions());
						}

						@Override
						public void elementSelectionne(int indice, Competition element) 
						{
							element.add(equipe);
							System.out.println(equipe.getNom()+" à bien été ajouter à "+element.getNom());
						}

						@Override
						public Option getOption(Competition element) 
						{
							return null;
						}
				
					};
				
		}
		
		//Liste ajouter une personne dans une équipe
		
		private static Liste<Equipe> getListeAjouterUnePersonneEquipe(Personne personne)
		{
			Liste<Equipe> liste = new Liste<>("Ajouter "+personne.getPrenom()+" dans une équipe","4",getActionListeAjouterUnePersonneEquipe(personne));
			liste.ajouteRevenir("r");
			return liste;
		}
		
		private static ActionListe<Equipe> getActionListeAjouterUnePersonneEquipe(Personne personne)
		{
			return new ActionListe<Equipe>()
					{
						@Override
						public List<Equipe> getListe() {
							return new ArrayList<>(inscriptions.getEquipes());
						}

						@Override
						public void elementSelectionne(int indice, Equipe element) 
						{
							element.add(personne);
						}

						@Override
						public Option getOption(Equipe element) 
						{
							return null;
						}
					};
		}
		
		
		//Liste ajouter une personne dans une compétition + action
		
//		private static ActionListe<Personne> getActionListeAjouterUnePersonneCompetition(Competition competition)
//		{
//			return new ActionListe<Personne>()
//					{
//
//						@Override
//						public List<Personne> getListe() 
//						{
//							return new ArrayList<>(inscriptions.getPersonnes());
//						}
//
//						@Override
//						public void elementSelectionne(int indice, Personne element) 
//						{
//							
//						}
//
//						@Override
//						public Option getOption(Personne element) 
//						{
//							return new Option("Ajouter "+element.getPrenom()+" à "+competition.getNom(),null,getActionAjouterUnePersonneCompetition(element,competition));
//						}
//
//				
//					};
//		}
	
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
		return new Option("Ajouter une personne","4",getActionAjouterPersonne());
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
		return new Option("Ajouter une equipe","5",getActionAjouterEquipe());
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
		return new Option("Ajouter une competition","6",getActionAjouterCompetition());
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
						String reponse="";
						while(!reponse.equals("o")&&!reponse.equals("n"))
						{
							reponse=utilitaires.EntreesSorties.getString("En équipe ? o : Oui n : Non : ");
						}
						enEquipe=reponse.compareTo("o")==0;
						inscriptions.createCompetition(nom, dateCloture, enEquipe);
					}
				};
	}
	//Supprimer une personne 
	
	
	static Option getOptionSupprimerPersonne(Personne personne)
	{
		return new Option("Supprimer "+personne.getPrenom(),"2",getActionSupprimerPersonne(personne));
	}
	
	private static Action getActionSupprimerPersonne(Personne personne)
	{
		return new Action()
				{
					public void optionSelectionnee()
					{
						personne.delete();
						System.out.println(personne.getPrenom()+" à été supprimer !");
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
					}
				};
	}
	
	//Supprimer une compétition
	private static Action getActionSupprimerUneCompetition(Competition element)
	{
		return new Action()
				{

					@Override
					public void optionSelectionnee() 
					{
						element.delete();
						System.out.println(element.getNom()+" à été supprimer !");
					}
			
				};
	}
}
