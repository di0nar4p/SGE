package br.com.sge.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.sge.modelo.Instituicao;

public class InstituicaoDAO extends DataAcessObject{

	public Instituicao buscar() {
		String select = "SELECT codigo, cnpj, razao_social, nome FROM instituicao";
		Instituicao instituicao = null;
		try {
			PreparedStatement statement = super.openStatementCreator(select);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				instituicao = new Instituicao();
				instituicao.setCodigo(result.getInt("codigo"));
				instituicao.setCnpj(result.getString("cnpj"));
				instituicao.setRazaoSocial(result.getString("razao_social"));
				instituicao.setNome(result.getString("nome"));
			}
		} catch (SQLException error) {
			System.out.println(error.getMessage());
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
		return instituicao;
	}

	public void atualizar(Instituicao object){
		
		String insert = "UPDATE instituicao SET cnpj = ?, nome = ?, razao_social = ? " + "WHERE	codigo = ?;";
		try {
			PreparedStatement statment = super.openStatementCreator(insert);
			statment.setString(1, object.getCnpj());
			statment.setString(2, object.getNome());
			statment.setString(3, object.getRazaoSocial());
			statment.setInt(4, object.getCodigo());
			statment.execute();
		}catch (SQLException error) {
			System.err.println(error.getMessage());
		}finally{
			super.disposeOpenConnectionAndStatement();
		}
	}
}