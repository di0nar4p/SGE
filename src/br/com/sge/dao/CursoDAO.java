package br.com.sge.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import br.com.sge.IF.DAO;
import br.com.sge.modelo.Boletim;
import br.com.sge.modelo.Curso;
import br.com.sge.modelo.Disciplina;
import br.com.sge.modelo.GradeCurso;
import br.com.sge.modelo.Usuario;
import br.com.sge.util.Sessao;

public class CursoDAO extends DataAcessObject implements DAO<Curso> {

	@Override
	public void atualizar(Curso object){
		String update =   "UPDATE 		curso "
						+ "SET 			carga_horaria = ?, descricao = ?, duracao = ?, nome = ? "
						+ "WHERE  		codigo = ?;";
		try{
			PreparedStatement statement = super.openStatementCreator(update);
			statement.setInt(1, object.getCargaHoraria());
			statement.setString(2, object.getDescricao());
			statement.setString(3, object.getDuracao());
			statement.setString(4, object.getTitulo());
			statement.setInt(5, object.getCodigo());
			statement.executeQuery();
		}catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
	}

	@Override
	public void cadastrar(Curso object){
		String insert =   "INSERT INTO 	curso (carga_horaria, descricao, duracao, nome) "
						+ "VALUES 	(?, ?, ?, ?)";
		try {
			
			PreparedStatement statment = super.openStatementCreator(insert);
			statment.setInt(1, object.getCargaHoraria());
			statment.setString(2, object.getDescricao());
			statment.setString(3, object.getDuracao());
			statment.setString(4, object.getNome());
			statment.execute();
		}catch (SQLException e){
			System.out.println(e.getMessage());
		} finally {
			super.disposeOpenConnectionAndStatement();
		}		
	}

