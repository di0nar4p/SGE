package br.com.sge.controlador;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import br.com.sge.dao.TurmaDAO;
import br.com.sge.modelo.Turma;

@ManagedBean(name="turmaMB", eager = true)
@RequestScoped
public class TurmaMB extends GeneralBean implements Controlador<Turma>{

	private static final long serialVersionUID = 1L;
	private ArrayList<Turma> lista = new ArrayList<>();
	private Turma turma = new Turma();

	public TurmaMB() {
		this.listar();
	}
	
	public ArrayList<Turma> getLista() { return lista; }
	public void setLista(ArrayList<Turma> lista) { this.lista = lista; }

	public Turma getTurma() { return turma;}
	public void setTurma(Turma turma) { this.turma = turma; }

	@Override
	public void cadastrar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listar() {
		TurmaDAO dao = new TurmaDAO();
		this.setLista(dao.listar());
		this.setQtd(this.getLista().size());
	}

	@Override
	public void pesquisar() {
		
		TurmaDAO dao = new TurmaDAO();
		this.setLista(dao.pesquisar(this.getTurma()));
		this.setQtd(this.getLista().size());
	}
	
	@Override
	public void removerRegistro() {
		// TODO Auto-generated method stub
	}

	@Override
	public void editar() {
		// TODO Auto-generated method stub
		
	}
}