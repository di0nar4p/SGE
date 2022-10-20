package br.com.sge.modelo;
import java.util.LinkedList;
import java.util.List;

public class Boletim extends Modelo {

	private static final long serialVersionUID = 1L;
	private String ano;
	private int codigoAluno;
	private int codigoTurma;
	private String nomeAluno;
	private String curso;
	private List<Nota> notas;
		
	public Boletim() {
		notas = new LinkedList<>();
	}
	
	public String getAno() { return ano;}
	public void setAno(String ano) {this.ano = ano;}

	public int getCodigoAluno() { return codigoAluno; }
	public void setCodigoAluno(int codigoAluno) { this.codigoAluno = codigoAluno; }

	public String getCurso() { return curso;}
	public void setCurso(String curso) { this.curso = curso;}

	public int getCodigoTurma() { return codigoTurma; }
	public void setCodigoTurma(int codigoTurma) { this.codigoTurma = codigoTurma; }
	
	public String getNomeAluno() {	return nomeAluno; }
	public void setNomeAluno(String nomeAluno) { this.nomeAluno = nomeAluno; }

	public List<Nota> getNotas() { return notas; }
	public void setNotas(List<Nota> notas) { this.notas = notas; }
}