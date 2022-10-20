package br.com.sge.modelo;

public class Nota extends Modelo {
	
	private static final long serialVersionUID = 1L;
	private int codAluno;
	private Curso curso;
	private String disciplina;
	private Double primeiraNota;
	private Double segundaNota;
	private Double terceiraNota;
	private Double quartaNota;
	private Double notaFinal;
	private Double media;

	public Nota() {
		new Disciplina();
	}

	public int getCodAluno() { return codAluno;}
	public void setCodAluno(int codAluno) { this.codAluno = codAluno;}

	public Curso getCurso() { return curso;}
	public void setCurso(Curso curso) { this.curso = curso;}

	public String getDisciplina() { return disciplina;}
	public void setDisciplina(String disciplina) {this.disciplina = disciplina;}

	public Double getPrimeiraNota() { return primeiraNota;}
	public void setPrimeiraNota(Double primeiraNota) { this.primeiraNota = primeiraNota;}

	public Double getSegundaNota() { return segundaNota;}
	public void setSegundaNota(Double segundaNota) { this.segundaNota = segundaNota;}

	public Double getTerceiraNota() { return terceiraNota;}
	public void setTerceiraNota(Double terceiraNota) { this.terceiraNota = terceiraNota;}

	public Double getQuartaNota() { return quartaNota;}
	public void setQuartaNota(Double quartaNota) { this.quartaNota = quartaNota;}
	
	public Double getNotaFinal() { return notaFinal;}
	public void setNotaFinal(Double notaFinal) { this.notaFinal = notaFinal;}

	public Double getMedia() { return	this.media;}
	public void setMedia(Double media) { this.media = media;}
}