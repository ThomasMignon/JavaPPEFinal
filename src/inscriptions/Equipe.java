package inscriptions;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import persistance.BDD;

/**
 * Représente une Equipe. C'est-à-dire un ensemble de personnes pouvant 
 * s'inscrire à une compétition.
 * 
 */

public class Equipe extends Candidat
{
	private static final long serialVersionUID = 4147819927233466035L;
	private SortedSet<Personne> membres = new TreeSet<>();
	private int id_equipe;

	BDD bdd = new BDD();
	
	public Equipe(Inscriptions inscriptions, String nom)
	{
		super(inscriptions, nom);
		bdd.save(this);
	}

	/**
	 * Retourne l'id de l'équipe.
	 */
	
	public int getId_equipe() {
		return id_equipe;
	}
	
	/**
	 * Modifie l'id de l'équipe.
	 */

	public void setId_equipe(int id_equipe) {
		this.id_equipe = id_equipe;
	}
	
	/**
	 * Retourne l'ensemble des personnes formant l'équipe.
	 */
	
	public SortedSet<Personne> getMembres()
	{
		return Collections.unmodifiableSortedSet(membres);
	}
	
	/**
	 * Ajoute une personne dans l'équipe.
	 * @param membre
	 * @return
	 */

	public boolean add(Personne membre)
	{
		membre.add(this);
		bdd.save(membre,this);
		return membres.add(membre);
	}

	/**
	 * Supprime une personne de l'équipe. 
	 * @param membre
	 * @return
	 */
	
	public boolean remove(Personne membre)
	{
		membre.remove(this);
		return membres.remove(membre);
	}

	@Override
	public void delete()
	{
		super.delete();
		for(Personne m : membres)
		{
			m.remove(this);
		}
	}
	
	@Override
	public String toString()
	{
		return "Equipe " + super.toString();
	}
}
