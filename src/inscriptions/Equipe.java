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
	private int id;

	BDD bdd = new BDD();
	boolean isDelete;
	
	public Equipe(Inscriptions inscriptions, String nom)
	{
		super(inscriptions, nom);
		bdd.save(this);
	}
	
	/**
	 * Retourne la valeur de suppression de Equipe.
	 */
	
	public boolean getIsDelete() {
		return isDelete;
	}

	/**
	 * Modifie la valeur de suppression de Equipe.
	 */
	
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * Retourne l'id de l'équipe.
	 */
	
	public int getId() {
		return id;
	}
	
	/**
	 * Modifie l'id de l'équipe.
	 */

	public void setId(int id_equipe) {
		this.id = id_equipe;
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
