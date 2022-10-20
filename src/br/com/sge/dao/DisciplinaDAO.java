package br.com.sge.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.sge.IF.DAO;
import br.com.sge.modelo.Disciplina;

public class DisciplinaDAO extends DataAcessObject implements DAO<Disciplina> {

	@Override
	public void atualizar(Disciplina object) {
		String update = "UPDATE disciplina SET carga_horaria = ?, descricao = "
				+ "?, label = ?,  nome = ? WHERE  codigo = ?;";
		try {
			PreparedStatement statement = super.openStatementCreator(update);
			statement.setInt(1, object.getCargaHoraria());
			statement.setString(2, object.getDescricao());
			statement.setString(3, object.getLabel());
			statement.setString(4, object.getNome());
			statement.setInt(5, object.getCodigo());
			statement.execute();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
	}

	@Override
	public void cadastrar(Disciplina object){
		
		String insert = "INSERT INTO disciplina (carga_horaria, data_inclusao,  descricao, label, nome) "
					  + "VALUES (?, LOCALTIMESTAMP, ?, ?, ?);";
		try{
			PreparedStatement statement = super.openStatementCreator(insert);
			statement.setInt(1, object.getCargaHoraria());
			statement.setString(2, object.getDescricao());
			statement.setString(3, object.getLabel());
			statement.setString(4, object.getNome());
			statement.execute();
		}catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
	}

	public void excluir(Disciplina object) {
		
		String delete = "UPDATE disciplina SET data_exclusao = LOCALTIMESTAMP WHERE codigo = ? ;";
		try {
			PreparedStatement statement = super.openStatementCreator(delete);
			statement.setInt(1, object.getCodigo());
			statement.execute();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
	}

	@Override
	public ArrayList<Disciplina> listar() {
		
		String select = "SELECT  codigo, carga_horaria, data_inclusao, descricao, label, nome FROM disciplina  WHERE data_exclusao IS NULL ORDER BY nome;";
		ArrayList<Disciplina> lista = new ArrayList<>();
		
		try{
			
			PreparedStatement statement = super.openStatementCreator(select);
			ResultSet result = statement.executeQuery();
			
			while(result.next()){
				Disciplina disciplina = new Disciplina();
				disciplina.setCodigo(result.getInt("codigo"));
				disciplina.setCargaHoraria(result.getInt("carga_horaria"));
				disciplina.setDataInclusao(result.getDate("data_inclusao"));
				disciplina.setDescricao(result.getString("descricao"));
				disciplina.setLabel(result.getString("label"));
				disciplina.setNome(result.getString("nome"));
				lista.add(disciplina);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			super.disposeOpenConnectionAndStatement();
		}
		return lista;
	}

	@Override
	public Disciplina buscar(Disciplina t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Disciplina> pesquisar(Disciplina disciplina) {
		
		String select = "SELECT  codigo, carga_horaria, nome, label " 
					  + "FROM 	id53_sge.disciplina " 
					  +	"WHERE 	(LOWER(nome) LIKE( LOWER (?)) OR LOWER(label) LIKE( LOWER(?))) " 
					  +	"AND 	data_exclusao IS NULL ORDER BY nome;";
		ArrayList<Disciplina> lista = new ArrayList<>();
		
		try {
			
			PreparedStatement st = super.openStatementCreator(select);
			st.setString(1, "%" + disciplina.getMisc() + "%");
			st.setString(2, "%" + disciplina.getMisc() + "%");
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				
				Disciplina prototype = new Disciplina();
				prototype.setCodigo(rs.getInt("codigo"));
				prototype.setNome(rs.getString("nome"));
				prototype.setLabel(rs.getString("label"));
				prototype.setCargaHoraria(rs.getInt("carga_horaria"));
				
				lista.add(prototype);
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return lista;
	}
}