package br.com.sge.modelo;

import java.util.ArrayList;

public class Aluno extends Usuario {
	private ArrayList<Aluno> alunos;
	
	public Aluno() {
		super();
		alunos = new ArrayList<Aluno>();
	}
	public ArrayList<Aluno> getAlunos() { return this.alunos; }
	public void setAluno(Aluno aluno) { this.alunos.add(aluno); }
}