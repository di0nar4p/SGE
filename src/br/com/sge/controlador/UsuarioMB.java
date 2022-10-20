package br.com.sge.controlador;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import br.com.sge.dao.EnderecoDAO;
import br.com.sge.dao.TipoUsuarioDAO;
import br.com.sge.dao.UsuarioDAO;
import br.com.sge.modelo.TipoUsuario;
import br.com.sge.modelo.UF;
import br.com.sge.modelo.Usuario;

@ManagedBean(name="usuarioMB", eager = true)
@RequestScoped
public class UsuarioMB extends GeneralBean implements Controlador<Usuario>{

	private static final long serialVersionUID = 1L;
	private ArrayList<Usuario> lista = new ArrayList<>();
	private Usuario usuario = new Usuario();
	private Usuario usuarioSelecionado = null;
	
	public UsuarioMB() {
		this.listar();
	}
	
	public ArrayList<Usuario> getLista() { return lista;}
	public void setLista(ArrayList<Usuario> lista) { this.lista = lista;}

	public Usuario getUsuario() { return usuario;}	
	public void setUsuario(Usuario usuario) { this.usuario = usuario; }

	public ArrayList<TipoUsuario> getTipos(){
		TipoUsuarioDAO dao = new TipoUsuarioDAO();
		return dao.listar();
	}
	
	public ArrayList<TipoUsuario> getTipoUsuario(){
		TipoUsuarioDAO tDao = new TipoUsuarioDAO();		
		return (ArrayList<TipoUsuario>) tDao.listar();
	}

	public List<UF> listarEstados() {
		EnderecoDAO dao = new EnderecoDAO();
		return dao.listaUnidadesFederativas();
	}

	private boolean validarCadastro(){
		if(this.usuario.getNomeCompleto().equals("") || this.usuario.getDataNascimento().equals(null) || this.usuario.getTipoUsuario().getCodigo() == -1){
			return false;
		}else if(this.usuario.getTipoUsuario().getCodigo()==4){
			if(this.usuario.getContato().getTelefone().equals(null) || this.usuario.getContato().getCelular().equals(null) || this.usuario.getContato().getEmail().equals(null)) {
				return false;
			}
			return true;
		}else {
			return true;
		}
	}
	
	@Override
	public void cadastrar() {
		NavigationMB nav = new NavigationMB();
		if(this.validarCadastro()){
			UsuarioDAO dao = new UsuarioDAO();
			dao.cadastrar(this.usuario);			
			nav.redirect("usuario/_listar");
		}else{
			//TODO: implementar método de devolver a resposta para a visão
			nav.setErro("Faltam parâmetros para realizar o cadastro, tente outra vez!");
			nav.redirect("erro/erro");
		}
	}
	
	@Override
	public void listar() {
		UsuarioDAO dao = new UsuarioDAO();
		this.setLista(dao.listar());
		this.setQtd(this.getLista().size());
	}
	
	@Override
	public void pesquisar() {
		UsuarioDAO dao = new UsuarioDAO();
		this.setLista(dao.pesquisar(this.getUsuario()));
		this.setQtd(this.lista.size());
	}
	
	@Override
	public void removerRegistro() {
		UsuarioDAO dao = new UsuarioDAO();
		dao.excluir(this.usuario);
		this.usuario = null;
	}

	@Override
	public void editar() {
		UsuarioDAO dao = new UsuarioDAO();
		dao.atualizar(usuario);
		NavigationMB nav = new NavigationMB();
		nav.redirect("usuario/usuarios");
	}
}