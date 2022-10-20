package br.com.sge.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.sge.IF.DAO;
import br.com.sge.modelo.Turma;

public class TurmaDAO extends DataAcessObject implements DAO<Turma>{

	@Override
	public void cadastrar(Turma t) {
		// TODO Auto-generated method stub
	}

	@Override
	public void excluir(Turma t) {
		// TODO Auto-generated method stub
	}

	@Override
	public Turma buscar(Turma t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Turma> listar() {
		
		ArrayList<Turma> lista = new ArrayList<>();
		String select = "SELECT		t.codigo, t.cod_curso, t.ano_letivo, t.nome, t.label, DATE_FORMAT(t.data_inclusao, '%M de %y') AS data, " +
						"			c.nome AS curso " +
						"FROM 		id53_sge.turma t " +
						"JOIN		id53_sge.curso c ON c.codigo = t.cod_curso AND c.data_exclusao IS NULL " +
						"WHERE  	YEAR(t.data_inclusao) = DATE_FORMAT(CURRENT_DATE(), '%Y') " + 
						"AND		t.data_exclusao IS NULL " +
						"ORDER BY 	t.nome;";
		try {
			
			PreparedStatement st = super.openStatementCreator(select);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				
				Turma turma = new Turma();
				turma.setCodigo(rs.getInt("codigo"));
				turma.setAnoLetivo(rs.getString("ano_letivo"));
				turma.setNome(rs.getString("nome"));
				turma.setLabel(rs.getString("label"));
				turma.setData(rs.getString("data"));
				turma.setMisc(rs.getString("curso"));
				lista.add(turma);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}finally {
			super.disposeOpenConnectionAndStatement();
		}
		System.out.println(lista);
		return lista;
	}

	@Override
	public void atualizar(Turma t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Turma> pesquisar(Turma turma) {
		
		String select = "SELECT	t.codigo, t.cod_curso, t.label, DATE_FORMAT(t.data_inclusao, '%M %y') AS data, t.ano_letivo, t.nome, "
					  + " 		c.nome AS curso "
					  + "FROM	turma t "
					  + "JOIN	curso c ON c.codigo = t.cod_curso AND c.data_exclusao IS NULL "
					  + "WHERE	( LOWER(t.label) LIKE( LOWER(?)) OR LOWER(t.nome) LIKE( LOWER(?)) OR LOWER(t.nome) LIKE( LOWER(?) OR LOWER(c.nome) LIKE( LOWER(?)))) "
					  + "AND	t.data_exclusao IS NULL "
					  + "ORDER BY t.nome; ";
		ArrayList<Turma> lista = new ArrayList<>();
		try {
			
			PreparedStatement st = super.openStatementCreator(select);
			st.setString(1, "%" + turma.getMisc() + "%");
			st.setString(2, "%" + turma.getMisc() + "%");
			st.setString(3, "%" + turma.getMisc() + "%");
			st.setString(4, "%" + turma.getMisc() + "%");
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				
				Turma prototype = new Turma();
				prototype.setCodigo(rs.getInt("codigo"));
				prototype.setAnoLetivo(rs.getString("ano_letivo"));
				prototype.setNome(rs.getString("nome"));
				prototype.setLabel(rs.getString("label"));
				prototype.setData(rs.getString("data"));
				prototype.setMisc(rs.getString("curso"));
				lista.add(prototype);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}finally {
			super.disposeOpenConnectionAndStatement();
		}
		return lista;
	}
}