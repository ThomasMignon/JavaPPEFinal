package inscriptions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDD{
	String url = "jdbc:mysql://localhost/ppejava";
	String login = "root";
	String password = "";
	Connection cn = null;
	Statement st = null;
	ResultSet rs = null;

	public String selectPersonne(int id)
	{
		//TODO Renvoyer un array avec les donnees.
		String personne = "Cette personne n'existe pas";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, login,password);
			st = cn.createStatement();	
			String requete ="Select * From personnes WHERE id_candidat = "+id;
			ResultSet result = st.executeQuery(requete);
			while ( result.next() ) {
			    int idUser = result.getInt( "id_candidat" );
			    String prenom = result.getString( "prenom" );
			    String mail = result.getString( "mail" );
			    personne = "Id user = " + idUser + " Prenom = " + prenom +" Mail = " +mail;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personne;
		
	}
	
	public void save(Personne personne) 
	{	
			try {
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection(url, login,password);
				st = cn.createStatement();	
				String requete ="Insert into personnes(prenom,mail) values ('"+prenom+"','"+mail+"')";
				st.executeUpdate(requete);	
				String requete2 ="Select id_candidat From personnes Where prenom ='" + prenom + "' And mail = '" + mail + "' And deleted_at IS NULL";
				ResultSet result = st.executeQuery(requete2);
				int idUser2 = 0;
				while (result.next()) {
				    idUser2 = result.getInt( "id_candidat" );
				}
				String requete3 ="Insert into candidat(id_personne,nom) values ('"+idUser2+"','"+nom+"')";
				st.executeUpdate(requete3);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
	}
	
	public void deletePersonne(int id)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, login,password);
			st = cn.createStatement();	
			String requete ="UPDATE personnes SET deleted_at = NOW() WHERE id_candidat = "+id;
			st.executeUpdate(requete);	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
