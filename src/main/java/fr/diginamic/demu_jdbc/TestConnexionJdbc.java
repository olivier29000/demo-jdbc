package fr.diginamic.demu_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.slf4j.LoggerFactory;

import Exceptions.TechnicalException;
import fr.diginamic.entité.Article;

public class TestConnexionJdbc {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TestConnexionJdbc.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");

		String driverName = monFichierConf.getString("database.driver");
		String user = monFichierConf.getString("database.user");
		String url = monFichierConf.getString("database.url");
		String password = monFichierConf.getString("database.password");

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			throw new TechnicalException("ça a foiré mec!", e);
		}

		Connection maConnection = null;
		ResultSet moyennePrix = null;
		ResultSet curseur = null;

		try {
			maConnection = DriverManager.getConnection(url, user, password);
			maConnection.setAutoCommit(false);
			System.out.println(maConnection);
			Statement monStatement = maConnection.createStatement();

			int nb1 = monStatement.executeUpdate(
					"INSERT INTO ARTICLE(ID,DESIGNATION,FOURNISSEUR,PRIX) VALUES(5,'raquette','decathlon',10),(6,'tomate','fermier',0.5),(7,'ballon','lesport',25),(8,'chaussures','nike',15)");
			int nb2 = monStatement.executeUpdate("UPDATE ARTICLE SET PRIX=PRIX*1.25 WHERE PRIX>10");

			// System.out.println(nb2);

			// System.out.println(moyennePrix);
			moyennePrix = monStatement.executeQuery("select avg(Prix) as moyenne from article");
			while (moyennePrix.next()) {

				System.out.println("la moyenne des prix des articles est " + moyennePrix.getInt("moyenne"));
			}

			curseur = monStatement.executeQuery("SELECT ID, DESIGNATION, FOURNISSEUR,PRIX FROM ARTICLE");

			ArrayList<Article> articles = new ArrayList<>();

			while (curseur.next()) {

				Integer id = curseur.getInt("ID");

				String designation = curseur.getString("DESIGNATION");

				String fournisseur = curseur.getString("FOURNISSEUR");

				Integer prix = curseur.getInt("PRIX");

				Article articleCourant = new Article(id, designation, fournisseur, prix);

				articles.add(articleCourant);
			}

			for (int i = 0; i < articles.size(); i++) {
				System.out.println(articles.get(i).getId() + " / " + articles.get(i).getDesignation() + " / "
						+ articles.get(i).getFournisseur() + " / " + articles.get(i).getPrix());
			}
			LOGGER.error("ça marche");
			int suppressionDesElements = monStatement.executeUpdate("DELETE FROM ARTICLE");
			System.out.println(suppressionDesElements);

			maConnection.commit();
		} catch (SQLException e) {
			throw new TechnicalException("une exception est apparut", e);
		} finally {
			try {
				maConnection.close();
				moyennePrix.close();
				curseur.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new TechnicalException("boooom", e);
			}
		}

	}

}
