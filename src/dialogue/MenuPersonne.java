package dialogue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import inscriptions.Competition;
import inscriptions.DateInvalide;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;
import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.ActionListe;
import utilitaires.ligneDeCommande.Liste;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuPersonne
{
	private static Inscriptions inscriptions;
	public MenuPersonne()
	{
		inscriptions = Inscriptions.getInscriptions();
		
	}
	
	public Inscriptions getInscriptions()
	{
		return inscriptions;
	}
	
	//Menu personne
		static Menu getMenuPersonne()
		{
			Menu menuPersonne = new Menu("Gestion de personne","1");
			menuPersonne.ajoute(getListeVoirUnePersonne());
			menuPersonne.ajoute(getOptionAjouterPersonne());
			menuPersonne.ajouteRevenir("r");
			return menuPersonne;
		}
		
		//Gérer une personne ou équipe
		
		static Liste<Personne> getListeVoirUnePersonne()
		{
			Liste<Personne> liste = new Liste<>("Liste de personne","1",getActionListeVoirUnePersonne());
			liste.ajouteRevenir("r");
			return liste;
//			menuVoirUnePersonne.ajoute(getListeSupprimerUnePersonne());
//			menuVoirUnePersonne.ajoute(getListeAjouterUnePersonneCompetition());
//			menuVoirUnePersonne.ajoute(getListeVoirUneEquipe());
//			menuVoirUnePersonne.ajoute(getListeSupprimerUneEquipe());
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
					menuPersonne.ajoute(getOptionEditerUnePersonne(element));
					//TODO:D'autres options pour l'utilisateur
					menuPersonne.ajouteRevenir("r");
					menuPersonne.setRetourAuto(true);
					return menuPersonne;
				}
			};
			
		}
		
		//Détails personne
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
		
		static Liste<Competition> getListeAjouterUnePersonneCompetition(Personne personne)
		{
			Liste<Competition> liste = new Liste<>("Ajouter "+personne.getPrenom()+" à une compétition","3",getActionListeCompetitionAjoutPersonne(personne));
			liste.ajouteRevenir("r");
			return liste;
		}
		
		//Liste ajouter une personne dans une compétition
		
			private static ActionListe<Competition> getActionListeCompetitionAjoutPersonne(Personne personne)
			{
				return new ActionListe<Competition>()
				{

					@Override
					public List<Competition> getListe()
					{
						ArrayList<Competition> liste = new ArrayList<>();
						Set<Competition> getCompetition = inscriptions.getCompetitions();
						for(Competition competition : getCompetition)
						{
							if(!competition.estEnEquipe())
							{
								liste.add(competition);
							}
						}
						return liste;
					}

					@Override
					public void elementSelectionne(int indice, Competition element) 
					{
						try 
						{
							element.add(personne);
						} 
						catch (DateInvalide e) 
						{
							e.printStackTrace();
						}
						System.out.println(personne.getPrenom()+" à bien été ajouter à "+element.getNom());
					}

					@Override
					public Option getOption(Competition element) 
					{
						//return new Option("Supprimer "+element.getNom(),null,getActionSupprimerUneCompetition(element));
//						Option option = new Option("Ajouter une personne à "+element.getNom(),null,getActionAjouterUnePersonneCompetition(personne,element));
//						return option;
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
			
			//Editer une personne 
			
			private static Option getOptionEditerUnePersonne(Personne personne)
			{
				return new Option("Editer "+personne.getPrenom(),"5",getActionEditerUnePersonne(personne));
			}
			
			private static Action getActionEditerUnePersonne(Personne personne)
			{
				return new Action()
						{

							@Override
							public void optionSelectionnee()
							{
								String nom= utilitaires.EntreesSorties.getString("Nom : "),
						                prenom = utilitaires.EntreesSorties.getString("Prénom : "),
						                mail = utilitaires.EntreesSorties.getString("Mail : ");
										inscriptions.editePersonne(personne,nom, prenom, mail);
								System.out.println(personne.getPrenom()+" à bien été édité !");
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
			
			
}
