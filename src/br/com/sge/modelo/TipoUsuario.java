package br.com.sge.modelo;

public class TipoUsuario extends Modelo{
    private String nome;
    
    public TipoUsuario() {}
    public TipoUsuario(int codigo) {
    	this.codigo = codigo;
    }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}