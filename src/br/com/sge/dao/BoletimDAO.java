package br.com.sge.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.sge.IF.DAO;
import br.com.sge.modelo.Boletim;
import br.com.sge.modelo.Usuario;
import br.com.sge.util.Sessao;

public class BoletimDAO extends DataAcessObject implements DAO<Boletim>{

	@Override
	public void cadastrar(Boletim t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Boletim t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boletim buscar(Boletim t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<?> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Boletim> listar(Usuario aluno) {
		 
		String select = "SELECT		b.codigo, b.cod_curso, b.cod_usuario, b.ano, "
					  + "			c.codigo, c.nome AS curso "
					  + "FROM 		id53_sge.boletim b " 
					  + "JOIN		id53_sge.curso c ON c.codigo = b.cod_curso AND c.data_exclusao IS NULL " 
					  + "WHERE		b.cod_usuario = ? "
					  + "AND 		b.data_exclusao IS NULL "
					  + "ORDER BY 	b.ano;";
		ArrayList<Boletim> lista = new ArrayList<>();
		try {

			PreparedStatement st = super.openStatementCreator(select);
			st.setInt(1, aluno.getCodigo());
			ResultSet rs = st.executeQuery();
		
			while(rs.next()) {
				Boletim boletim = new Boletim();
				boletim.setAno(rs.getString("ano"));
				boletim.setCodigo(rs.getInt("codigo"));
				boletim.setCurso(rs.getString("curso"));
				lista.add(boletim);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
		return lista;
	}
	
	@Override
	public void atualizar(Boletim t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Boletim> pesquisar(Boletim boletim) {
		
		String select = "SELECT		b.codigo, b.cod_curso, b.cod_usuario, b.ano, "
				  	  + "			c.codigo, c.nome AS curso "
					  + "FROM 		id53_sge.boletim b " 
					  + "JOIN		id53_sge.curso c ON c.codigo = b.cod_curso AND c.data_exclusao IS NULL " 
					  + "WHERE		b.cod_usuario = ? "
					  + "AND 		b.data_exclusao IS NULL "
					  + "AND		b.ano LIKE(?) OR c.nome LIKE(?) "
					  + "ORDER BY 	b.ano;";
		ArrayList<Boletim> lista = new ArrayList<>();
		try {
			
			System.out.println("Meu codigo = " + Sessao.getInstance().getUser().getCodigo());
			PreparedStatement st = super.openStatementCreator(select);
			st.setInt(1, Sessao.getInstance().getUser().getCodigo());
			st.setString(2, "%" + boletim.getMisc() + "%");
			st.setString(3, "%" + boletim.getMisc() + "%");
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Boletim prototype = new Boletim();
				prototype.setAno(rs.getString("ano"));
				prototype.setCodigo(rs.getInt("codigo"));
				prototype.setCurso(rs.getString("curso"));
				lista.add(prototype);
			}
			return lista;
		} catch (SQLException ex) {
			System.out.println(ex);
		}finally {
			super.disposeOpenConnectionAndStatement();
		}		
		return null;
	}
}