package br.com.sge.dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import br.com.sge.factory.ConnectionFactory;

public abstract class DataAcessObject {

	private Connection connection;
	private PreparedStatement statement;

	protected Connection openConnection() {

		if (connection == null) {
			connection = ConnectionFactory.getConnectionProviderByDriverManager();
		}
		return connection;
	}

	protected PreparedStatement openStatementCreator(String queryString) throws SQLException {
		
		return this.statement = this.openConnection().prepareStatement(queryString);
	}

	protected void disposeOpenConnectionAndStatement() {
		try {
			if (connection != null) {
				this.connection.close();
				this.connection = null;
			}
			if (statement != null) {
				this.statement.close();
				this.statement = null;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}