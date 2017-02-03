package inscriptions;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import persistance.BDD;

/**
 * Représente une compétition, c'est-à-dire un ensemble de candidats 
 * inscrits à un événement, les inscriptions sont closes à la date dateCloture.
 *
 */

public class Competition implements Comparable<Competition>, Serializable
{
	private static final long serialVersionUID = -2882150118573759729L;
	private Inscriptions inscriptions;
	private String nom;
	private int id_competition;
	private Set<Candidat> candidats;
	private LocalDate dateCloture;
	private boolean enEquipe = false;
	BDD bdd = new BDD();
	boolean isDelete;
	
	Competition(Inscriptions inscriptions, String nom, LocalDate dateCloture, boolean enEquipe, boolean save)
	{
		this.enEquipe = enEquipe;
		this.inscriptions = inscriptions;
		this.nom = nom;
		this.dateCloture = dateCloture;
		candidats = new TreeSet<>();
		if(save)
		{
			bdd.save(this);
		}
	}
	
	/**
	 * Retourne si Competition est Supprimer.
	 * @return
	 */
	
	public boolean getIsDelete() {
		return isDelete;
	}

	/**
	 * Modifie la suppression de la competition.
	 * @return
	 */
	
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * Retourne le nom de la compétition.
	 * @return
	 */
	
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * Modifie le nom de la compétition.
	 */
	
	public void setNom(String nom)
	{
		this.nom = nom ;
	}
	
	/**
	 * Retourne vrai si les inscriptions sont encore ouvertes, 
	 * faux si les inscriptions sont closes.
	 * @return
	 */
	
	public boolean inscriptionsOuvertes()
	{
		return this.getDateCloture().isBefore(LocalDate.now());
	}
	
	/**
	 * Retourne la date de cloture des inscriptions.
	 * @return
	 */
	
	public LocalDate getDateCloture()
	{
		return dateCloture;
	}
	
	/**
	 * Est vrai si et seulement si les inscriptions sont réservées aux équipes.
	 * @return
	 */
	
	public boolean estEnEquipe()
	{
		return enEquipe;
	}
	
	/**
	 * Modifie la date de cloture des inscriptions. Il est possible de la reculer 
	 * mais pas de l'avancer.
	 * @param dateCloture
	 * @throws DateInvalide 
	 */
	
	public void setDateCloture(LocalDate dateCloture) throws DateInvalide
	{
		if(this.getDateCloture().isBefore(dateCloture))
		{
			this.dateCloture = dateCloture;
		}
		else
		{
			throw new DateInvalide();
		}
	}
	
	/**
	 * Retourne l'ensemble des candidats inscrits.
	 * @return
	 */
	
	public Set<Candidat> getCandidats()
	{
		return Collections.unmodifiableSet(candidats);
	}
	
	/**
	 * Inscrit un candidat de type Personne à la compétition. Provoque une
	 * exception si la compétition est réservée aux équipes ou que les 
	 * inscriptions sont closes.
	 * @param personne
	 * @return
	 * @throws DateInvalide 
	 */
	
	public boolean add(Personne personne) throws DateInvalide
	{
		if(this.getDateCloture().isAfter(LocalDate.now()))
		{
			if (enEquipe)
				throw new RuntimeException();
			personne.add(this,true);
			bdd.save(personne,this);
			return candidats.add(personne);
		}
		else
		{
			throw new DateInvalide();
		}
		
	}

	/**
	 * Inscrit un candidat de type Equipe à la compétition. Provoque une
	 * exception si la compétition est réservée aux personnes ou que 
	 * les inscriptions sont closes.
	 * @param personne
	 * @return
	 * @throws DateInvalide 
	 */

	public boolean add(Equipe equipe) throws DateInvalide
	{
		if(this.getDateCloture().isAfter(LocalDate.now()))
		{
			if (!enEquipe)
				throw new RuntimeException();
			equipe.add(this,true);
			bdd.save(equipe,this);
			return candidats.add(equipe);
		}
		else
		{
			throw new DateInvalide();
		}
	}

	/**
	 * Désinscrit un candidat.
	 * @param candidat
	 * @return
	 */
	
	public boolean remove(Candidat candidat)
	{
		candidat.remove(this);
		return candidats.remove(candidat);
	}
	
	/**
	 * Supprime la compétition de l'application.
	 */
	
	public void delete()
	{
		for (Candidat candidat : candidats)
			remove(candidat);
		inscriptions.remove(this);
	}
	
	@Override
	public int compareTo(Competition o)
	{
		return getNom().compareTo(o.getNom());
	}
	
	@Override
	public String toString()
	{
		return getNom();
	}

	public int getId() {
		return this.id_competition;
	}

	public void setId(int id_competition) {
		this.id_competition = id_competition;
	}
}
