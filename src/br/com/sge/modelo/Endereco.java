package br.com.sge.modelo;

public class Endereco extends Modelo {

	private int codigoUsuario;
	private String cep;
	private String bairro;
	private String cidade;
	private String complemento;
	private String logradouro;
	private String numero;
	private UF uf = new UF();

	public int getCodigoUsuario() {	return codigoUsuario; }
	public void setCodigoUsuario(int codigoUsuario) { this.codigoUsuario = codigoUsuario; }

	public String getCep() { return cep; }
	public void setCep(String cep) { this.cep = cep; }
	
	public String getBairro() { return bairro; }
	public void setBairro(String bairro) { this.bairro = bairro; }

	public String getCidade() { return cidade; }
	public void setCidade(String cidade) { this.cidade = cidade; }

	public String getComplemento() { return complemento; }
	public void setComplemento(String complemento) { this.complemento = complemento; }

	public String getLogradouro() { return logradouro; }
	public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

	public String getNumero() {	return numero; }
	public void setNumero(String numero) { this.numero = numero; }

	public UF getUf() { return uf; }
	public void setUf(UF uf) { this.uf = uf; }
	@Override
	public String toString() {
		return "Endereco [codigoUsuario=" + codigoUsuario + ", cep=" + cep + ", bairro=" + bairro + ", cidade=" + cidade
				+ ", complemento=" + complemento + ", logradouro=" + logradouro + ", numero=" + numero + ", uf=" + uf
				+ "]";
	}
	
	
}