	@Override
	public void excluir(Curso object) {
		String delete = "UPDATE curso SET data_exclusao = CURRENT_TIMESTAMP WHERE codigo = ? ;";
		try {
			PreparedStatement statment = super.openStatementCreator(delete);
			statment.setInt(1, object.getCodigo());
			statment.execute();
		} catch (SQLException error) {
			System.out.println(error.getMessage());
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
	}

	@Override
	public ArrayList<Curso> listar(){
		
		String select = "SELECT  codigo, carga_horaria, LEFT(descricao, 300) AS descricao, duracao, nome, data_inclusao FROM curso "
				      + "WHERE data_exclusao IS NULL ORDER BY nome;";
		ArrayList<Curso> lista = new ArrayList<Curso>();
		try{
		
			PreparedStatement statement = super.openStatementCreator(select);
			ResultSet result = statement.executeQuery();
			while (result.next()){
				Curso curso = new Curso();
				curso.setCodigo(result.getInt("codigo"));
				curso.setCargaHoraria(result.getInt("carga_horaria"));
				curso.setDescricao(result.getString("descricao"));
				curso.setDuracao(result.getString("duracao"));
				curso.setNome(result.getString("nome"));
				java.sql.Date databaseDate = result.getDate("data_inclusao");
				curso.setDataInclusao(new Date(databaseDate.getTime()));
				lista.add(curso);
			}
		} catch (SQLException error) {
			System.out.println(error.getMessage());
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
		return lista;
	}
	
	public ArrayList<Curso> listar(Usuario professor){
		
		String select 	= "SELECT	DISTINCT "
						+ "			c.codigo, c.carga_horaria, LEFT(descricao, 300) AS descricao, c.duracao, c.nome, c.data_inclusao "
						+ "FROM 	id53_sge.curso c "
						+ "JOIN		id53_sge.curso_disciplina cd ON cd.cod_curso = c.codigo AND cd.data_exclusao IS NULL "
						+ "JOIN		id53_sge.disciplina_professor dp ON dp.cod_disciplina = cd.cod_disciplina "
						+ "WHERE 	c.data_exclusao IS NULL "
						+ "AND		dp.cod_professor = ? "
						+ "ORDER BY c.nome";
		ArrayList<Curso> lista = new ArrayList<Curso>();
		try{
		
			PreparedStatement statement = super.openStatementCreator(select);
			statement.setInt(1, professor.getCodigo());
			ResultSet result = statement.executeQuery();
			while (result.next()){
				Curso curso = new Curso();
				curso.setCodigo(result.getInt("codigo"));
				curso.setCargaHoraria(result.getInt("carga_horaria"));
				curso.setDescricao(result.getString("descricao"));
				curso.setDuracao(result.getString("duracao"));
				curso.setNome(result.getString("nome"));
				java.sql.Date databaseDate = result.getDate("data_inclusao");
				curso.setDataInclusao(new Date(databaseDate.getTime()));
				lista.add(curso);
			}
		} catch (SQLException error) {
			System.out.println(error.getMessage());
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
		return lista;
	}

	public ArrayList<Curso> listar(Curso curso) {
		String select = "SELECT  codigo, carga_horaria, descricao, duracao, nome, data_inclusao FROM curso"
				+ "	WHERE LOWER(descricao) LOWER(LIKE(?)) || LOWER(nome) LOWER(LIKE(?)) || LOWER(data_inclusao)"
				+ " LOWER(LIKE(?)) AND data_exclusao IS NULL ORDER BY nome;";
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		try {
			PreparedStatement statement = super.openStatementCreator(select);
			statement.setString(1, curso.getDescricao().toLowerCase());
			statement.setString(2, "%" + curso.getDescricao().toLowerCase() + "%");
			statement.setString(3, curso.getNome().toLowerCase());
			statement.setString(4, "%" + curso.getNome().toLowerCase() + "%");
			statement.setDate(5, new java.sql.Date(curso.getDataInclusao().getTime()));
			statement.setDate(6, new java.sql.Date(curso.getDataInclusao().getTime()));
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Curso cursoObject = new Curso();
				curso.setCodigo(result.getInt("codigo"));
				curso.setCargaHoraria(result.getInt("carga_horaira"));
				curso.setDescricao(result.getString("descricao"));
				curso.setDuracao(result.getString("duracao"));
				curso.setNome(result.getString("nome"));
				curso.setDataInclusao(new Date(result.getDate("data_inclusao").getTime()));
				cursos.add(cursoObject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
		return cursos;
	}

	public void recuperar(Curso object) {
		String recover = "UPDATE curso SET data_exclusao = NULL WHERE codigo = ? ;";
		try {
			PreparedStatement statement = super.openStatementCreator(recover);
			statement.setInt(1, object.getCodigo());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
	}

	private void associar(Curso curso) {
		String insert = "INSERT INTO instituicao_curso (cod_curso, cod_instituicao, "
				+ "data_inclusao) VALUES (?, 1, LOCALTIMESTAMP);";
		try {
			PreparedStatement statement = super.openStatementCreator(insert);
			statement.setInt(1, curso.getCodigo());
			statement.execute();
		} catch (SQLException error) {
			System.out.println(error.getMessage());
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
	}

	@Override
	public Curso buscar(Curso curso) {
		
		String select = "SELECT * FROM curso WHERE codigo = ? ORDER BY nome";
		Curso cursoDto = new Curso();
		try {
			PreparedStatement statement = super.openStatementCreator(select);
			statement.setInt(1, curso.getCodigo());
			ResultSet result = statement.executeQuery();			
			if (result.next()) {
				cursoDto.setNome(result.getString("nome"));
				cursoDto.setDescricao(result.getString("descricao"));
				cursoDto.setCargaHoraria(result.getInt("carga_horaria"));
				cursoDto.setDuracao(result.getString("duracao"));
			}
			System.out.println(statement.toString());
		} catch (SQLException error) {
			System.out.println(error.getMessage());
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
		
		return cursoDto;
	}
	
	public Curso buscar(int codCurso) {
		Curso curso = new Curso();
		curso.setCodigo(codCurso);
		return this.buscar(curso);
	}
	
	public Curso buscar(Boletim boletim) {
		
		String select 	= "SELECT	c.codigo, c.nome "
						+ "FROM 	id53_sge.curso c "
						+ "JOIN		id53_sge.boletim b ON b.cod_curso = c.codigo "
						+ "AND		b.codigo = ?";
		try {
			Curso curso = new Curso();
			PreparedStatement st = super.openStatementCreator(select);
			st.setInt(1, boletim.getCodigo());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				curso.setCodigo(rs.getInt("codigo"));
				curso.setNome(rs.getString("nome"));
			}
			return curso;
		} catch (SQLException ex) {
			System.out.println(ex);
		}finally {
			super.disposeOpenConnectionAndStatement();
		}
		return null;
	}
	
	public Curso buscarGradePorAluno() throws SQLException{
		
		String select = "SELECT		d.nome AS disciplina, d.label AS sigla_disciplina, d.carga_horaria As carga_horaria_disciplina, "+
				 	 	"			p.nome_completo AS docente "+
						"FROM		curso_aluno ca "+
						"JOIN		curso c ON c.codigo = ca.cod_curso "+
						"JOIN		curso_disciplina cd ON cd.cod_curso =  c.codigo "+
						"JOIN		disciplina d ON d.codigo = cd.cod_disciplina "+
						"JOIN		disciplina_professor dp ON dp.cod_disciplina = cd.cod_disciplina "+
						"JOIN		usuario p ON p.codigo = dp.cod_professor "+
						"WHERE		ca.cod_aluno = ? "+
						"ORDER BY 	d.nome";
		PreparedStatement statment = super.openStatementCreator(select);
		statment.setInt(1, Sessao.getInstance().getUser().getCodigo());
		ResultSet result =  statment.executeQuery();
		GradeCurso grade = new GradeCurso();
		while(result.next()){
			Disciplina disciplina = new Disciplina();
			disciplina.setNome(result.getString("disciplina"));
			disciplina.setLabel(result.getString("sigla_disciplina"));
			disciplina.setCargaHoraria(result.getInt("carga_horaria_disciplina"));
			grade.setDisciplina(disciplina);
			
			Usuario docente = new Usuario();
			docente.setNomeCompleto(result.getString("docente"));
			grade.setDocentes(docente);
		}
		Curso curso = new Curso();
		curso.setGrade(grade);
		return curso;
	}

	@Override
	public ArrayList<Curso> pesquisar(Curso curso) {
		
		String select = "SELECT  codigo, carga_horaria, LEFT(descricao, 300) AS descricao, duracao, nome, data_inclusao "
					  + "FROM 	 curso "
					  + "WHERE 	 (LOWER(nome) LIKE( LOWER (?)) OR LOWER(descricao) LIKE( LOWER(?))) "
			      	  + "AND 	 data_exclusao IS NULL ORDER BY nome;";
		ArrayList<Curso> lista = new ArrayList<>();
		try {
			
			PreparedStatement st = super.openStatementCreator(select);
			st.setString(1, "%" + curso.getMisc() + "%");
			st.setString(2, "%" + curso.getMisc() + "%" );
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Curso prototype = new Curso();
				prototype.setCodigo(rs.getInt("codigo"));
				prototype.setCargaHoraria(rs.getInt("carga_horaria"));
				prototype.setDescricao(rs.getString("descricao"));
				prototype.setDuracao(rs.getString("duracao"));
				prototype.setNome(rs.getString("nome"));
				lista.add(prototype);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}finally {
			super.disposeOpenConnectionAndStatement();
		}
		return lista;
	}
	
	public ArrayList<Disciplina> listarGradePorCodCurso(Curso curso) {
		
		String select = "SELECT		d.codigo, d.nome, d.label, d.carga_horaria, d.descricao "
					  + "FROM		id53_sge.curso_disciplina cd " 
					  + "JOIN		id53_sge.curso c ON c.codigo = cd.cod_curso AND c.data_exclusao IS NULL " 
					  + "JOIN		id53_sge.disciplina d ON d.codigo = cd.cod_disciplina AND d.data_exclusao IS NULL " 
					  + "WHERE		c.codigo = ? " 
					  + "ORDER BY	d.nome";
		ArrayList<Disciplina> lista = new ArrayList<>();
		try {
			
			PreparedStatement st = super.openStatementCreator(select);
			st.setInt(1, curso.getCodigo());
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				Disciplina prototype = new Disciplina();
				prototype.setCodigo(rs.getInt("codigo"));
				prototype.setNome(rs.getString("nome"));
				prototype.setLabel(rs.getString("label"));
				prototype.setCargaHoraria(rs.getInt("carga_horaria"));
				prototype.setDescricao(rs.getString("descricao"));
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
	
	private void grade() {
		
		String select = "SELECT		d.nome AS disciplina, d.label As sigla, d.carga_horaria, d.descricao, "
					  + "			p.nome_completo AS docente, "
					  + "			ct.email "
					  + "FROM		id53_sge.curso_disciplina "
					  + "JOIN		id53_sge.curso c ON c.codigo = cd.cod_curso AND c.data_exclusao IS NULL "
					  + "JOIN		id53_sge.disciplina d ON d.codigo = cd.cod_disciplina AND d.data_exclusao IS NULL "
					  + "JOIN		id53_sge.disciplina_professor dp ON dp.cod_disciplina = d.codigo AND dp.data_exclusao IS NULL "
					  + "JOIN		id53_sge.usuario p ON p.codigo = dp.cod_professor AND p.data_exclusao IS NULL "
					  + "LEFT JOIN	id53_sge.contato ct ON ct.cod_usuario = p.codigo AND ct.data_exclusao IS NULL "
					  + "WHERE		c.codigo = 4" //Código do curso;
					  + "ORDER BY	d.nome";
	}
}