package br.com.sge.message;
public abstract class Mensagem{
    
    private int codigo;
    private String mensagem;

    public int getCodigo(){return codigo;}
    public void setCodigo(int codigo){this.codigo = codigo;}

    public String getMensagem(){return mensagem;}
    public void setMensagem(String mensagem) {this.mensagem = mensagem;}
}