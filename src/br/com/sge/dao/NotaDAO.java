package br.com.sge.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.sge.IF.DAO;
import br.com.sge.modelo.Nota;

public class NotaDAO extends DataAcessObject implements DAO<Nota> {

	@Override
	public void cadastrar(Nota t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Nota t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Nota buscar(Nota t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Nota> buscar(int codBoletim) {
		
		String select = "SELECT	n.codigo, n.primeira_nota, n.segunda_nota, n.terceira_nota, n.quarta_nota, n.nota_final, "
					  + "ROUND(((n.primeira_nota + n.segunda_nota + n.terceira_nota + n.quarta_nota) / 4), 2) AS media, "
					  +	"		d.nome AS disciplina "
					  + "FROM	id53_sge.notas n "
					  + "JOIN	id53_sge.usuario a	ON	a.codigo = n.cod_usuario AND	a.data_exclusao IS NULL "
					  +	"JOIN	id53_sge.disciplina d	ON	d.codigo = n.cod_disciplina AND d.data_exclusao IS NULL "
					  + "JOIN	id53_sge.boletim b	ON b.codigo = n.cod_boletim "
					  + "WHERE	b.codigo = ?";
		ArrayList<Nota> lista = new ArrayList<>();
		try {
			PreparedStatement st  = super.openStatementCreator(select);
			st.setInt(1, codBoletim);
			ResultSet rs = st.executeQuery();			
			while (rs.next()) {
				
				Nota nota = new Nota();
				nota.setCodigo(rs.getInt("codigo"));
				nota.setPrimeiraNota(rs.getDouble("primeira_nota"));
				nota.setSegundaNota(rs.getDouble("segunda_nota"));
				nota.setTerceiraNota(rs.getDouble("terceira_nota"));
				nota.setQuartaNota(rs.getDouble("quarta_nota"));
				nota.setNotaFinal(rs.getDouble("nota_final"));
				nota.setMedia(rs.getDouble("media"));
				nota.setDisciplina(rs.getString("disciplina"));
				lista.add(nota);
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}finally {
			super.disposeOpenConnectionAndStatement();
		}
		return lista;
	}

	@Override
	public ArrayList<?> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizar(Nota t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<?> pesquisar(Nota t) {
		// TODO Auto-generated method stub
		return null;
	}

}