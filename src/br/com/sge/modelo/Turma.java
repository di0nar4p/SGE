package br.com.sge.modelo;

public class Turma extends Modelo {
	
	private static final long serialVersionUID = 1L;
	private int codCurso;
	private String nome;
	private String anoLetivo;

	public int getCodCurso() { return codCurso; }
	public void setCodCurso(int cod_curso) { this.codCurso = cod_curso; }
	
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	
	public String getAnoLetivo() { return anoLetivo; }
	public void setAnoLetivo(String anoLetivo) { this.anoLetivo = anoLetivo; }
}