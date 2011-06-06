package util;

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
		tipoAssociado = "";
		corpo = "";
		parametros = new LinkedList<Parametro>();
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	public String code(){
		String s = "";
		if(!tipoAssociado.isEmpty())
			s = "func (" + "self *" + tipoAssociado + ") " + nome + "(" + getParametrosString() + ") (" + retornoTipo + ") {\n";
		else
			s = "func " + nome + "(" + getParametrosString() + ") (" + retornoTipo + ") {\n";
		if(retornoTipo.isEmpty()){
			corpo = corpo.trim().equalsIgnoreCase("return") ? "" : corpo;
		}else{
			corpo = corpo.trim().equalsIgnoreCase("return") ? corpo + Gerador.literal(retornoTipo) : corpo;
		}
		s += corpo + "\n";
		s += "}";
		return s;
	}
	
	private String getParametrosString(){
		String s = "";
		for(int i = 0; i < parametros.size(); i++){
			
			Parametro p = parametros.get(i);
			s += p.toString();
			
			if(i + 1 != parametros.size()){
				s += ", ";
			}
			
		}
		return s;
	}
	
	public String getLiteralParametros(){
		String s = "";
		for(int i = 0; i < parametros.size(); i++){
			
			Parametro p = parametros.get(i);
			s += Gerador.literal(p.tipo);
			
			if(i + 1 != parametros.size()){
				s += ", ";
			}
			
		}
		return s;
	}

}
