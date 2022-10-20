package br.com.sge.controlador;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.sge.dao.UsuarioDAO;
import br.com.sge.modelo.Usuario;
import br.com.sge.util.Sessao;

@ApplicationScoped
@ManagedBean(name="sessaoMB", eager=true)
@SessionScoped
public class SessaoMB extends GeneralBean{

	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public void setUsuario(Usuario user){
		this.usuario = user;
	}
	
	/***
	* @description: realiza o processo de login do usuário. 
	* @return String
	*/
	public String login(){
		
		UsuarioDAO dao = new UsuarioDAO();
		Usuario user = new Usuario();
		user = dao.buscar(this.usuario);
		if(user == null){
			return "index?faces-redirect=true";
		}else{
			Sessao.getInstance();
			this.usuario = Sessao.sessionStart(user);
			return "home.xhtml?faces-redirect=true";
		}
	}
	
	public String logout() {
		Sessao.getInstance().sessionEnd();
		this.usuario = new Usuario();
		return "/index.xhtml?faces-redirect=true";
	}
}