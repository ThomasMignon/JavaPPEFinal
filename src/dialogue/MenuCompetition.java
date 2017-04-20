package dialogue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Inscriptions;
import commandLine.*;

public class MenuCompetition
{
	private static Inscriptions inscriptions;
	public MenuCompetition()
	{
		inscriptions = MenuPrincipal.getInscriptions();
	}
	
	static Menu getMenuCompetition()
	{
		Menu menuCompetition = new Menu("Gestion de compétition","3");
		menuCompetition.ajoute(getListeCompetition());
		menuCompetition.ajoute(getOptionAjouterCompetition());
		menuCompetition.ajouteRevenir("r");
		return menuCompetition;
	}
	
	//Gestion comp�tition
	
	static Liste<Competition> getListeCompetition()
	{
		Liste<Competition> liste = new Liste<>("Gestion de compétition","1",getActionListeGestionCompetition());
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
				menuCompetition.ajoute(getOptionSupprimerUneCompetition(element));
				menuCompetition.ajoute(getOptionEditerUneCompetition(element));
				menuCompetition.ajoute(getListeSupprimerUneCandidatCompetition(element));
				menuCompetition.ajouteRevenir("r");
				menuCompetition.setRetourAuto(true);
				return menuCompetition;
			}
			
		};
	}
	
	static Option getOptionVoirUneCompetition(Competition competition)
	{
		Option option = new Option("Détails de "+competition.getNom(),"1",getActionAfficherUneCompetition(competition));
		return option;
	}
	
	//Supprimer une personne d'une competition
	
	static Liste<Candidat> getListeSupprimerUneCandidatCompetition(Competition competition)
	{
		Liste<Candidat> liste = new Liste<>("Supprimer un candidat de "+competition.getNom(),"4",getListeActionSupprimerUnCandidatCompetition(competition));
		liste.ajouteRevenir("r");
		return liste;
	}
	
	//Liste supprimer un candidat d'une competition
	static ActionListe<Candidat>getListeActionSupprimerUnCandidatCompetition(Competition competition)
	{
		return new ActionListe<Candidat>()
				{

					@Override
					public List<Candidat> getListe() 
					{
						return new ArrayList<>(competition.getCandidats());
					}

					@Override
					public void elementSelectionne(int indice, Candidat element) 
					{
						competition.remove(element,true);
						System.out.println(element.getNom()+" à bien été supprimer de "+competition.getNom());
					}

					@Override
					public Option getOption(Candidat element) 
					{
						return null;
					}
			
				};
	}
	
	//Liste voir une comp�tition
	
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
	
	
	
	//Ajouter une comp�tition
	
	static Option getOptionAjouterCompetition()
	{
		return new Option("Ajouter une competition","2",getActionAjouterCompetition());
	}
	
	private static Action getActionAjouterCompetition()
	{
		return new Action()
				{
					@Override
					public void optionSelectionnee() 
					{
						String nom= commandLine.util.InOut.getString("Nom : ");
						System.out.println("Date de cloture : ");
						String jour=commandLine.util.InOut.getString("Jour : "),
								mois=commandLine.util.InOut.getString("Mois : "),
								annee=commandLine.util.InOut.getString("Annee : ");
						String dateClotureString = annee+"-"+mois+"-"+jour;
						LocalDate dateCloture = LocalDate.parse(dateClotureString);
						boolean enEquipe = false;
						String reponse="";
						while(!reponse.equals("o")&&!reponse.equals("n"))
						{
							reponse=commandLine.util.InOut.getString("En équipe ? o : Oui n : Non : ");
						}
						enEquipe=reponse.compareTo("o")==0;
						inscriptions.createCompetition(nom, dateCloture, enEquipe,true);
						System.out.println(nom+ "à bien été rajouter");
					}
				};
	}
	
	//Editer une competition
	
	private static Option getOptionEditerUneCompetition(Competition competition)
	{
		Option option = new Option("Editer "+competition.getNom(),"3",getActionEditerUneCompetition(competition));
		return option;
	}
	
	private static Action getActionEditerUneCompetition(Competition competition)
	{
		return new Action()
				{

					@Override
					public void optionSelectionnee() 
					{
						String nom= commandLine.util.InOut.getString("Nom : ");
								inscriptions.editeCompetition(competition,nom);
					}
			
				};
	}
	//Supprimer une comp�tition
	private static Option getOptionSupprimerUneCompetition(Competition competition)
	{
		Option option = new Option("Supprimer "+competition.getNom(),"2",getActionSupprimerUneCompetition(competition));
		return option;
	}
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
