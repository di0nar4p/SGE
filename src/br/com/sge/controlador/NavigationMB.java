package br.com.sge.controlador;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name="navMB", eager=true)
@RequestScoped
public class NavigationMB extends GeneralBean{

	private static final long serialVersionUID = 1L;
	private String erro = null;

	public String getErro() { return erro;}
	public void setErro(String erro) { this.erro = erro;}

	public String goToHome() {
		return "/home.xhtml?faces-redirect=true";
	}
	
	public String goTo(String url) {		
		return "view/" + url + ".xhtml?faces-redirect=true";
	}
	
	public void redirect(String url) {
		
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			if(url.equals("home")) {
				externalContext.redirect(externalContext.getRequestContextPath() + "/home.xhtml?faces-redirect=true");
			}else {
				externalContext.redirect(externalContext.getRequestContextPath() + "/view/" + url + ".xhtml?faces-redirect=true");
			}
	    } catch (IOException e) {
	          e.printStackTrace();
	    }
	}
}