package br.com.sge.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.sge.modelo.Contato;
import br.com.sge.modelo.Usuario;

public class ContatoDAO extends DataAcessObject {

	public Contato buscar(Usuario usuario) {
		String select = "SELECT * FROM contato WHERE cod_usuario = ?";
		Contato contato = null;
		try {
			PreparedStatement statement = super.openStatementCreator(select);
			statement.setInt(1, usuario.getCodigo());
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				contato = new Contato();
				contato.setCelular(result.getString("celular"));
				contato.setTelefone(result.getString("telefone"));
				contato.setCodigo(result.getInt("codigo"));
				contato.setEmail(result.getString("email"));
				contato.setCodUsuario(result.getInt("cod_usuario"));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
		return contato;
	}

	public void cadastrar(Contato contato, int codUsuario) {
		String insert = "INSERT INTO contato (cod_usuario, email, telefone, celular) VALUES (?, ?, ?, ?);";
		try {
			PreparedStatement statement = super.openStatementCreator(insert);
			statement.setInt(1, codUsuario);
			statement.setString(2, contato.getEmail());
			statement.setString(3, contato.getTelefone());
			statement.setString(4, contato.getCelular());
			statement.execute();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
	}
}
