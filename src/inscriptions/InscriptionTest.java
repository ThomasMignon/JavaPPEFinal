package inscriptions;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class InscriptionTest 
{
	Inscriptions inscriptionTest = new Inscriptions();
	
	Personne personneTest;
	Personne personneTest1;
	Personne personneTest2;

	Equipe equipeTest;
	Equipe equipeTest1;
	Equipe equipeTest2;
	
	Competition competitionTest;
	Competition competitionTest1;
	Competition competitionTest2;

	@Test
	public void testPersonne() 
	{
		personneTest = new Personne(inscriptionTest, "Limentour", "Gaetan", "gaetalim@gmail.com"); 
		personneTest1 = new Personne(inscriptionTest, "Blabla", "Moi", "moi@gmail.com");
		personneTest2 = new Personne(inscriptionTest, "Bloblo", "Toi", "toi@gmail.com");
		
		equipeTest = new Equipe(inscriptionTest,"blabla");
		
		assertEquals("getPrenom", "Gaetan", personneTest.getPrenom());//Vérification du prénom
		assertEquals("getPrenom", "Moi", personneTest1.getPrenom());
		assertEquals("getPrenom", "Toi", personneTest2.getPrenom());

		assertEquals("getNom", "Limentour", personneTest.getNom());//Vérification du nom
		assertEquals("getNom", "Blabla", personneTest1.getNom());
		assertEquals("getNom", "Bloblo", personneTest2.getNom());

		assertEquals("getMail", "gaetalim@gmail.com", personneTest.getMail());//Vérification du mail
		assertEquals("getMail", "moi@gmail.com", personneTest1.getMail());
		assertEquals("getMail", "toi@gmail.com", personneTest2.getMail());
		
		personneTest.setMail("yoyo@gmail.com");//Vérification du setMail
		personneTest1.setMail("lala@gmail.com");
		personneTest2.setMail("lolo@gmail.com");
		assertEquals("setMail", "yoyo@gmail.com",personneTest.getMail());
		assertEquals("setMail", "lala@gmail.com",personneTest1.getMail());
		assertEquals("setMail", "lolo@gmail.com",personneTest2.getMail());
		
		personneTest.setNom("papa");
		personneTest1.setNom("papa");
		personneTest2.setNom("pupu");
		
		assertEquals("compareTo",0,personneTest.compareTo(personneTest1));//Vérification de la fonction CompareTo
		assertEquals("compareTo",-20,personneTest1.compareTo(personneTest2));
		assertEquals("compareTo",-20,personneTest.compareTo(personneTest2));
		
		personneTest.add(equipeTest); // Vérification de l'ajout d'une personne dans une équipe
		
		boolean trouver = false;
		Set<Equipe> getEquipe = personneTest.getEquipes(); // Test du getEquipe
		if(getEquipe.contains(equipeTest))
		{
			trouver=true;
		}
		assertEquals("getEquipes",true,trouver);
		
		
		personneTest.remove(equipeTest);
		boolean trouver2 = false;
		Set<Equipe> getEquipe2 = personneTest.getEquipes(); // Test du getEquipe
		if(getEquipe.contains(equipeTest))
		{
			trouver2=true;
		}
		assertEquals("remove",false,trouver2);
		
		
	}
	
	@Test
	public void testCompetition()
	{
		competitionTest = inscriptionTest.createCompetition("Mondial de basket", null, true); //Création d'une compétition
		competitionTest1 = inscriptionTest.createCompetition("Mondial de PingPong", null, false);
		competitionTest2 = inscriptionTest.createCompetition("Mondial de Tennis", null, false);
		
		assertEquals("estEnEquipe",true,competitionTest.estEnEquipe());//Vérification de si la compépition se déroule en équipe ou non
		assertEquals("estEnEquipe",false,competitionTest1.estEnEquipe());
		assertEquals("estEnEquipe",false,competitionTest2.estEnEquipe());
	}
	
	@Test
	public void testEquipe()
	{
		equipeTest = inscriptionTest.createEquipe("YoyoTeam");
		equipeTest1 = inscriptionTest.createEquipe("LalaTeam");
		equipeTest2 = inscriptionTest.createEquipe("LoloTeam");
		
		
		
		//TODO : GetMembre
	}

}
