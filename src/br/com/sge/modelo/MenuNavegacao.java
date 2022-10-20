package br.com.sge.modelo;

import java.util.ArrayList;

public class MenuNavegacao extends Modelo{

	private static final long serialVersionUID = 1L;
	private String url;
	private int codMenu;
	private ArrayList<MenuNavegacao> submenu = new ArrayList<>();

	public ArrayList<MenuNavegacao> getSubmenu() {
		return submenu;
	}
	public void setSubmenu(ArrayList<MenuNavegacao> submenu) {
		this.submenu = submenu;
	}
	public int getCodMenu() {
		return codMenu;
	}
	public void setCodMenu(int codMenu) {
		this.codMenu = codMenu;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "MenuNavegacao [url=" + url + ", codMenu=" + codMenu + ", submenu=" + submenu + "]";
	}
}