package br.com.sge.util;
import javax.faces.context.FacesContext;
import br.com.sge.modelo.Usuario;

public class Sessao{
	
	private static Sessao sessao;
	private Usuario user;
	
	private Sessao() {}
	
	public static Sessao getInstance() {
		if(sessao == null) {
			sessao = new Sessao();
		}
		return sessao;
	}
	
	public Usuario getUser() { 
		return this.user;
	}
	
	public void setUser(Usuario usuario) { this.user = usuario;}
	
	/**
	* @Description: recebe o objeto que será carregado para a sessão da aplicação e retorna a página de home 
	* @param usuario
	* @return String
	*/
	public static Usuario sessionStart(Usuario usuario){
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("usuario.logado", usuario);		
		getInstance().setUser((Usuario) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("usuario.logado"));
		return getInstance().getUser();
	}
	
	public void sessionEnd(){
		getInstance().setUser(null);
		//FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().clear();
	}

	@Override
	public String toString() {
		return "Sessao [getUser()=" + getUser() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}