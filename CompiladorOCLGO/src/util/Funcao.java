package util;

import java.util.ArrayList;
import java.util.LinkedList;

public class Funcao {
	
	public String nome;
	public String retornoTipo;
	public LinkedList<Parametro> parametros;
	public String corpo;
	public String tipoAssociado;
	
	public Funcao(String nome, String retornoTipo) {
		this.nome = nome;
		this.retornoTipo = retornoTipo;
		this.tipoAssociado = tipoAssociado;
		corpo = "";
		parametros = new LinkedList<Parametro>();
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	public String code(){
		String s = "func " + nome + "(" + getParametrosString() + ") (" + retornoTipo + ") {\n";
		s += corpo + "\n";
		s += "}";
		return s;
	}
	
	private String getParametrosString(){
		String s = "";
		for(int i = 0; i < parametros.size(); i++){
			
			Parametro p = parametros.get(i);
			s += p.tipo + " " + p.nome;
			
			if(i + 1 != parametros.size()){
				s += ", ";
			}
			
		}
		return s;
	}

}
