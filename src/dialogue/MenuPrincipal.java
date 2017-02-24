package dialogue;

import java.io.IOException;

import commandLine.*;
import inscriptions.Inscriptions;


public class MenuPrincipal 
{
	private static Menu menuPrincipal;
	private static MenuPersonne menuPersonne;
	private static MenuEquipe menuEquipe;
	private static MenuCompetition menuCompetition;
	private static Inscriptions inscriptions;
	public MenuPrincipal()
	{
		inscriptions = Inscriptions.getInscriptions();
		menuPrincipal=getMenuPrincipal();
		menuPersonne = new MenuPersonne();
		menuEquipe = new MenuEquipe();
		menuCompetition = new MenuCompetition();
	}
	
	public void start()
	{
		menuPrincipal.start();
	}
	
	public static Inscriptions getInscriptions()
	{
		return inscriptions;
	}
	
	//Menu principal
	static Menu getMenuPrincipal()
	{
	        Menu menuPrincipal = new Menu("Menu Principal");
	        menuPrincipal.ajoute(menuPersonne.getMenuPersonne());
	        menuPrincipal.ajoute(menuEquipe.getMenuEquipe());
	        menuPrincipal.ajoute(menuCompetition.getMenuCompetition());
	        menuPrincipal.ajoute(getOptionSauvegarde());
	        menuPrincipal.ajouteQuitter("q");
	        return menuPrincipal;
	}
	
	//Sauvergarder
	
	static Option getOptionSauvegarde()
	{
		return new Option("Synchroniser","s",getActionSauvegarde());
	}
	
	static Action getActionSauvegarde()
	{
		return new Action()
				{
					@Override
					public void optionSelectionnee() 
					{
						inscriptions=Inscriptions.getInscriptions();
						System.out.println("Synchronisation réussi !");
					}
				};
	}
	

}
