package br.com.sge.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.sge.modelo.MenuNavegacao;
import br.com.sge.modelo.Usuario;
import br.com.sge.util.Sessao;

/**
* @author: Eduardo Marinho
* @since: 08/11/2018
**/
public class MenuDAO extends DataAcessObject {
	
	/**
	* @description: consulta os menus principais que foram cadastrados na base de dados
	* @return: {@link ArrayList<MenuNavegacao()>}
	* @throws SQLException
	**/
	public ArrayList<MenuNavegacao> listarMenu(){
		
		String select = "SELECT codigo, label, titulo, url FROM id53_sge.menu WHERE cod_menu IS NULL;";
		ArrayList<MenuNavegacao> lista = new ArrayList<MenuNavegacao>();
		try {
			PreparedStatement st = super.openStatementCreator(select);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {

				MenuNavegacao mv = new MenuNavegacao();
				mv.setCodigo(rs.getInt("codigo"));
				mv.setLabel(rs.getString("label"));
				mv.setTitulo(rs.getString("titulo"));
				mv.setUrl(rs.getString("url"));
				lista.add(mv);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return menuCompleto(lista);
	}
	
	public ArrayList<MenuNavegacao> listarMenu(Usuario usuario){
		
		String select 	= "SELECT 	m.codigo, m.label, m.titulo, m.url "
						+ "FROM		id53_sge.usuario u "
						+ "JOIN		id53_sge.tipo_usuario tu ON tu.codigo = u.cod_tipo_usuario AND tu.data_exclusao IS NULL "
						+ "JOIN		id53_sge.grupo_funcao gf ON gf.cod_tipo_usuario = tu.codigo "
						+ "JOIN 	id53_sge.funcao_usuario fu ON fu.codigo = gf.cod_funcao_usuario "
						+ "JOIN 	id53_sge.menu m ON m.codigo = fu.cod_menu_funcao "
						+ "WHERE 	m.cod_menu IS NULL "
						+ "AND		u.cod_tipo_usuario = ? "
						+ "AND		u.codigo = ? "
						+ "AND		m.data_exclusao IS NULL ;";
		ArrayList<MenuNavegacao> lista = new ArrayList<MenuNavegacao>();
		try {
			 
			PreparedStatement st = super.openStatementCreator(select);
			st.setInt(1, usuario.getTipoUsuario().getCodigo());
			st.setInt(2, usuario.getCodigo());
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {

				MenuNavegacao mv = new MenuNavegacao();
				mv.setCodigo(rs.getInt("codigo"));
				mv.setLabel(rs.getString("label"));
				mv.setTitulo(rs.getString("titulo"));
				mv.setUrl(rs.getString("url"));
				lista.add(mv);
			}
			return menuCompleto(lista);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	} 	
	
	/**
	* @description: consulta no banco se existem submenus associados ao menu passado por parâmetro.
	* @param menu MenuNavegacao
	* @return {@link ArrayList} MenuNavegacao
	 * @throws SQLException 
	**/
	private ArrayList<MenuNavegacao> menuCompleto(ArrayList<MenuNavegacao> menu){
		
		String select = null;
		if(Sessao.getInstance().getUser()==null) {
			select = "SELECT codigo, cod_menu, label, titulo, url FROM id53_sge.menu WHERE cod_menu = ? ORDER BY label";	
		}else {
			select 	= "SELECT 	m.codigo, m.cod_menu, m.label, m.titulo, m.url "
					+ "FROM		id53_sge.usuario u "
					+ "JOIN		id53_sge.tipo_usuario tu ON tu.codigo = u.cod_tipo_usuario AND tu.data_exclusao IS NULL "
					+ "JOIN		id53_sge.grupo_funcao gf ON gf.cod_tipo_usuario = tu.codigo "
					+ "JOIN 	id53_sge.funcao_usuario fu ON fu.codigo = gf.cod_funcao_usuario "
					+ "JOIN 	id53_sge.menu m ON m.codigo = fu.cod_menu_funcao "
					+ "WHERE 	m.cod_menu = ? "
					+ "AND		u.cod_tipo_usuario = ? "
					+ "AND		u.codigo = ? "
					+ "AND		m.data_exclusao IS NULL "
					+ "ORDER BY m.label";
		}
		
		
		//@desc: realiza a iteração de acordo com o tamenho do menu.
		for(int i = 0; i < menu.size(); i++) {
			ArrayList<MenuNavegacao> submenu = new ArrayList<>();
			try {
				
				PreparedStatement st = super.openStatementCreator(select);
				st.setInt(1, menu.get(i).getCodigo());
				if(Sessao.getInstance().getUser()!=null) {
					st.setInt(2, Sessao.getInstance().getUser().getTipoUsuario().getCodigo());
					st.setInt(3, Sessao.getInstance().getUser().getCodigo());
				}
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {

					MenuNavegacao item = new MenuNavegacao();
					item.setCodigo(rs.getInt("codigo"));
					item.setCodMenu(rs.getInt("cod_menu"));
					item.setLabel(rs.getString("label"));
					item.setTitulo(rs.getString("titulo"));
					item.setUrl(rs.getString("url"));
					submenu.add(item);
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
			menu.get(i).setSubmenu(submenu);
		}
		return menu;
	}
}