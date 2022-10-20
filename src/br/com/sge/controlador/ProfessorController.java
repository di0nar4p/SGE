package br.com.sge.controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.sge.modelo.Usuario;

@ManagedBean(name = "professorController")
@SessionScoped
public class ProfessorController {
	private Usuario usuario;
	private Usuario usuarioSelecionado;
	
	public Usuario getUsuario() { return usuario; }
	public void setUsuario(Usuario usuario) { this.usuario = usuario; }
	
	public Usuario getUsuarioSelecionado() { return usuarioSelecionado; }
	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {	this.usuarioSelecionado = usuarioSelecionado; }
	
	public String consultar() {
		return "/view/professor/consultar.xhtml?faces-redirect=true";
	}
}
