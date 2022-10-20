package br.com.sge.controlador;
import java.io.IOException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import br.com.sge.dao.BoletimDAO;
import br.com.sge.dao.CursoDAO;
import br.com.sge.dao.NotaDAO;
import br.com.sge.modelo.Boletim;
import br.com.sge.modelo.Curso;
import br.com.sge.modelo.Nota;
import br.com.sge.util.Sessao;

@ManagedBean(name = "boletimMB", eager = true)
@RequestScoped
public class BoletimMB extends GeneralBean implements Controlador<Boletim> {

	private static final long serialVersionUID = 1L;
	private Boletim boletim = new Boletim();
	private Curso curso = new Curso();
	private ArrayList<Boletim> lista = new ArrayList<>();
	private ArrayList<Nota> notas = new ArrayList<>();
	
	public Boletim getBoletim() { return boletim; }
	public void setBoletim(Boletim boletim) { this.boletim = boletim;}
	
	public Curso getCurso() { return curso;}
	public void setCurso(Curso curso) { this.curso = curso;}
	
	public ArrayList<Boletim> getLista() { return lista;}
	public void setLista(ArrayList<Boletim> lista) { this.lista = lista;}

	public ArrayList<Nota> getNotas() { return notas;}
	public void setNotas(ArrayList<Nota> notas) { this.notas = notas;}
	
	public BoletimMB() {
		this.listar();
	}
	
	public void renderizarBoletim() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    try {
	    	externalContext.redirect(externalContext.getRequestContextPath() + "/view/boletim/boletim.xhtml?faces-redirect=true&cod=" + this.getBoletim().getCodigo());
	    } catch (IOException e) {
          e.printStackTrace();
	    }
	}
		
	@Override
	public void cadastrar() {
	}

	@Override
	public void listar() {
		
		BoletimDAO dao = new BoletimDAO();
		int codTipoUsuario = Sessao.getInstance().getUser().getTipoUsuario().getCodigo(); 
		if(codTipoUsuario == 2) {
			this.setLista( dao.listar(Sessao.getInstance().getUser()));
		}else if(codTipoUsuario==1) {
			this.setLista( dao.listar(Sessao.getInstance().getUser()));
		}
	}

	@Override
	public void pesquisar() {
		BoletimDAO dao = new BoletimDAO();
		this.setLista(dao.pesquisar(this.getBoletim()));
		this.setQtd(this.getLista().size());
	}

	@Override
	public void removerRegistro() {
		// TODO Auto-generated method stub
		
	}
	
	public void teste(int codBoletim) {
		NotaDAO dao = new NotaDAO();
		this.setNotas(dao.buscar(codBoletim));
		this.setQtd(this.getNotas().size());
	}
	
	public String nomeCurso(int codBoletim) {
		CursoDAO dao = new CursoDAO();
		this.boletim.setCodigo(codBoletim);
		this.setCurso(dao.buscar(this.boletim));
		return this.getCurso().getNome();
	}
	@Override
	public void editar() {
		// TODO Auto-generated method stub
		
	}
}