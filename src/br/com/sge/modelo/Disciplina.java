package br.com.sge.modelo;

public class Disciplina extends Modelo{
	
	private int cargaHoraria;
	private String nome;
	private String descricao;

	public int getCargaHoraria(){return cargaHoraria;}
	public void setCargaHoraria(int cargaHoraria){this.cargaHoraria = cargaHoraria;}

	public String getDescricao(){return descricao;}
	public void setDescricao(String descricao){this.descricao = descricao;}
	
	public String getNome(){return nome;}
	public void setNome(String nome){this.nome = nome;}

}