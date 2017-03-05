package inscriptions;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import persistance.BDD;

/**
 * Candidat à un événement sportif, soit une personne physique, soit une équipe.
 *
 */

public abstract class Candidat implements Comparable<Candidat>, Serializable
{
	private static final long serialVersionUID = -6035399822298694746L;
	private Inscriptions inscriptions;
	private String nom;
	private int id;
	boolean isDelete;
	private BDD bdd = new BDD();

	private Set<Competition> competitions;
	
	public Candidat(Inscriptions inscriptions, String nom)
	{
		this.inscriptions = inscriptions;	
		this.nom = nom;
		competitions = new TreeSet<>();

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
	 * Retourne l'id du candidat.
	 * @return
	 */
	
	public int getId() {
		return this.id;
	}
	
	/**
	 * Modifie l'id du candidat.
	 * @param id_candidat
	 */

	public void setId(int id_candidat) {
		this.id = id_candidat;
	}

	/**
	 * Retourne le nom du candidat.
	 * @return
	 */
	
	public String getNom()
	{
		return nom;
	}

	/**
	 * Modifie le nom du candidat.
	 * @param nom
	 */
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}

	/**
	 * Retourne toutes les compétitions auxquelles ce candidat est inscrit.s
	 * @return
	 */

	public Set<Competition> getCompetitions()
	{
		return Collections.unmodifiableSet(competitions);
	}
	
	public boolean add(Competition competition,boolean save)
	{
		if(save)
		{
			bdd.save(competition);
		}
		return competitions.add(competition);
	}

	boolean remove(Competition competition)
	{
		return competitions.remove(competition);
	}

	/**
	 * Supprime un candidat de l'application.
	 */
	
	public void delete()
	{
		for (Competition c : competitions)
			c.remove(this,true);
		inscriptions.remove(this);
	}
	
	@Override
	public int compareTo(Candidat o)
	{
		return getNom().compareTo(o.getNom());
	}
	
	@Override
	public String toString()
	{
		return "\n" + getNom() + " -> inscrit à " + getCompetitions();
	}
	
}
