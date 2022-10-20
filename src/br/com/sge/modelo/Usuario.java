package br.com.sge.modelo;
import java.util.Date;

public class Usuario extends Modelo{

	private static final long serialVersionUID = 1L;
	private Contato contato;
    private String nomeUsuario;
    private String senha;
    private TipoUsuario tipoUsuario;
    private String matricula;
    private String nomeCompleto;
    private Date dataNascimento;
    private Endereco endereco;
    
    public Usuario(){
    	tipoUsuario = new TipoUsuario();
    	contato = new Contato();
    	endereco = new Endereco();
    }
    
    public Usuario(Usuario usuario) {
    	
    	this.setCodigo(usuario.getCodigo());
    	this.setCodInstituicao(usuario.getCodInstituicao());
    	this.setDataExclusao(usuario.getDataExclusao());
    	this.setDataInclusao(usuario.getDataInclusao());
    	this.setDataNascimento(usuario.getDataNascimento());
    	this.setDescricao(usuario.getDescricao());
    	this.setMatricula(usuario.getMatricula());
    	this.setNomeCompleto(usuario.getNomeCompleto());
    	this.setNomeUsuario(usuario.getNomeUsuario());
    	this.setSenha(usuario.getSenha());
    	this.setTipoUsuario(usuario.getTipoUsuario());
    	this.setTitulo(usuario.getTitulo());    	
    	
    	//Associa Endereço
    	this.setEndereco(usuario.getEndereco());
    	
    	//Associa Contato
    	this.setContato(usuario.getContato());
    }

	public Contato getContato(){ return this.contato; }
    public void setContato(Contato contato){ this.contato = contato; }
    
	public String getNomeUsuario(){ return nomeUsuario; }
    public void setNomeUsuario(String nome){ this.nomeUsuario = nome; }

    public String getSenha(){ return senha; }
    public void setSenha(String senha){ this.senha = senha; }

    public TipoUsuario getTipoUsuario(){ return tipoUsuario; }
    public void setTipoUsuario(TipoUsuario tipoUsuario){ this.tipoUsuario = tipoUsuario; }
    
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    
    public String getNomeCompleto() { return nomeCompleto; }
	public void setNomeCompleto(String nomeCompleto) {	this.nomeCompleto = nomeCompleto; }
	
	public Date getDataNascimento() { return dataNascimento; }
	public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }

	public Endereco getEndereco() { return endereco; }
	public void setEndereco(Endereco endereco) { this.endereco = endereco;}

	public String gerarMatricula() {
		String matriculaGenerica = Integer.toString( (int) (nomeCompleto.length() + Math.random() * 10000000));
		matriculaGenerica += Integer.toString(dataNascimento.getYear());
		return matriculaGenerica;
	}

	@Override
	public String toString() {
		return "Usuario [contato=" + contato + ", nomeUsuario=" + nomeUsuario + ", senha=" + senha + ", tipoUsuario="
				+ tipoUsuario + ", matricula=" + matricula + ", nomeCompleto=" + nomeCompleto + ", dataNascimento="
				+ dataNascimento + ", endereco = "+ endereco +"]";
	}
	
	
}