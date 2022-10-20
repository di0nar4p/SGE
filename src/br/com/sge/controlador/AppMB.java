package br.com.sge.controlador;
import javax.faces.bean.ManagedBean;
import br.com.sge.modelo.App;
import br.com.sge.modelo.Modelo;

@ManagedBean(name="appMB")
public class AppMB extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	App aplication = new App();

	public App getAplication(){return this.aplication;}
	
	public static String navToHome(){
		return "home.xhtml";
	}
}