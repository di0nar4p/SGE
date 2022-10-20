package br.com.sge.modelo;

public class Contato extends Modelo{

	private int codUsuario;
    private String email;
	private String telefone;
	private String celular;
		
	public int getCodUsuario(){return codUsuario;}
	public void setCodUsuario(int codUsuario){this.codUsuario = codUsuario;}
	
    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }
    
	public String getTelefone(){return telefone;}
	public void setTelefone(String telefone){this.telefone = telefone;}
	
	public String getCelular(){return celular;}
	public void setCelular(String celular){this.celular = celular;}
}