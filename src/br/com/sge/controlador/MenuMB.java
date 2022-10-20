package br.com.sge.controlador;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import br.com.sge.dao.MenuDAO;
import br.com.sge.modelo.MenuNavegacao;
import br.com.sge.util.Sessao;

@ManagedBean (name="menuMB")
public class MenuMB extends GeneralBean{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<MenuNavegacao> menu = new ArrayList<>();
	
	public MenuMB() {
		MenuDAO dao = new MenuDAO();
		this.menu = dao.listarMenu();		
	}
	
	public ArrayList<MenuNavegacao> getMenu() {
		
		if(Sessao.getInstance().getUser() == null) {
			return this.menu;
		}else {
			MenuDAO dao = new MenuDAO();
			return dao.listarMenu(Sessao.getInstance().getUser());
		}				
		
	}
}