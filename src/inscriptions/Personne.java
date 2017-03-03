package inscriptions;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import persistance.*;

/**
 * Représente une personne physique pouvant s'inscrire à une compétition.
 */

public class Personne extends Candidat
{
	private static final long serialVersionUID = 4434646724271327254L;
	private String prenom, mail;
	private Set<Equipe> equipes;
	boolean isDelete;

	private BDD bdd = new BDD();
	
	public Personne(Inscriptions inscriptions, String nom, String prenom, String mail,boolean save)
	{
		super(inscriptions, nom);
		this.prenom = prenom;
		this.mail = mail;
		equipes = new TreeSet<>();
		if(save)
		{
			bdd.save(this);
		}
	}

	/**
	 * Retourne la valeur de suppression de Personne.
	 * @return
	 */
	
	public boolean getIsDelete() {
		return isDelete;
	}

	/**
	 * Modifie la valeur de suppression de Personne.
	 */
	
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	/**
	 * Retourne le prénom de la personne.
	 * @return
	 */
	
	public String getPrenom()
	{
		return this.prenom;
	}

	/**
	 * Modifie le prénom de la personne.
	 * @param prenom
	 */
	
	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
		//bdd.save(this);
	}

	/**
	 * Retourne l'adresse électronique de la personne.
	 * @return
	 */
	
	public String getMail()
	{
		return mail;
	}

	/**
	 * Modifie l'adresse électronique de la personne.
	 * @param mail
	 */
	
	public void setMail(String mail)
	{
		this.mail = mail;
	}
	
	/**
	 * Retourne l'id de la personne.
	 * @param mail
	 */
	
	/**
	 * Modifie l'id de la personne.
	 * @param mail
	 */

	/**
	 * Retoure les équipes dont cette personne fait partie.
	 * @return
	 */
	
	public Set<Equipe> getEquipes()
	{
		return Collections.unmodifiableSet(equipes);
	}
	
	public boolean add(Equipe equipe)
	{
		return equipes.add(equipe);
	}

	public boolean remove(Equipe equipe)
	{
		return equipes.remove(equipe);
	}
	
	@Override
	public void delete()
	{
		super.delete();
		for (Equipe e : equipes)
			e.remove(this,true);
	}
	
	@Override
	public String toString()
	{
		return super.toString() + " membre de " + equipes.toString();
	}
}
