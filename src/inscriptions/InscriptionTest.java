package inscriptions;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.SortedSet;

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
		if(getEquipe2.contains(equipeTest))
		{
			trouver2=true;
		}
		assertEquals("remove",false,trouver2);
		
		
	}
	
	@Test
	public void testCompetition()
	{
		competitionTest = inscriptionTest.createCompetition("Mondial de basket", LocalDate.now(), true); //Création d'une compétition
		competitionTest1 = inscriptionTest.createCompetition("Mondial de PingPong", LocalDate.now(), false);
		competitionTest2 = inscriptionTest.createCompetition("Mondial de Tennis", LocalDate.now(), false);
		
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
	// test compare 
	public void testCompareTo() {

		// On créé une nouvelle compétition

		Competition competitionQuis = inscriptionTest.createCompetition("Visionnage de Cassettes", null, true);

		//Et on compare a la compétition déjà créée plus haut

		assertTrue(competitionTest.compareTo(competitionQuis) == 0);

	}
	public void testDelete() {// on  teste la supression d'une competition

		SortedSet<Competition> competitionDel = inscriptionTest.getCompetitions();

		competitionTest.delete();
		assertTrue(!competitionDel.contains(competitionTest));

	}
		public void testRemove() {// on verifie la supression d'un candidat

			Set<Candidat> candidats = competitionTest.getCandidats();

			try {
				competitionTest.add(personneTest);
			} catch (DateInvalide e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			competitionTest.remove(personneTest);

			assertTrue(!candidats.contains(personneTest));

		}
		public void testAddEquipe() { // verification d'ajout d' une equipe dans une competition

			Set<Candidat> candidats = competitionTest.getCandidats();

			Equipe fly = inscriptionTest.createEquipe("Fly - Forces libérées yaourt");

			try {
				competitionTest.add(fly);
			} catch (DateInvalide e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			assertTrue(candidats.contains(fly));

		}
		public void testAddPersonne() { // on verifie ici l'ajout d'un candidat dans une competition

			Set<Candidat> candidats = competitionTest.getCandidats();

			try {
				competitionTest.add(personneTest);
			} catch (DateInvalide e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			assertTrue(candidats.contains(personneTest));

		}
		public class testPersonne { // inscription d'une personne

			Inscriptions inscriptions = Inscriptions.getInscriptions();

			Personne personne = inscriptions.createPersonne("Jannot", "Marcel", "test@foxtrop.com");
		}

			public void testGetPrenom() {

				assertEquals("Marcel", personneTest.getPrenom());

			}
			public void testSetPrenom() {

				personneTest.setPrenom("LaPoulette"); // On ajoute une personne appellée "LaPoulette"

				assertEquals("LaPoulette", personneTest.getPrenom()); // On vérifie

			}
			public void testDelete1() { // on supprime une personne 

				Set<Equipe> equipes = personneTest.getEquipes();

				personneTest.delete();

				assertTrue(equipes.isEmpty());

			}
			public class testEquipe {

				Inscriptions inscriptions = Inscriptions.getInscriptions();

				Equipe plop = inscriptions.createEquipe("Plop blob");

				//Ajout des  personnnesd dans des equipes

				Personne personnes = inscriptions.createPersonne("Patrick", "Manchot", "test@test.com");

				Personne people = inscriptions.createPersonne("Marin", "Dodouce", "testdouce@test.com");

				Personne treople = inscriptions.createPersonne("Popol", "Quidur", "testquidur@test.com");

				Personne qreople = inscriptions.createPersonne("DaBro", "Breaf", "testbreaf@test.com");

			
			public void testGetMembres() {//Test GetMembres de l'equipe

				SortedSet<Personne> lesMembres = plop.getMembres();

				plop.add(personnes);

				plop.add(people);

				plop.add(treople);

				plop.add(qreople);

				assertEquals(lesMembres, plop.getMembres());

			}
			public void testAddpersonne() {// verification  des membres

				SortedSet<Personne> lesMembres = plop.getMembres();

				plop.add(personnes);

				plop.add(people);

				plop.add(treople);

				plop.add(qreople);

				assertTrue(lesMembres.contains(personnes) && lesMembres.contains(people) && lesMembres.contains(treople) && lesMembres.contains(qreople));

			}
			public void testRemovePersonnesonne() {//Test RemovePersonne

				SortedSet<Personne> membres = plop.getMembres();

				plop.add(personnes);

				plop.add(people);

				plop.add(treople);

				plop.add(qreople);

				plop.remove(personnes);

				plop.remove(people);

				plop.remove(treople);


				// Si l'Affirmation est fausse, le résultat est correct

				assertTrue(!membres.contains(personnes));

			}
		}
			public class testCandidat {

				Inscriptions inscriptions = Inscriptions.getInscriptions();

				Personne personne = inscriptions.createPersonne("Jacqueline", "Ferdinand", "test@test.com");

				Personne people = inscriptions.createPersonne("Jacqueline", "Ferdinand", "test@test.com");
				//test GetNom

				@Test

				public void testGetNom() {

					assertEquals("Jacqueline", personne.getNom());

				}
				public void testSetNom() {

					personne.setNom("John");

					assertEquals("John", personne.getNom());

				}
				public void testAdd() {// ajout d'un candiat dans competition

					Set<Competition> Competitions = personne.getCompetitions();

					Competition testCompetition = inscriptions.createCompetition("Concours de saut en travers", null, false);

					try {
						testCompetition.add(personne);
					} catch (DateInvalide e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					assertTrue(Competitions.contains(testCompetition));

				}
				public void testRemove() {//TestRemove, comme pour Add mais on retire la donnée ajoutée

					Set<Competition> Competitions = personne.getCompetitions();

					Competition testCompetition = inscriptions.createCompetition("Concours de saut en travers", null, false);

					try {
						testCompetition.add(personne);
					} catch (DateInvalide e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					testCompetition.remove(personne);

					assertFalse(Competitions.contains(testCompetition));

				}
		}	
}
