package booksStandalone.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

	private static final String URL = "*****";
	private static final String USER = "*****";
	private static final String PASS = "*****";
	private static final String CLASS_FOR_NAME = "*****";
	private static Connection conect = null;

	public static Connection getConection() {

		try {
			Class.forName(CLASS_FOR_NAME);
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