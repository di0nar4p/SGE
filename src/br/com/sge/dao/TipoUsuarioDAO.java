package br.com.sge.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.sge.IF.DAO;
import br.com.sge.modelo.TipoUsuario;

public class TipoUsuarioDAO extends DataAcessObject implements DAO<TipoUsuario> {

	public ArrayList<TipoUsuario> listar(){
		
		String select = "SELECT codigo, descricao, nome FROM tipo_usuario WHERE data_exclusao IS NULL ORDER BY nome;";		
		try{
			ArrayList<TipoUsuario> lista = new ArrayList<>();
			PreparedStatement openStatementCreator = super.openStatementCreator(select);
			ResultSet result = openStatementCreator.executeQuery();
			while (result.next()){
				TipoUsuario tipoUsuario = new TipoUsuario();
				tipoUsuario.setCodigo(result.getInt("codigo"));
				tipoUsuario.setNome(result.getString("nome"));
				tipoUsuario.setDescricao(result.getString("descricao"));
				lista.add(tipoUsuario);
			}
			return lista;
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			super.disposeOpenConnectionAndStatement();
		}
		return null;
	}

	@Override
	public void atualizar(TipoUsuario tipoUsuario) {
		String update = "UPDATE sge.tipo_usuario SET nome = '?', descricao = '?' " + "WHERE  codigo = '?';";
		try {
			PreparedStatement openStatementCreator = super.openStatementCreator(update);
			openStatementCreator.setString(1, tipoUsuario.getNome());
			openStatementCreator.setString(2, tipoUsuario.getDescricao());
			openStatementCreator.setLong(3, tipoUsuario.getCodigo());
			openStatementCreator.executeQuery();
			openStatementCreator.executeQuery();
			this.disposeOpenConnectionAndStatement();
		} catch (SQLException error) {
			System.err.println(error.getMessage());
		}
	}

	@Override
	public void cadastrar(TipoUsuario object) {
		String insert = "INSERT INTO sge.tipo_usuario (nome, descricao, data_inclusao) "
				+ "VALUES ('?', '?', LOCALTIMESTAMP);";
		try {
			PreparedStatement statement = super.openStatementCreator(insert);
			statement.setString(1, object.getNome());
			statement.setString(2, object.getDescricao());
			statement.executeQuery();
			super.disposeOpenConnectionAndStatement();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void excluir(TipoUsuario object) {
		String exclude = "UPDATE sge.tipo_usuario SET data_exclusao = '?' " + "WHERE codigo = '?';";
		try {
			PreparedStatement statement = super.openStatementCreator(exclude);
			statement.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			statement.setLong(2, object.getCodigo());
			statement.executeQuery();
			super.disposeOpenConnectionAndStatement();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public TipoUsuario buscar(TipoUsuario object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ArrayList<?> pesquisar(TipoUsuario t) {
		// TODO Auto-generated method stub
		return null;
	}
}
