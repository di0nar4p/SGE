package br.com.sge.modelo;

public class UF extends Modelo{

	private String nome;

	public String getNome(){return nome;}
	public void setNome(String nome){this.nome = nome;}
	@Override
	public String toString() {
		return "UF [nome=" + nome + "]";
	}
}