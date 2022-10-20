package br.com.sge.modelo;

import java.util.ArrayList;

public class GradeCurso{
	
	private ArrayList<Disciplina> disciplinas;
	private ArrayList<Usuario>  docentes;
	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplinas.add(disciplina);
	}
	public ArrayList<Usuario> getDocentes() {
		return docentes;
	}
	public void setDocentes(Usuario docente){
		this.docentes.add(docente);
	}
}