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
	        menuPrincipal.ajouteQuitter("q");
	        return menuPrincipal;
	}

}
