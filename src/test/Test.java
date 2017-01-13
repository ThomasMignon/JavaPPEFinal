package test;

import inscriptions.*;


public class Test 
{
	public boolean testPersonne(Inscriptions inscription, String prenom, String nom, String mail)
	{
		boolean error = false;
		Personne personneTest = new Personne(inscription,nom,prenom,mail);
		if(personneTest.getPrenom()==prenom)
		{
			return true;
		}
		else
		{
			System.out.println("Erreur sur le prénom");
			System.out.println(prenom);
			System.out.println("!="+personneTest.getPrenom());
			error=false;
		}
		
		if(personneTest.getNom()==nom)
		{
			return true;
		}
		else
		{
			System.out.println("Erreur sur le nom");
			System.out.println(nom);
			System.out.println("!="+personneTest.getNom());
			error=false;
		}
		
		if(!error)
		{
			return false;
		}
		else
		{
			return true;
		}
		
	}
}
