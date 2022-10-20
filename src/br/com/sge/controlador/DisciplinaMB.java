package br.com.sge.controlador;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import br.com.sge.dao.DisciplinaDAO;
import br.com.sge.modelo.Disciplina;

@ManagedBean (name = "disciplinaMB")
@RequestScoped
public class DisciplinaMB extends GeneralBean implements Controlador<Disciplina>{

	private static final long serialVersionUID = 1L;
	private ArrayList<Disciplina> lista = new ArrayList<>();
	private Disciplina disciplina = new Disciplina();
	
	public DisciplinaMB() {
		this.listar();
	}
	
	public ArrayList<Disciplina> getLista() { return lista;}
	public void setLista(ArrayList<Disciplina> lista) { this.lista = lista;}
	
	public Disciplina getDisciplina() { return disciplina;}
	public void setDisciplina(Disciplina disciplina) { this.disciplina = disciplina;}

	@Override
	public void cadastrar() {
		DisciplinaDAO dao = new DisciplinaDAO();
		dao.cadastrar(this.disciplina);
		NavigationMB nav = new NavigationMB();
		nav.redirect("disciplinas/disciplinas");
	}

	@Override
	public void listar() {
		DisciplinaDAO dao = new DisciplinaDAO();
		this.setLista(dao.listar());
		this.setQtd(this.getLista().size());
	}

	@Override
	public void pesquisar() {
		DisciplinaDAO dao = new DisciplinaDAO();		
		this.setLista(dao.pesquisar(this.getDisciplina()));
		this.setQtd(this.getLista().size());
	}
	
	@Override
	public void removerRegistro() {
		DisciplinaDAO dao = new DisciplinaDAO();
		dao.excluir(this.disciplina);
		this.disciplina = null;
	}

	@Override
	public void editar() {
		// TODO Auto-generated method stub
		
	}
}
