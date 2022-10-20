package br.com.sge.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.sge.IF.DAO;
import br.com.sge.modelo.Endereco;
import br.com.sge.modelo.Instituicao;
import br.com.sge.modelo.UF;
import br.com.sge.modelo.Usuario;

public class EnderecoDAO extends DataAcessObject implements DAO<Endereco> {

	@Override
	public void atualizar(Endereco object) {

		String update = "UPDATE endereco SET cod_instituicao = ?, cod_usuario = ? bairro = LOWER(?), "
				+ "cep = ?, cidade = LOWER(?), complemento = LOWER(?), logradouro = LOWER(?), "
				+ "numero = ?, uf = ? WHERE codigo = ?;";
		try {
			PreparedStatement statement = super.openStatementCreator(update);
			statement.setInt(1, object.getCodInstituicao());
			statement.setInt(2, object.getCodigoUsuario());
			statement.setString(3, object.getBairro().toLowerCase());
			statement.setString(4, object.getCep().toLowerCase());
			statement.setString(5, object.getComplemento().toLowerCase());
			statement.setString(6, object.getLogradouro().toLowerCase());
			statement.setString(7, object.getNumero().toLowerCase());
			statement.setInt(8, object.getUf().getCodigo());
			statement.setInt(9, object.getCodigo());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
	}

	public Endereco buscar(Instituicao instituicao) {
		
		Endereco endereco = new Endereco();
		String select = "SELECT	codigo, bairro, cep, complemento, logradouro, numero, uf "
					  + "FROM endereco "
					  + "WHERE codigo = ? AND data_exclusao IS NULL;";
		try {
			
			PreparedStatement st = super.openStatementCreator(select);
			st.setInt(1, instituicao.getCodigo());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				endereco.setCodigo(rs.getInt("codigo"));
				endereco.setCep(rs.getString("cep"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setLogradouro("logradouro");
				endereco.setNumero("numero");
			}
		} catch (SQLException e) {
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
		return endereco;
	}
	
	public Endereco buscar(Usuario usuario) {
		String select = "SELECT * FROM endereco WHERE cod_usuario = ? AND data_exclusao IS NULL;";
		Endereco endereco = null;
		try {
			PreparedStatement statement = super.openStatementCreator(select);
			statement.setInt(1, usuario.getCodigo());
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				endereco = new Endereco();
				endereco.setBairro(result.getString("bairro"));
				endereco.setCep(result.getString("cep"));
				endereco.setCidade(result.getString("cidade"));
				endereco.setCodigo(result.getInt("codigo"));
				endereco.setCodigoUsuario(result.getInt("cod_usuario"));
				endereco.setComplemento(result.getString("complemento"));
				endereco.setLogradouro(result.getString("logradouro"));
				endereco.setNumero(result.getString("numero"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
		return endereco;
	}

	public void cadastrar(Usuario usuario) {
		String insert = "INSERT INTO endereco (cod_instituicao, cod_usuario, bairro, cep, cidade, complemento, logradouro, numero, uf) "
				+ "VALUES (?, ?, LOWER(?), ?, LOWER(?), LOWER(?), LOWER(?), LOWER(?), UPPER(?); ";
		try {
			PreparedStatement statement = super.openStatementCreator(insert);
			statement.setInt(1, 1);
			statement.setInt(2, usuario.getCodigo());
			statement.setString(3, usuario.getEndereco().getBairro());
			statement.setString(4, usuario.getEndereco().getCep());
			statement.setString(5, usuario.getEndereco().getCidade());
			statement.setString(6, usuario.getEndereco().getComplemento());
			statement.setString(7, usuario.getEndereco().getLogradouro());
			statement.setString(8, usuario.getEndereco().getNumero());
			statement.setInt(9, usuario.getEndereco().getUf().getCodigo());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
	}

	@Override
	public void excluir(Endereco object) {
		String delete = "UPDATE endereco SET data_exclusao = LOCALTIMESTAMP WHERE codigo = ? ;";
		try {
			PreparedStatement statement = super.openStatementCreator(delete);
			statement.setInt(1, object.getCodigo());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.disposeOpenConnectionAndStatement();
		}
	}
	
	public List<UF> listaUnidadesFederativas() {
		
		List<UF> lista = new ArrayList<>();
		String select = " SELECT  codigo, label, nome FROM unidades_federativas;";
		try {

			PreparedStatement st = super.openStatementCreator(select);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				UF uf = new UF();
				uf.setCodigo(rs.getInt("codigo"));
				uf.setLabel(rs.getString("label"));
				uf.setNome(rs.getString("nome"));
				lista.add(uf);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.disposeOpenConnectionAndStatement();
		}
		return lista;
	}
	
	@Override
	public Endereco buscar(Endereco t) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ArrayList<?> listar() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void cadastrar(Endereco endereco) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ArrayList<?> pesquisar(Endereco t) {
		// TODO Auto-generated method stub
		return null;
	}
}