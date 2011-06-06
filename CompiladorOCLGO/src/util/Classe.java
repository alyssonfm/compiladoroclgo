package util;

import java.util.HashMap;
import java.util.LinkedList;

public class Classe {
	
	public String nome;
	public LinkedList<Parametro> atributos;
	public HashMap<String, Funcao> funcoes;
	
	public Classe(String nome) {
		this.nome = nome;
		atributos = new LinkedList<Parametro>();
		funcoes = new HashMap<String, Funcao>();
	}
	
	private String getCodeLista(){
		String code = "type Node" + nome + " struct{\n";
		code += "\tvalor " + "*" + nome + "\n";
		code += "\tnext " + "*Node" + nome + "\n";
		code += "}\n";
		
		code += "type List" + nome + " struct{\n";
		code += "\thead " + "*Node" + nome + "\n";
		code += "}\n\n";
		return code;
	}
	
	public String code(){
		String code = "type " + nome + " struct{\n";
		
		for(Parametro a : atributos){
			code += "\t" + a.toString() + "\n"; 
		}
		code += "}\n\n";
		
		code += getCodeLista();
		
		for(Funcao f : funcoes.values()){
			code += f.code() + "\n";
		}
		
		return code;
	}
	
}
