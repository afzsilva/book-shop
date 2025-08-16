package booksStandalone.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/booksdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Sao_Paulo&createDatabaseIfNotExist=true";
	private static final String USER = "root";
	private static final String PASS = "root";
	//private static final String CLASS_FOR_NAME = "com.mysql.cj.jdbc.Driver";
	private static Connection conect = null;

	public static Connection getConection() {

		try {
			//Class.forName(CLASS_FOR_NAME);
			conect = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conectado >>> " + URL);
		} catch (Exception e) {
			System.out.println("Erro Conexao " + e);
		}

		return conect;

	}

	public static void setEndConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("Conexao foi fechada");
			} catch (SQLException e) {
				System.out.println("Erro ao tentar fechar conexao");
				e.printStackTrace();
			}
		}

	}

}