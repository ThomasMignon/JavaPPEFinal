package dialogue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import commandLine.*;
import inscriptions.Competition;
import inscriptions.DateInvalide;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class MenuEquipe 
{
	private static Inscriptions inscriptions;
	public MenuEquipe()
	{
		inscriptions = MenuPrincipal.getInscriptions();
	}
	
	static Menu getMenuEquipe()
	{
		Menu menuEquipe = new Menu("Gestion d'équipe","2");
		menuEquipe.ajoute(getListeVoirUneEquipe());
		menuEquipe.ajoute(getOptionAjouterEquipe());
		menuEquipe.ajouteRevenir("r");
		return menuEquipe;
	}
	
	static Liste<Equipe> getListeVoirUneEquipe()
	{
		
		Liste<Equipe> liste= new Liste<>("Liste d'équipe","1",getActionListeVoirUneEquipe());
		liste.ajouteRevenir("r");
		return liste;
	}
	
	//Ajouter equipe
	
		static Option getOptionAjouterEquipe()
		{
			return new Option("Ajouter une equipe","2",getActionAjouterEquipe());
		}
		
		private static Action getActionAjouterEquipe()
		{
			return new Action ()
					{
						@Override
						public void optionSelectionnee()
						{
							String nom= commandLine.util.InOut.getString("Nom : ");
									inscriptions.createEquipe(nom,true);
							System.out.println(nom+" à bien été créer");
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
					//TODO : Ajouter des options pour les �quipes
					menuEquipe.ajoute(getOptionVoirUneEquipe(element));
					menuEquipe.ajoute(getOptionSupprimerEquipe(element));
					menuEquipe.ajoute(getOptionEditerEquipe(element));
					menuEquipe.ajoute(getListeAjouterUneEquipeCompetition(element));
					menuEquipe.ajoute(getListeSupprimerUnePersonneEquipe(element));
					menuEquipe.ajouteRevenir("r");
					menuEquipe.setRetourAuto(true);
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
							if(!element.getMembres().isEmpty())
							{
								System.out.println("Membre de l'équipe: "+element.getMembres().toString());
							}
							else
							{
								System.out.println(element.getNom()+" n'as pas de membres");
							}
							if(!element.getCompetitions().isEmpty())
							{
								System.out.println("Competition : "+element.getCompetitions().toString());
							}
							else
							{
								System.out.println(element.getNom()+" ne participe é aucune compétition");
							}
						}
					};
		}
		//Editer une equipe
		
		static Option getOptionEditerEquipe(Equipe equipe)
		{
			return new Option("Editer "+equipe.getNom(),"3",getActionEditerEquipe(equipe));
		}
		
		private static Action getActionEditerEquipe(Equipe equipe)
		{
			return new Action()
					{
						@Override
						public void optionSelectionnee()
						{
							String nom= commandLine.util.InOut.getString("Nom : ");
							inscriptions.editeEquipe(equipe, nom);
							System.out.println(equipe.getNom()+" à bien été éditer !");
						}
					};
		}
		
		//Supprimer une �quipe
		
		static Option getOptionSupprimerEquipe(Equipe equipe)
		{
			return new Option("Supprimer "+equipe.getNom(),"2",getActionSupprimerEquipe(equipe));
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
		
		static Liste<Competition> getListeAjouterUneEquipeCompetition(Equipe equipe)
		{
			Liste<Competition> liste = new Liste<>("Ajouter "+equipe.getNom()+" à une compétition","4",getActionListeAjouterUneEquipeCompetition(equipe));
			liste.ajouteRevenir("r");
			return liste;
		}
		
		//Liste ajouter une equipe dans une competition
		
		private static ActionListe<Competition> getActionListeAjouterUneEquipeCompetition(Equipe equipe)
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
						if(competition.estEnEquipe())
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
						element.add(equipe);
					} catch (DateInvalide e) 
					{
						e.printStackTrace();
					}
					System.out.println(equipe.getNom()+" a bien été ajouter à "+element.getNom());
				}

				@Override
				public Option getOption(Competition element) 
				{
					return null;
				}
						
			};
						
		}
		
		// Supprimer une personne d'une �quipe
		
		public static Liste<Personne> getListeSupprimerUnePersonneEquipe(Equipe equipe)
		{
			Liste<Personne> liste= new Liste<>("Supprimer une personne de "+equipe.getNom(),"5",getActionListeSupprimerUnePersonneEquipe(equipe));
			liste.ajouteRevenir("r");
			return liste;
		}
		
		public static ActionListe<Personne> getActionListeSupprimerUnePersonneEquipe(Equipe equipe)
		{
			return new ActionListe<Personne>()
			{

				@Override
				public void elementSelectionne(int arg0, Personne personne) 
				{
					equipe.remove(personne,true);
					System.out.println(personne.getNom()+" à bien été supprimer de "+equipe.getNom());
				}

				@Override
				public List<Personne> getListe() 
				{
					return new ArrayList<>(equipe.getMembres());
				}

				@Override
				public Option getOption(Personne arg0) 
				{
					return null;
				}
				
			};
		}
			
	
}
