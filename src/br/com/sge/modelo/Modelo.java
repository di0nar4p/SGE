package br.com.sge.modelo;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.mysql.cj.result.Field;

public abstract class Modelo  implements Serializable {

	protected int codigo;
    protected int codInstituicao;
    protected String descricao;
    protected Date dataExclusao;
    protected Date dataInclusao;
    protected String data;
    protected String label;
    protected String misc;
    protected String titulo;
	private java.lang.reflect.Field[] fields;
    
    public int getCodigo(){return codigo;}
    public void setCodigo(int codigo){this.codigo = codigo;}

    public int getCodInstituicao(){return codInstituicao;}
    public void setCodInstituicao(int codInstituicao){this.codInstituicao = codInstituicao;}

    public String getDescricao(){return descricao;}
    public void setDescricao(String descricao){this.descricao = descricao;}

    public Date getDataExclusao(){return dataExclusao;}
    public void setDataExclusao(Date dataExclusao){this.dataExclusao = dataExclusao;}

    public Date getDataInclusao(){return dataInclusao;}
    public void setDataInclusao(Date dataInclusao){this.dataInclusao = dataInclusao;}
    
    public String getData(){return data;}
    public void setData(String data){this.data = data;}
    
    public String getLabel(){return label;}
    public void setLabel(String label){this.label = label;}
    
    public String getMisc() { return misc; }
	public void setMisc(String misc) { this.misc = misc; }
	
	public String getTitulo(){return titulo;}
    public void setTitulo(String titulo){this.titulo = titulo;}
    
    public void isNull(Object object){
    	
    	System.out.println(object.getClass().getName());
        
    	fields = object.getClass().getDeclaredFields();
    	int none = 0;
        for(int i=0; i < fields.length; i++) {
        	
        	
        	
        	System.out.println("Meu nome é: " + fields[i]);
        	
        	if(fields[i].getGenericType() == null) {
        		System.out.println("Meu "  + fields[i].getGenericType());
        		none = none + 1;
        	}
    	}
        System.out.println("Campos do objeto = " + fields.length);
        if(none == fields.length){
        	System.out.println("O objeto é nulo");
        }else {
        	System.out.println("O possui dados");
        }
        
        /*
         * Para implementar no DAO
        * for(int i=0; i < fields.length; i++) {
        		System.out.println(fields[i].getName());
        	}
        **/
    }
    @Override
	public String toString() {
		return "Modelo [codigo=" + codigo + ", codInstituicao=" + codInstituicao + ", descricao=" + descricao
				+ ", dataExclusao=" + dataExclusao + ", dataInclusao=" + dataInclusao + ", label=" + label + ", titulo="
				+ titulo + ", fields=" + Arrays.toString(fields) + "]";
	}
}
