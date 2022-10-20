package br.com.sge.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sge.IF.DAO;
import br.com.sge.modelo.Contato;
import br.com.sge.modelo.Disciplina;
import br.com.sge.modelo.TipoUsuario;
import br.com.sge.modelo.Usuario;
import br.com.sge.util.MD5Cryption;

public class UsuarioDAO extends DataAcessObject implements DAO<Usuario>{
	
	@Override
	public Usuario buscar(Usuario object){
		
		String select = "SELECT		u.codigo, u.nome_usuario, u.nome_completo, u.matricula, u.senha, u.cod_tipo_usuario, c.email, tp.nome "
					  + "FROM 		id53_sge.usuario u "
					  +	"JOIN		id53_sge.tipo_usuario tp ON tp.codigo = u.cod_tipo_usuario AND tp.data_exclusao IS NULL "
					  + "LEFT JOIN	id53_sge.contato c ON c.cod_usuario = u.codigo AND c.data_exclusao IS NULL "
					  + "WHERE  	LOWER(nome_usuario) = LOWER(?) AND senha = (?)	"
					  + "AND		u.data_exclusao IS NULL";
		Usuario usuario = new Usuario();
		try{
			PreparedStatement statement = super.openStatementCreator(select);
			statement.setString(1, object.getNomeUsuario());
			statement.setString(2, MD5Cryption.transformStringToMD5(object.getSenha()));
			ResultSet result = statement.executeQuery();
			if(result.next()){
				usuario.setCodigo(result.getInt("codigo"));
				usuario.setNomeUsuario(result.getString("nome_usuario"));
				usuario.setNomeCompleto(result.getString("nome_completo"));
				usuario.setMatricula(result.getString("matricula"));
				usuario.setSenha(result.getString("senha"));
				Contato contato = new Contato();
				contato.setEmail(result.getString("email"));
				usuario.setContato(contato);				
				TipoUsuario tipoUsuario = new TipoUsuario();				
				tipoUsuario.setCodigo(result.getInt("cod_tipo_usuario"));
				tipoUsuario.setNome(result.getString("nome"));
				usuario.setTipoUsuario(tipoUsuario);
			}
			if(usuario.getCodigo() > 0) {
				return usuario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
		return null;
	}
	
	@Override
	public ArrayList<Usuario> listar() {
		String select ="SELECT		u.nome_usuario, u.nome_completo, u.matricula, u.senha, u.codigo, u.cod_tipo_usuario, "+
					   				"c.codigo, c.email, c.telefone, c.celular, "+
					   				"tu.nome "+
					   "FROM    	usuario u "+
					   "LEFT JOIN 	contato c ON c.cod_usuario = u.codigo AND c.data_exclusao IS NULL "+
					   "JOIN		tipo_usuario tu ON tu.codigo = u.cod_tipo_usuario AND tu.data_exclusao IS NULL ";
		select+= " AND u.data_exclusao IS NULL ORDER BY    u.nome_completo;";
		ArrayList<Usuario> lista = new ArrayList<>();
		try {
			PreparedStatement statement = super.openStatementCreator(select);
			ResultSet result = statement.executeQuery();

			while(result.next()){

				Usuario usuario = new Usuario();
				TipoUsuario tp = new TipoUsuario();
				Contato c = new Contato();

				tp.setCodigo(result.getInt("cod_tipo_usuario"));
				tp.setNome(result.getString("nome"));

				c.setCodUsuario(result.getInt("codigo"));
				c.setCelular(result.getString("celular"));
				c.setEmail(result.getString("email"));
				c.setTelefone(result.getString("telefone"));
				
				usuario.setContato(c);
				usuario.setTipoUsuario(tp);
				
				usuario.setCodigo(result.getInt("codigo"));
				usuario.setNomeUsuario(result.getString("nome_usuario"));
				usuario.setNomeCompleto(result.getString("nome_completo"));
				usuario.setMatricula(result.getString("matricula"));
				usuario.setSenha(result.getString("senha"));				

				lista.add(usuario);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
		return lista;
	}
	
	/***
	* @description: Realiza a consulta dos professores associados à disciplina. 
	* @param disciplina
	* @return
	*/
	public ArrayList<Usuario> listar(Disciplina disciplina){
		
		ArrayList<Usuario> lista = new ArrayList<>();
		String select = "SELECT	u.codigo, u.nome_completo " + 
						"FROM 	usuario u " + 
						"JOIN	disciplina_professor dp ON dp.cod_professor = u.codigo AND dp.data_exclusao IS NULL " + 
						"WHERE 	u.cod_tipo_usuario = 1 " + 
						"AND	dp.cod_disciplina = 2 " +
						"AND 	u.data_exclusao IS NULL";
		try{
			PreparedStatement statement = super.openStatementCreator(select);
			ResultSet result =  statement.executeQuery();
			while(result.next()){
				Usuario professor = new Usuario();
				professor.setCodigo(result.getInt("u.codigo"));
				professor.setNomeCompleto(result.getString("nome_completo"));
				lista.add(professor);
			}
			return lista;
		}catch(SQLException e){
			System.err.println(e.getMessage());
		}finally {
			super.disposeOpenConnectionAndStatement();
		}
		return lista;
	}
	
	@Override
	public void cadastrar(Usuario object){
		
		String insert =   "INSERT INTO 	 usuario"
						+ "             (cod_tipo_usuario, data_nascimento, "
						+ "				 matricula, nome_completo, nome_usuario, senha) "
						+ "VALUES 	     (?, ?, ?, ?, ?, ?);";
		try {
			
			PreparedStatement statement = super.openConnection().prepareStatement(insert, java.sql.Statement.RETURN_GENERATED_KEYS);

			statement.setInt(1, object.getTipoUsuario().getCodigo());
			statement.setDate(2, new java.sql.Date(object.getDataNascimento().getTime()));
			statement.setString(3, object.getMatricula());
			statement.setString(4, object.getNomeCompleto());
			statement.setString(5, object.getNomeUsuario());
			statement.setString(6, object.getSenha());
			statement.executeUpdate();
			
			ResultSet keys = statement.getGeneratedKeys();
			if(keys.next()) {
				ContatoDAO contato = new ContatoDAO();
				object.setCodigo(keys.getInt(1));
				contato.cadastrar(object.getContato(), object.getCodigo());
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
	}

	@Override
	public void atualizar(Usuario object) {
		String update =   "UPDATE 		usuario"
						+ "SET 			cod_tipo_usuario = ?, data_nascimento = ?, matricula = ?,"
						+ " 			nome_completo = ?, nome_usuario = ?, senha = ?"
						+ "WHERE 		codigo = ? AND data_exclusao IS NULL;";
		try {
			PreparedStatement statement = super.openStatementCreator(update);
			statement.setInt(1, object.getTipoUsuario().getCodigo());
			statement.setDate(2, new java.sql.Date(object.getDataNascimento().getTime()));
			statement.setString(3, object.getMatricula());
			statement.setString(4, object.getNomeCompleto());
			statement.setInt(5, object.getCodigo());
			super.disposeOpenConnectionAndStatement();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			this.disposeOpenConnectionAndStatement();
		}
	}
	
	/**
	*	 @description Excluir logicamente o usuário
	*	 @param  a object type Usuario
	*/
	@Override
	public void excluir(Usuario object) {
		String delete =   "UPDATE 			usuario "
						+ "SET 				data_exclusao = CURRENT_TIMESTAMP "
						+ "WHERE 			usuario.codigo = ? ;";
		try {
			PreparedStatement statement = super.openStatementCreator(delete);
			statement.setInt(1, object.getCodigo());
			statement.execute();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			this.disposeOpenConnectionAndStatement();
		}
	}
	
	public List<Usuario> listarAlunosParaMatricula(){
		
		List<Usuario> alunos = new ArrayList<>();
		String select = "SELECT	codigo, nome_completo, nome_usuario, matricula, senha, data_nascimento "+ 
						"FROM 	usuario "+
						"WHERE 	cod_tipo_usuario = 2"+
						"AND	data_exclusao IS NULL";
		try {
			PreparedStatement statement = super.openStatementCreator(select);
			ResultSet rs = statement.executeQuery(); 
			while(rs.next()) {

				Usuario aluno = new Usuario();
				aluno.setCodigo(rs.getInt("codigo"));
				aluno.setNomeCompleto(rs.getString("nome_completo"));
				aluno.setNomeUsuario(rs.getString("nome_usuario"));
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setSenha(rs.getString("senha"));
				aluno.setDataNascimento(rs.getDate("data_nascimento"));
				
				alunos.add(aluno);
			}
		} catch (SQLException ex) {
		}
		return alunos;
	}

	@Override
	public ArrayList<Usuario> pesquisar(Usuario usuario) {

		String select = "SELECT  	u.codigo, u.nome_completo, u.nome_usuario, u.matricula, " 
					  + "			c.email, tp.nome As tipo_usuario " 
					  + "FROM		id53_sge.usuario u "
					  +	"LEFT JOIN	id53_sge.contato c ON c.cod_usuario = u.codigo AND c.data_exclusao IS NULL "
					  +	"JOIN		id53_sge.tipo_usuario tp ON tp.codigo = u.cod_tipo_usuario AND tp.data_exclusao IS NULL " 
					  + "WHERE		(LOWER(u.nome_completo) LIKE( LOWER(?)) OR LOWER(u.nome_usuario) LIKE( LOWER(?)) "
					  + "OR 		u.matricula LIKE(?) OR LOWER(c.email) LIKE( LOWER(?)) OR LOWER(tp.nome) LIKE( LOWER(?))) " 
					  +	"AND		u.data_exclusao IS NULL "
					  + "ORDER BY	u.nome_completo;";
		ArrayList<Usuario> lista = new ArrayList<>();
		try {

			PreparedStatement st = super.openStatementCreator(select);
			st.setString(1, "%" + usuario.getMisc() + "%");
			st.setString(2, "%" + usuario.getMisc() + "%");
			st.setString(3, "%" + usuario.getMisc() + "%");
			st.setString(4, "%" + usuario.getMisc() + "%");
			st.setString(5, "%" + usuario.getMisc() + "%");
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				
				Usuario prototype = new Usuario();
				prototype.setCodigo(rs.getInt("codigo"));
				prototype.setNomeCompleto(rs.getString("nome_completo"));
				prototype.setNomeUsuario(rs.getString("nome_usuario"));
				prototype.setMatricula(rs.getString("matricula"));
				
				Contato contato = new Contato();
				contato.setEmail(rs.getString("email"));
				prototype.setContato(contato);
				
				TipoUsuario tipoUsuario = new TipoUsuario();
				tipoUsuario.setNome(rs.getString("tipo_usuario"));				
				prototype.setTipoUsuario(tipoUsuario);
				
				lista.add(prototype);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			super.disposeOpenConnectionAndStatement();
		}
		return lista;
	}
}