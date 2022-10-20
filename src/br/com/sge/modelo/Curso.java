package br.com.sge.modelo;

import java.util.Date;

public class Curso extends Modelo{
	private int cargaHoraria;
	private Date periodo;
	private Turno turno;
	private Double valor;
	private String duracao;
	private String nome;
	private GradeCurso grade;

	
	public GradeCurso getGrade(){return grade;}
	public void setGrade(GradeCurso grade){this.grade = grade;}
	
	public String getNome(){return nome;}
	public void setNome(String nome){this.nome = nome;}

	public int getCargaHoraria(){return cargaHoraria;}
	public void setCargaHoraria(int cargaHoraria){this.cargaHoraria = cargaHoraria;}

	public Date getPeriodo(){return periodo;}
	public void setPeriodo(Date periodo){this.periodo = periodo;}

	public Turno getTurno(){return turno;}
	public void setTurno(Turno turno){this.turno = turno;}

	public Double getValor(){return valor;}
	public void setValor(Double valor){this.valor = valor;}

	public String getDuracao(){return duracao;}
	public void setDuracao(String duracao) {this.duracao = duracao;}
	
	@Override
	public String toString() {
		return "Curso [cargaHoraria=" + cargaHoraria + ", periodo=" + periodo + ", turno=" + turno + ", valor=" + valor
				+ ", duracao=" + duracao + ", nome=" + nome + ", grade=" + grade + "]";
	}
}
