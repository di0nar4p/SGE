package br.com.sge.controlador;
import java.io.Serializable;

import br.com.sge.util.Sessao;

public class GeneralBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String qtd = null;
	
	public GeneralBean() {

	}
	
	public String getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = Integer.toString(qtd);
	}
}