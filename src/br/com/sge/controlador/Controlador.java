package br.com.sge.controlador;

public interface Controlador<T>{

	void cadastrar();
	
	void editar();
	
	void listar();
	
	void pesquisar();
	
	void removerRegistro();
}