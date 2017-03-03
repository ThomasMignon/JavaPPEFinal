package inscriptions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import dialogue.Fenetre;
import dialogue.MenuPrincipal;
import persistance.BDD;

/**
 * Point d'entrée dans l'application, un seul objet de type Inscription
 * permet de gérer les compétitions, candidats (de type equipe ou personne)
 * ainsi que d'inscrire des candidats à des compétition.
 */

public class Inscriptions implements Serializable
{
	private static final long serialVersionUID = -3095339436048473524L;
	private static final String FILE_NAME = "Inscriptions.srz";
	private static Inscriptions inscriptions;
	
	private SortedSet<Competition> competitions = new TreeSet<>();
	private SortedSet<Candidat> candidats = new TreeSet<>();
	public Inscriptions()
	{
		
	}
	
	/**
	 * Retourne les compétitions.
	 * @return
	 */
	
	public SortedSet<Competition> getCompetitions()
	{
		return Collections.unmodifiableSortedSet(competitions);
	}
	
	/**
	 * Retourne tous les candidats (personnes et équipes confondues).
	 * @return
	 */
	
	public SortedSet<Candidat> getCandidats()
	{
		return Collections.unmodifiableSortedSet(candidats);
	}

	/**
	 * Retourne toutes les personnes.
	 * @return
	 */
	
	public SortedSet<Personne> getPersonnes()
	{
//		SortedSet<Personne> personnes = new TreeSet<>();
//		for (Candidat c : getCandidats())
//			if (c instanceof Personne)
//				personnes.add((Personne)c);
//		return Collections.unmodifiableSortedSet(personnes);
		return getPersonnes(false);
	}
	
	/**
	 * Retourne toutes les personnes non-supprim�s.
	 * @return
	 */
	
	public SortedSet<Personne> getPersonnes(boolean valide)
	{
		SortedSet<Personne> personnes = new TreeSet<>();
		for (Candidat c : getCandidats())
			if (c instanceof Personne && (!valide || c.getIsDelete()))
					personnes.add((Personne)c);
		return Collections.unmodifiableSortedSet(personnes);
	}

	/**
	 * Retourne toutes les équipes.
	 * @return
	 */
	
	public SortedSet<Equipe> getEquipes()
	{
		SortedSet<Equipe> equipes = new TreeSet<>();
		for (Candidat c : getCandidats())
			if (c instanceof Equipe)
				equipes.add((Equipe)c);
		return Collections.unmodifiableSortedSet(equipes);
	}

	/**
	 * Créée une compétition. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Competition}.
	 * @param nom
	 * @param dateCloture
	 * @param enEquipe
	 * @return
	 */
	
	public Competition createCompetition(String nom, 
			LocalDate dateCloture, boolean enEquipe,boolean save)
	{
		Competition competition = new Competition(this, nom, dateCloture, enEquipe,save);
		competitions.add(competition);
		return competition;
	}

	/**
	 * Créée une Candidat de type Personne. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Personne}.

	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */
	
	public Competition editeCompetition(Competition competition, String nom)
	{
		competition.setNom(nom);
		return competition;
	}
	
	public Personne createPersonne(String nom, String prenom, String mail , boolean save)
	{
		Personne personne = new Personne(this,nom, prenom, mail,save);
		candidats.add(personne);
		return personne;
	}
	
	public Personne editePersonne(Personne personne,String nom,String prenom,String mail)
	{
		personne.setPrenom(prenom);
		personne.setNom(nom);
		personne.setMail(mail);
		return personne;
	}
	
	/**
	 * Créée une Candidat de type équipe. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Equipe}.
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */
	
	public Equipe createEquipe(String nom , boolean save)
	{
		Equipe equipe = new Equipe(this, nom, save);
		candidats.add(equipe);
		return equipe;
	}
	
	public Equipe editeEquipe(Equipe equipe, String nom)
	{
		equipe.setNom(nom);
		return equipe;
	}
	
	
	void remove(Competition competition)
	{
		competitions.remove(competition);
	}
	
	void remove(Candidat candidat)
	{
		candidats.remove(candidat);
		BDD bdd = new BDD();
		bdd.delete(candidat);
	}
	
	/**
	 * Retourne l'unique instance de cette classe.
	 * Crée cet objet s'il n'existe déjà.
	 * @return l'unique objet de type {@link Inscriptions}.
	 */
	
	public static Inscriptions getInscriptions()
	{
		
//		if (inscriptions == null)
//		{
//			inscriptions = readObject();
//			if (inscriptions == null)
//				inscriptions = new Inscriptions();
//		}
		//TODO : 
		BDD bdd = new BDD();
		inscriptions= new Inscriptions();
		bdd.selectPersonne(inscriptions);
		System.out.println("Personne r�cup�r�...");
		bdd.selectEquipe(inscriptions);
		System.out.println("Equipe r�cup�r�...");
		bdd.selectCompetitions(inscriptions);
		System.out.println("Competition r�cup�r�...");
		bdd.selectAttrEquipe(inscriptions);
		return inscriptions;
	}

	/**
	 * Retourne un object inscriptions vide. Ne modifie pas les compétitions
	 * et candidats déjà existants.
	 */
	
	public Inscriptions reinitialiser()
	{
		inscriptions = new Inscriptions();
		return getInscriptions();
	}

	/**
	 * Efface toutes les modifications sur Inscriptions depuis la dernière sauvegarde.
	 * Ne modifie pas les compétitions et candidats déjà existants.
	 */
	
	public Inscriptions recharger()
	{
		inscriptions = null;
		return getInscriptions();
	}
	
	private static Inscriptions readObject()
	{
		ObjectInputStream ois = null;
		try
		{
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ois = new ObjectInputStream(fis);
			return (Inscriptions)(ois.readObject());
		}
		catch (IOException | ClassNotFoundException e)
		{
			return null;
		}
		finally
		{
				try
				{
					if (ois != null)
						ois.close();
				} 
				catch (IOException e){}
		}	
	}
	
	/**
	 * Sauvegarde le gestionnaire pour qu'il soit ouvert automatiquement 
	 * lors d'une exécution ultérieure du programme.
	 * @throws IOException 
	 */
	
	public void sauvegarder() throws IOException
	{
		ObjectOutputStream oos = null;
		try
		{
			FileOutputStream fis = new FileOutputStream(FILE_NAME);
			oos = new ObjectOutputStream(fis);
			oos.writeObject(this);
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			try
			{
				if (oos != null)
					oos.close();
			} 
			catch (IOException e){}
		}
	}
	
	@Override
	public String toString()
	{
		return "Candidats : " + getCandidats().toString()
			+ "\nCompetitions : " + getCompetitions().toString();
	}
	
	public static void main(String[] args)
	{
		
//        MenuPrincipal menu = new MenuPrincipal();
//        menu.start();
        Fenetre fenetre = new Fenetre();
        fenetre.setVisible(true);
//		try
//		{
//			inscriptions.sauvegarder();
//		} 
//		catch (IOException e)
//		{
//			System.out.println("Sauvegarde impossible." + e);
//		}
	}
}
