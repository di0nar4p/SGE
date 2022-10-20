package br.com.sge.controlador;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.com.sge.dao.CursoDAO;
import br.com.sge.modelo.Curso;
import br.com.sge.modelo.Disciplina;
import br.com.sge.util.Sessao;

@ManagedBean (name="cursoMB")
@SessionScoped
public class CursoMB extends GeneralBean implements Controlador<Curso>{

	private static final long serialVersionUID = 1L;
	private ArrayList<Curso> lista = new ArrayList<>();
	private ArrayList<Disciplina> grade = new ArrayList<>();
	private Curso curso = new Curso();
	private Curso cursoSelecionado = null;
	
	public CursoMB(){
		listar();
	}
	
	public Curso getCurso() { return curso; }
	public void setCurso(Curso curso) { this.curso = curso; }
	
	public ArrayList<Curso> getLista() { return lista; }
	public void setLista(ArrayList<Curso> lista) { this.lista = lista; }

	public ArrayList<Disciplina> getGrade() { return grade;}
	public void setGrade(ArrayList<Disciplina> grade) { this.grade = grade; }

	public Curso getCursoSelecionado() { return cursoSelecionado; }
	public void setCursoSelecionado(Curso cursoSelecionado) { this.cursoSelecionado = cursoSelecionado; }

	public Curso renderizarGradeUsuarioLogado() throws SQLException {
		CursoDAO dao = new CursoDAO();
		return dao.buscarGradePorAluno();
	}
	
	@Override
	public void cadastrar(){
		CursoDAO dao = new CursoDAO();
		dao.cadastrar(this.curso);
		NavigationMB nav = new NavigationMB();
		nav.redirect("cursos/cursos");
		this.listar();
	}

	@Override
	public void listar(){
		CursoDAO dao = new CursoDAO();
		if(Sessao.getInstance().getUser().getTipoUsuario().getCodigo()==1) {
			this.setLista(dao.listar(Sessao.getInstance().getUser()));
		}else {
			this.setLista(dao.listar());
		}
		this.setQtd(this.getLista().size());
	}

	@Override
	public void pesquisar() {
		CursoDAO dao = new CursoDAO();		
		this.setLista(dao.pesquisar(this.getCurso()));
		this.setQtd(this.lista.size());
	}
	
	@Override
	public void removerRegistro() {
		CursoDAO dao = new CursoDAO();	
		dao.excluir(this.curso);
	}
	
	public void goToEditar() {
		NavigationMB nav = new NavigationMB();
		nav.redirect("cursos/editar");
	}
	
	public void editar() {
		CursoDAO dao = new CursoDAO();
		dao.atualizar(cursoSelecionado);
		NavigationMB nav = new NavigationMB();
		nav.redirect("cursos/cursos");
	}
	
	public ArrayList<Disciplina> listarGrade() {
		CursoDAO dao = new CursoDAO();
		return dao.listarGradePorCodCurso(this.curso);
	}
}