package br.com.sge.IF;
import java.util.ArrayList;

public interface DAO <T> {
	
	public void cadastrar(T t);
	public void excluir(T t);
	public T buscar(T t);
	public ArrayList<?> listar();
	public void atualizar(T t);
	public ArrayList<?> pesquisar(T t);
}