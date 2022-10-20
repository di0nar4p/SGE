package br.com.sge.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory{
	/*
	private final static String URL = "jdbc:mysql://db4free.net:3306/id53_sge?useSSL=false&useTimezone=true&serverTimezone=UTC";
	private final static String USERNAME = "sgedatabaseuser";
	private final static String PASSWORD = "123456@P";
	*/
	
	//Configuração utilizada para o banco local
	private final static String URL = "jdbc:mysql://localhost:3306/id53_sge?useSSL=false&useTimezone=true&serverTimezone=UTC";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "123456";
	//private final static String PASSWORD = "M4r1nh018@";

	/*
	*	@desc: prover uma conexão com o banco de dados
	*	@param:
	*	@return: an connection
	*/
	public static Connection getConnectionProviderByDriverManager(){
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
}