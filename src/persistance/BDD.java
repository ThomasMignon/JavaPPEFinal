package persistance;
import inscriptions.*;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDD implements Serializable
{
	String url = "jdbc:mysql://localhost/ppejava";
	String login = "root";
	String password = "";
//	Connection cn = null;
//	Statement st = null;
	private static final long serialVersionUID = -60L;
	
	public void selectPersonne(Inscriptions inscription)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection(url, login,password);
			Statement st = cn.createStatement();	
			String requete ="Select * From personnes p,candidats c WHERE p.id_personne = c.id_candidat";
			ResultSet result;
			result = st.executeQuery(requete);
			while ( result.next() ) {
			    inscription.createPersonne(result.getString( "nom" ),result.getString( "prenom" ), result.getString( "mail" ));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void save(Personne personne) 
	{	
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection cn = DriverManager.getConnection(url, login,password);
				Statement st = cn.createStatement();	
				String requete ="Insert into personnes(prenom,mail) values ('"+personne.getPrenom()+"','"+personne.getMail()+"')";
				st.executeUpdate(requete);	
				String requete2 ="Select id_personne From personnes Where prenom ='" + personne.getPrenom() + "' And mail = '" + personne.getMail() + "' And deleted_at IS NULL";
				ResultSet result = st.executeQuery(requete2);
				int idUser2 = 0;
				while (result.next()) {
				    idUser2 = result.getInt( "id_personne" );
				}
				personne.setId(idUser2);
				String requete3 ="Insert into candidats(id_personne,nom) values ('"+idUser2+"','"+personne.getNom()+"')";
				st.executeUpdate(requete3);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
	}
	public void save(Equipe equipe) 
	{	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection(url, login,password);
			Statement st = cn.createStatement();	
			String requete ="Insert into equipes(id_equipe) values (NULL)";
			st.executeUpdate(requete);	
			String requete2 ="Select id_equipe From equipes";
			ResultSet result = st.executeQuery(requete2);
			int idequipe = 0;
			while (result.next()) {
			    idequipe = result.getInt( "id_equipe" );
			}
			String requete3 ="Insert into candidats(id_equipe,nom) values ('"+idequipe+"','"+equipe.getNom()+"')";
			st.executeUpdate(requete3);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}
	public void save(Competition competition) 
	{	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection(url, login,password);
			Statement st = cn.createStatement();	
			
			int equipe = competition.estEnEquipe() ? 1 : 0;;
			
			String requete ="Insert into competitions(date,nom,enequipe) values ('"+competition.getDateCloture()+"','"+competition.getNom()+"','"+equipe+"')";
			st.executeUpdate(requete);		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}
	public void save(Personne personne,Equipe equipe) 
	{	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection(url, login,password);
			Statement st = cn.createStatement();		
			String requete ="Insert into attrequipe(id_personne,id_equipe) values ('"+personne.getId()+"','"+equipe.getId()+"')";
			st.executeUpdate(requete);		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}
	public void save(Candidat candidat,Competition competition) 
	{	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection(url, login,password);
			Statement st = cn.createStatement();		
			String requete ="Insert into attrcompetition(id_candidat,id_competition) values ('"+candidat.getId_candidat()+"','"+competition.getId_competition()+"')";
			st.executeUpdate(requete);		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}
	
	
	public void delete(Personne personne)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection(url, login,password);
			Statement st = cn.createStatement();	
			String requete ="UPDATE personnes SET deleted_at = NOW() WHERE id_candidat = "+personne.getId();
			st.executeUpdate(requete);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void delete(Equipe equipe)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection(url, login,password);
			Statement st = cn.createStatement();	
			String requete ="UPDATE equipes SET deleted_at = NOW() WHERE id_candidat = "+equipe.getId();
			st.executeUpdate(requete);	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void delete(Candidat candidat)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection(url, login,password);
			Statement st = cn.createStatement();	
			String requete ="UPDATE candidats SET deleted_at = NOW() WHERE id_candidat = "+candidat.getId_candidat();
			st.executeUpdate(requete);	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void delete(Competition competition)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection(url, login,password);
			Statement st = cn.createStatement();	
			String requete ="UPDATE competitions SET deleted_at = NOW() WHERE id_candidat = "+competition.getId_competition();
			st.executeUpdate(requete);	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
